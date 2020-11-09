package common;

import io.cucumber.java.Scenario;
import io.restassured.response.Response;
import lombok.Getter;
import lombok.Setter;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

@Getter
@Setter
public class TestsContext {

    public static final ConcurrentMap<String, String> GLOBAL_CONTEXT;
    private static final ThreadLocal<TestsContext> CONTEXT;
    private final Map<String, Object> data;
    private String scenarioFilePath;
    private String requestContentType;
    private Scenario scenario;
    private Response response;
    private String token;

    static {
        GLOBAL_CONTEXT = new ConcurrentHashMap<>();
        CONTEXT = ThreadLocal.withInitial(TestsContext::new);
    }

    private TestsContext() {
        data = new HashMap<>();
    }

    public static void clear() {
        GLOBAL_CONTEXT.clear();
        CONTEXT.remove();
    }

    public static TestsContext get() {
        return CONTEXT.get();
    }

    public void setCucumberScenario(Scenario scenario) {
        this.scenario = scenario;
        scenarioFilePath = String.valueOf(scenario.getUri());
        scenarioFilePath = scenarioFilePath.substring(0, scenarioFilePath.lastIndexOf('/')) + '/';
    }

}
