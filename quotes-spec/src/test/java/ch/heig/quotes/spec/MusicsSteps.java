package ch.heig.quotes.spec;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openapitools.client.ApiException;
import org.openapitools.client.ApiResponse;
import org.openapitools.client.api.MusicsApi;
import org.openapitools.client.model.AddMusicRequest;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class MusicsSteps {
    private final MusicsApi api = new MusicsApi();
    private AddMusicRequest musicRequest;
    private int statusCode;

    @When("I POST it to the \\/musics endpoint")
    public void iPOSTItToTheMusicsEndpoint() {
        try {
            ApiResponse response = api.addMusicWithHttpInfo(musicRequest);
            statusCode = response.getStatusCode();
        } catch (ApiException e) {
            statusCode = e.getCode();
        }
    }

    @Given("I have a {int} number and a {string} payload")
    public void iHaveANumberAndAPayload(Integer arg0, String arg1) {
        musicRequest = new AddMusicRequest();
        musicRequest.setArtist(arg0);
        musicRequest.setTitle(arg1);
    }

    @Then("I receive a {int} status code for musics endpoint")
    public void iReceiveAStatusStatusCodeForMusicsEndpoint(Integer arg0) {
        assertEquals(arg0, statusCode);
    }
}
