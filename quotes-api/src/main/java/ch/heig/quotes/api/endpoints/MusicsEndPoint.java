package ch.heig.quotes.api.endpoints;

import ch.heig.quotes.api.exceptions.ArtistNotFoundException;
import ch.heig.quotes.api.services.MusicsService;
import org.openapitools.api.MusicsApi;
import org.openapitools.model.AddMusicRequest;
import org.openapitools.model.Music;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;
import java.util.List;

@RestController
public class MusicsEndPoint implements MusicsApi {
    @Autowired
    private MusicsService musicsService;

    @Override
    public ResponseEntity<List<Music>> getMusics() {
        return new ResponseEntity<List<Music>>(musicsService.getMusics(), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Void> addMusic(AddMusicRequest addMusicRequest) {
        if (addMusicRequest == null || addMusicRequest.getArtist() == null || addMusicRequest.getTitle() == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return ResponseEntity.created(musicsService.addMusics(addMusicRequest)).build();
    }
}
