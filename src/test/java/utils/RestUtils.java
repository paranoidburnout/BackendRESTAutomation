package utils;

import common.TestsContext;
import config.EnvConfig;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import io.restassured.http.Headers;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.apache.http.HttpStatus;

import java.io.InputStream;
import java.util.List;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;
import static utils.HeadersBuilder.setHeaders;
import static utils.SerializationUtils.deserializeInputStreamAsCollectionMapObjects;
import static utils.SerializationUtils.deserializeInputStreamAsMapObjects;

public class RestUtils {
    private RestUtils() {
    }

    public static Response post(String url, String req) {
        RequestSpecification specification = prepareRequest(setHeaders()).basePath(url);
        if (!req.isEmpty()) specification.body(req);
        return specification.post();
    }

    public static Response get(String url) {
        RequestSpecification specification = prepareRequest(setHeaders()).basePath(url);
        return specification.get();
    }

    public static Response put(String url, String req) {
        RequestSpecification specification = prepareRequest(setHeaders());
        specification.basePath(url);
        if (!req.isEmpty()) specification.body(req);
        return specification.put();
    }

    public static Response patch(String url, String req) {
        RequestSpecification specification = prepareRequest(setHeaders());
        specification.basePath(url);
        if (!req.isEmpty()) specification.body(req);

        return specification.patch();
    }

    public static Response delete(String url) {
        RequestSpecification specification = prepareRequest(setHeaders()).basePath(url);
        return specification.delete();
    }

    private static Response getResponse(String jsonSchemaFileName) {
        TestsContext context = TestsContext.get();
        Response response = context.getResponse();
        return response
                .then()
                .statusCode(HttpStatus.SC_OK)
                .and()
                .contentType(ContentType.JSON)
                .and()
                .body(matchesJsonSchemaInClasspath(String.format("schemas/%s", jsonSchemaFileName)))
                .extract()
                .response();
    }

    private static RequestSpecification prepareRequest(Headers headers) {
        return given()
                .filter(new RequestLoggingFilter())
                .filter(new ResponseLoggingFilter())
                .headers(headers)
                .baseUri(EnvConfig.URL)
                .redirects()
                .follow(false);
    }
    public static List<Map<String, Object>> pullResponseBodyAsCollectionMapObjects(String jsonSchemaFileName) {
        InputStream input = pullResponseBody(jsonSchemaFileName);
        return deserializeInputStreamAsCollectionMapObjects(input);
    }

    private static InputStream pullResponseBody(String jsonSchemaFileName) {
        return getResponse(jsonSchemaFileName).asInputStream();
    }

    public static Map<String, Object> pullResponseBodyAsMap(String jsonSchemaFileName) {
        InputStream input = pullResponseBody(jsonSchemaFileName);
        return deserializeInputStreamAsMapObjects(input);
    }
}
