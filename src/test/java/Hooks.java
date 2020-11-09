import common.TestsContext;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;

public class Hooks {

    @Before
    public void beforeScenario(Scenario scenario) {
        TestsContext.clear();
        TestsContext context = TestsContext.get();
        context.setCucumberScenario(scenario);
    }
}
