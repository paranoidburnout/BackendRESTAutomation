package steps;

import common.Paths;
import common.TestsContext;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import utils.RestUtils;

import static common.TestsContext.*;


public class HealthCheck {

    @When("^Request get health check was sent by user$")
    public void getPing() {
        TestsContext context = get();
        context.setResponse(RestUtils.get(Paths.PING));
    }

    @Then("Client receive API success response")
    public void verifyGetPing() {
        get().getResponse().getStatusCode();
    }
}
