package steps;

import common.Paths;
import common.TestsContext;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.module.jsv.JsonSchemaValidator;
import utils.RestUtils;

import static common.TestsContext.*;
import static utils.ResourcesProvider.getDataFromFile;

public class PartUpdateBooking {
    public static final String INITIALIZATION_FILES_PATH = "initialization/";
    public static final String EXPECTED_FILES_PATH_SCHEMA = "schemas/";

    @When("^Request with credentials \"([^\"]*)\" was sent by user$")
    public void patchBooking(String fileName) {
        TestsContext context = get();
        String requestP = getDataFromFile(
                INITIALIZATION_FILES_PATH + fileName);
        String id = get()
                .getData()
                .get("bookingid")
                .toString();
        String url = Paths.BOOKING_ID + id;
        context.setResponse(RestUtils.patch(url, requestP));
    }

    @Then("User successfully update booking")
    public void verifyPatchBooking() {
        get()
                .getResponse()
                .then()
                .assertThat()
                .body(JsonSchemaValidator
                        .matchesJsonSchemaInClasspath(EXPECTED_FILES_PATH_SCHEMA + "partialUpdate.json"));
    }
}
