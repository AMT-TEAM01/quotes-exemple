package ch.heig.quotes.api.services;

import ch.heig.quotes.api.entities.ArtistEntity;
import ch.heig.quotes.api.entities.MusicEntity;
import ch.heig.quotes.api.exceptions.ArtistNotFoundException;
import ch.heig.quotes.api.repositories.ArtistRepository;
import ch.heig.quotes.api.repositories.MusicRepository;
import jdk.dynalink.linker.LinkerServices;
import org.openapitools.model.AddMusicRequest;
import org.openapitools.model.Artist;
import org.openapitools.model.ModifyArtistRequest;
import org.openapitools.model.Music;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class MusicsService {
    @Autowired
    private MusicRepository musicRepository;
    @Autowired
    private ArtistRepository artistRepository;

    public List<Music> getMusics(){
        return getMusics(musicRepository.findAll());
    }

    public static List<Music> getMusics(List<MusicEntity> musicEntities){
        List<Music> musics  = new ArrayList<>();
        for (MusicEntity musicEntity : musicEntities) {
            Music music = new Music();
            music.setId(musicEntity.getId());
            music.setTitle(musicEntity.getTitle());

            var artEnt = musicEntity.getArtistEntity();
            Artist art = new Artist();
            art.setId(artEnt.getId());
            art.setStyle(artEnt.getStyle());
            art.setName(artEnt.getName());

            music.setArtist(art);
            musics.add(music);
        }
        return musics;
    }

    public URI addMusics(AddMusicRequest addMusicRequest) {
        MusicEntity musicEntity = new MusicEntity();
        musicEntity.setTitle(addMusicRequest.getTitle());

        Optional<ArtistEntity> optionalArtistEntity = artistRepository.findById(addMusicRequest.getArtist());
        if (optionalArtistEntity.isEmpty()) {
            throw new ArtistNotFoundException(addMusicRequest.getArtist());
        }

        ArtistEntity artistEntity = optionalArtistEntity.get();
        musicEntity.setArtistEntity(artistEntity);
        MusicEntity quoteAdded = musicRepository.save(musicEntity);
        URI uri = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(quoteAdded.getId())
                .toUri();
        return uri;
    }
}
