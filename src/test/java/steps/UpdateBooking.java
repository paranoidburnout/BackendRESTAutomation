package steps;

import common.Paths;
import common.TestsContext;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.module.jsv.JsonSchemaValidator;
import utils.RestUtils;

import static common.TestsContext.*;
import static utils.ResourcesProvider.getDataFromFile;

public class UpdateBooking {
    public static final String INITIALIZATION_FILES_PATH = "initialization/";
    public static final String EXPECTED_FILES_PATH_SCHEMA = "schemas/";

    @When("^Client send Request with credentials \"([^\"]*)\"$")
    public void putBooking(String fileName) {
        TestsContext context = get();
        String requestP = getDataFromFile(
                INITIALIZATION_FILES_PATH + fileName);
        String id = get()
                .getData()
                .get("bookingid")
                .toString();
        String url = Paths.BOOKING_ID + id;
        context.setResponse(RestUtils.put(url, requestP));
    }

    @Then("Booking was updated")
    public void verifyPutBooking() {
        get()
                .getResponse()
                .then()
                .assertThat()
                .body(JsonSchemaValidator
                        .matchesJsonSchemaInClasspath(EXPECTED_FILES_PATH_SCHEMA + "update.json"));
    }
}

