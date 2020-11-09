package steps;

import common.Paths;
import common.TestsContext;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.module.jsv.JsonSchemaValidator;
import utils.RestUtils;

import java.util.Map;

import static common.TestsContext.*;
import static utils.ResourcesProvider.getDataFromFile;
import static utils.RestUtils.pullResponseBodyAsMap;

public class CommonHttpSteps {
    public static final String INITIALIZATION_FILES_PATH = "initialization/";
    public static final String EXPECTED_FILES_PATH_SCHEMA = "schemas/";

    @When("^User sends request with credentials \"([^\"]*)\" to generate token$")
    public void postAuth(String fileName) {
        TestsContext context = get();
        String requestParameters = getDataFromFile(INITIALIZATION_FILES_PATH + fileName);
        context.setResponse(RestUtils.post(Paths.AUTHENTICATION, requestParameters));
    }

    @Then("User successfully receive new token")
    public void verifyPostAuth() {
        get()
                .getResponse()
                .then()
                .assertThat()
                .body(JsonSchemaValidator
                        .matchesJsonSchemaInClasspath(EXPECTED_FILES_PATH_SCHEMA + "token.json"));
        Map<String, Object> responseData = pullResponseBodyAsMap("token.json");
        String token = responseData.get("token").toString();
        get().setToken(token);
    }
}
