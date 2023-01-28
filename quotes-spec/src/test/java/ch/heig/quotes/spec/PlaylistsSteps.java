package ch.heig.quotes.spec;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.cucumber.java.sl.In;
import org.openapitools.client.ApiException;
import org.openapitools.client.ApiResponse;
import org.openapitools.client.api.PlaylistsApi;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class PlaylistsSteps {
    private final PlaylistsApi api = new PlaylistsApi();
    private List<Integer> musics;
    private Integer playlist;
    private int statusCode;


    @When("I PUT it to the \\/playlist\\/id\\/musics endpoint")
    public void iPUTItToThePlaylistIdMusicsEndpoint() {
        try {
            ApiResponse response = api.addMusicToPlaylistWithHttpInfo(playlist, musics);
            statusCode = response.getStatusCode();
        } catch (ApiException e) {
            statusCode = e.getCode();
        }
    }

    @Then("I receive a {int} status code for playlists endpoint")
    public void iReceiveAStatusStatusCodeForPlaylistsEndpoint(Integer status) {
        assertEquals(status, statusCode);
    }

    @Given("I have a list of {string} number and a {int} number")
    public void iHaveAListOfNumberAndAPlaylistNumberNumber(String musics, Integer playlist) {
        this.musics = Arrays.stream(musics.split(",")).mapToInt(Integer::parseInt).boxed().collect(Collectors.toList());
        this.playlist = playlist;
    }
}

