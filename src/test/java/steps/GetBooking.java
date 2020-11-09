package steps;

import common.Paths;
import common.TestsContext;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.module.jsv.JsonSchemaValidator;
import utils.RestUtils;

import static common.TestsContext.*;


public class GetBooking {
    public static final String EXPECTED_FILES_PATH_SCHEMA = "schemas/";

    @When("^User sends request to get booking$")
    public void getBooking() {
        TestsContext context = get();
        String id = get()
                .getData()
                .get("bookingid")
                .toString();
        String url = Paths.BOOKING_ID + id;
        context.setResponse(RestUtils.get(url));

    }

    @Then("User successfully receive booking")
    public void verifyGetBooking() {
        get()
                .getResponse()
                .then()
                .assertThat()
                .body(JsonSchemaValidator
                        .matchesJsonSchemaInClasspath(EXPECTED_FILES_PATH_SCHEMA + "get.json"));
    }
}
