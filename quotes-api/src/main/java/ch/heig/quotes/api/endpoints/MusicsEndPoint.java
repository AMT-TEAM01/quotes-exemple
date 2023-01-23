package ch.heig.quotes.api.endpoints;

import ch.heig.quotes.api.entities.MusicEntity;
import ch.heig.quotes.api.entities.QuoteEntity;
import ch.heig.quotes.api.repositories.MusicRepository;
import ch.heig.quotes.api.repositories.QuoteRepository;
import org.openapitools.api.MusicsApi;
import org.openapitools.model.Music;
import org.openapitools.model.Quote;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

//table user, author creators many to many, artist
//table playlist
//patch change ordre playlist

@RestController
public class MusicsEndPoint implements MusicsApi {
    @Autowired
    private MusicRepository musicRepository;

    @Override
    public ResponseEntity<List<Music>> getMusics() {
        List<MusicEntity> musicEntities= musicRepository.findAll();
        List<Music> musics  = new ArrayList<>();
        for (MusicEntity quoteEntity : musicEntities) {
            Music music = new Music();
            music.setId(quoteEntity.getId());
            music.setAuthor(quoteEntity.getAuthor());
            music.setTitle(quoteEntity.getTitle());
            musics.add(music);
        }
        return new ResponseEntity<List<Music>>(musics, HttpStatus.OK);
    }
}
