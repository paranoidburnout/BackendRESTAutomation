package utils;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.io.IOUtils;

import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.*;

public final class SerializationUtils {
    private SerializationUtils() {
    }
    static final ObjectMapper OBJECT_MAPPER;

    static {
        OBJECT_MAPPER = new ObjectMapper();
        OBJECT_MAPPER.setSerializationInclusion(NON_NULL);
        OBJECT_MAPPER.enable(DeserializationFeature.ACCEPT_EMPTY_ARRAY_AS_NULL_OBJECT);
    }

    static List<Map<String, Object>> deserializeInputStreamAsCollectionMapObjects(InputStream input) {
        try {
            return OBJECT_MAPPER.readValue(input, MAP_STRING_OBJECT_LIST_TYPE);
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            IOUtils.closeQuietly(input);
        }
    }

    static Map<String, Object> deserializeInputStreamAsMapObjects(InputStream input) {
        try {
            return OBJECT_MAPPER.readValue(input, MAP_STRING_OBJECT_TYPE);
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            IOUtils.closeQuietly(input);
        }
    }

    static final JavaType MAP_STRING_OBJECT_TYPE = OBJECT_MAPPER.getTypeFactory()
            .constructMapType(
                    HashMap.class, String.class, Object.class
            );

    static final JavaType MAP_STRING_OBJECT_LIST_TYPE = OBJECT_MAPPER.getTypeFactory()
            .constructCollectionType(
                    List.class, OBJECT_MAPPER.getTypeFactory()
                    .constructMapType(
                            HashMap.class, String.class, Object.class)
            );
}