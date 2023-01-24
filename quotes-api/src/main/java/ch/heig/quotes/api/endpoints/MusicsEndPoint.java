package ch.heig.quotes.api.endpoints;

import ch.heig.quotes.api.entities.MusicEntity;
import ch.heig.quotes.api.entities.QuoteEntity;
import ch.heig.quotes.api.repositories.MusicRepository;
import ch.heig.quotes.api.repositories.QuoteRepository;
import ch.heig.quotes.api.services.MusicsService;
import org.openapitools.api.MusicsApi;
import org.openapitools.model.AddMusicRequest;
import org.openapitools.model.Music;
import org.openapitools.model.Quote;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.ArrayList;
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
        return ResponseEntity.created(musicsService.addMusics(addMusicRequest)).build();
    }
}
