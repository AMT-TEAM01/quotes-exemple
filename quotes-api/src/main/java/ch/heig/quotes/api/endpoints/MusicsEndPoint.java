package ch.heig.quotes.api.endpoints;

import ch.heig.quotes.api.entities.MusicEntity;
import ch.heig.quotes.api.entities.QuoteEntity;
import ch.heig.quotes.api.repositories.MusicRepository;
import ch.heig.quotes.api.repositories.QuoteRepository;
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
    private MusicRepository musicRepository;

    @Override
    public ResponseEntity<List<Music>> getMusics() {
        List<MusicEntity> musicEntities= musicRepository.findAll();
        List<Music> musics  = new ArrayList<>();
        for (MusicEntity musicEntity : musicEntities) {
            Music music = new Music();
            music.setId(musicEntity.getId());
            music.setAuthor(musicEntity.getAuthor());
            music.setTitle(musicEntity.getTitle());
            musics.add(music);
        }
        return new ResponseEntity<List<Music>>(musics, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Void> addMusic(AddMusicRequest addMusicRequest) {
        MusicEntity musicEntity = new MusicEntity();
        musicEntity.setAuthor(addMusicRequest.getAuthor());
        musicEntity.setTitle(addMusicRequest.getTitle());
        MusicEntity quoteAdded = musicRepository.save(musicEntity);
        URI uri = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(quoteAdded.getId())
                .toUri();
        return ResponseEntity.created(uri).build();
    }
}
