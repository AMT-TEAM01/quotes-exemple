package ch.heig.quotes.api.endpoints;

import ch.heig.quotes.api.services.ArtistsService;
import org.openapitools.api.ArtistsApi;
import org.openapitools.model.AddArtistRequest;
import org.openapitools.model.Artist;
import org.openapitools.model.ModifyArtistRequest;
import org.openapitools.model.Music;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ArtistsEndPoint implements ArtistsApi {
    @Autowired
    private ArtistsService artistsService;
    @Override
    public ResponseEntity addArtist(AddArtistRequest addArtistRequest) {
        return ResponseEntity.created(artistsService.addArtist(addArtistRequest)).build();
    }

    @Override
    public ResponseEntity<List<Artist>> getArtists() {
        return new ResponseEntity<List<Artist>>(artistsService.getArtists(), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Void> modifyArtist(Integer id, ModifyArtistRequest modifyArtistRequest) {
        return ResponseEntity.created(artistsService.modifyArtist(id, modifyArtistRequest)).build();
    }
}
