package ch.heig.quotes.api.services;

import ch.heig.quotes.api.entities.ArtistEntity;
import ch.heig.quotes.api.entities.MusicEntity;
import ch.heig.quotes.api.exceptions.ArtistNotFoundException;
import ch.heig.quotes.api.repositories.ArtistRepository;
import org.openapitools.model.AddArtistRequest;
import org.openapitools.model.Artist;
import org.openapitools.model.ModifyArtistRequest;
import org.openapitools.model.Music;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ArtistsService {
    @Autowired
    private ArtistRepository artistRepository;

    public URI addArtist(AddArtistRequest addArtistRequest) {
        ArtistEntity artistEntity = new ArtistEntity();
        artistEntity.setName(addArtistRequest.getName());
        artistEntity.setStyle(addArtistRequest.getStyle());
        ArtistEntity quoteAdded = artistRepository.save(artistEntity);
        URI uri = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(quoteAdded.getId())
                .toUri();
        return uri;
    }

    public URI modifyArtist(Integer id, ModifyArtistRequest modifyArtistRequest) {
        Optional<ArtistEntity> optionalArtistEntity = artistRepository.findById(id);
        if (optionalArtistEntity.isEmpty()) {
            throw new ArtistNotFoundException(id);
        }

        ArtistEntity artistEntity = optionalArtistEntity.get();
        if (modifyArtistRequest.getStyle() != null ) {
            artistEntity.setStyle(modifyArtistRequest.getStyle());
        }

        if (modifyArtistRequest.getName() != null) {
            artistEntity.setName(modifyArtistRequest.getName());
        }

        artistEntity = artistRepository.save(artistEntity);

        URI uri = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(artistEntity.getId())
                .toUri();
        return uri;
    }

    public List<Artist> getArtists() {
        List<ArtistEntity> artistEntities = artistRepository.findAll();
        List<Artist> artists  = new ArrayList<>();
        for (ArtistEntity artistEntity : artistEntities) {
            Artist artist = new Artist();
            artist.setId(artistEntity.getId());
            artist.setStyle(artistEntity.getStyle());
            artist.setName(artistEntity.getName());
            artists.add(artist);
        }
        return artists;
    }
}
