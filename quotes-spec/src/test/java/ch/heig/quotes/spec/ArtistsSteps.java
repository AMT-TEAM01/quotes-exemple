package ch.heig.quotes.spec;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openapitools.client.ApiException;
import org.openapitools.client.ApiResponse;
import org.openapitools.client.api.ArtistsEndPointApi;
import org.openapitools.client.api.PlaylistsEndPointApi;
import org.openapitools.client.model.AddArtistRequest;

import static org.junit.jupiter.api.Assertions.assertEquals;


public class ArtistsSteps {
    private final ArtistsEndPointApi api = new ArtistsEndPointApi();
    private AddArtistRequest artist;
    private int statusCode;

    @Given("I have an artist payload")
    public void iHaveAnArtistPayload() {
        artist = new AddArtistRequest();
        artist.setName("B2O");
        artist.setStyle("alternatif");
    }

    @When("I POST it to the \\/artists endpoint")
    public void iPOSTItToTheArtistsEndpoint() {
        try {
            ApiResponse response = api.addArtistWithHttpInfo(artist);
            statusCode = response.getStatusCode();
        } catch (ApiException e) {
            statusCode = e.getCode();
        }
    }

    @Then("I receive a {int} status code for artists endpoint")
    public void i_receive_a_status_code_for_artists_endpoint(Integer int1) {
        assertEquals(int1, statusCode);
    }
}