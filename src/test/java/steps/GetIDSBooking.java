package steps;

import common.Paths;
import common.TestsContext;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import utils.RestUtils;

import java.util.List;
import java.util.Map;

import static common.TestsContext.*;
import static utils.RestUtils.pullResponseBodyAsCollectionMapObjects;

public class GetIDSBooking {
    @When("^Request to get booking by ids was sent$")
    public void getIdsBooking() {
        TestsContext context = get();
        context.setResponse(RestUtils.get(Paths.BOOKING));
    }

    @Then("Client successfully receive booking by ids")
    public void receiveBookingByIds() {
        List<Map<String, Object>> actualResponseData =
                pullResponseBodyAsCollectionMapObjects("idGet.json");
        get().getData().putAll(actualResponseData.get(0));
        get().getData().putAll(actualResponseData.get(7));
    }
}
