package utils;

import common.TestsContext;
import org.apache.commons.io.IOUtils;

import java.io.InputStream;
import java.nio.charset.StandardCharsets;

public class ResourcesProvider {

    private ResourcesProvider() {
    }

    public static String getDataFromFile(String fileName) {
        try (InputStream inputStream = readFromFile(fileName)) {
            return IOUtils.toString(inputStream, StandardCharsets.UTF_8);
        } catch (Exception e) {
            throw new RuntimeException(String.format("File cannot be opened: %s", fileName), e);
        }
    }

    private static InputStream readFromFile(String fileName) {
        String scenarioFilePath = TestsContext.get().getScenarioFilePath();
        scenarioFilePath = scenarioFilePath.substring(scenarioFilePath.indexOf(SCENARIO_PATH));
        String fullPath = String.format("/%s%s", scenarioFilePath, fileName);
        return ResourcesProvider.class.getResourceAsStream(fullPath);
    }
    private static final String SCENARIO_PATH = "features";

}

