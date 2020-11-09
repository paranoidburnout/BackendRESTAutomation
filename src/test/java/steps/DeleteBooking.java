package steps;

import common.Paths;
import common.TestsContext;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import utils.RestUtils;

import static common.TestsContext.*;


public class DeleteBooking {
    @When("^User sends request to delete booking$")
    public void deleteBooking() {
        TestsContext context = get();
        String id = get().getData().get("bookingid").toString();
        context.setResponse(RestUtils.delete(Paths.DELETE + id));

    }

    @Then("Booking was actually deleted")
    public void verifyDeleteBooking() {
        get().getResponse().getStatusCode();
    }
}
