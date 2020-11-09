package steps;

import common.Paths;
import common.TestsContext;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.module.jsv.JsonSchemaValidator;
import utils.RestUtils;

import static common.TestsContext.*;
import static utils.ResourcesProvider.*;

public class CreateBooking {
    public static final String INITIALIZATION_FILES_PATH = "initialization/";
    public static final String EXPECTED_FILES_PATH_SCHEMA = "schemas/";

    @When("^User sends request with credentials \"([^\"]*)\" to create new booking$")
    public void postBooking(String fileName) {
        TestsContext context = get();
        String requestP = getDataFromFile(
                INITIALIZATION_FILES_PATH + fileName);
        context.setResponse(RestUtils.post(Paths.BOOKING, requestP));

    }

    @Then("User successfully create new booking")
    public void verifyPostBooking() {
        get()
                .getResponse()
                .then()
                .assertThat()
                .body(JsonSchemaValidator
                        .matchesJsonSchemaInClasspath(EXPECTED_FILES_PATH_SCHEMA + "create.json"));
    }
}
