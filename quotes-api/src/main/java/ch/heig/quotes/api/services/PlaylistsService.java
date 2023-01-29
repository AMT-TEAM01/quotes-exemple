package ch.heig.quotes.api.services;

import ch.heig.quotes.api.entities.MusicEntity;
import ch.heig.quotes.api.entities.PlaylistEntity;
import ch.heig.quotes.api.exceptions.PlaylistExistsException;
import ch.heig.quotes.api.exceptions.PlaylistNotFoundException;
import ch.heig.quotes.api.repositories.MusicRepository;
import ch.heig.quotes.api.repositories.PlaylistRepository;
import org.openapitools.model.Playlist;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class PlaylistsService {
    @Autowired
    private PlaylistRepository playlistRepository;

    @Autowired
    private MusicRepository musicRepository;

    public List<Playlist> getPlaylists() {
        List<PlaylistEntity> playlistEntities= playlistRepository.findAll();
        List<Playlist> playlists  = new ArrayList<>();
        for (PlaylistEntity playlistEntity : playlistEntities) {
            Playlist playlist = new Playlist();
            playlist.setId(playlistEntity.getId());
            playlist.setName(playlistEntity.getName());
            playlist.setMusics(playlistEntity.getMusics());
            playlists.add(playlist);
        }
        return playlists;
    }

    public URI createPlaylist(String name) {
        var test = playlistRepository.findByName(name);
        if (test != null) {
            throw new PlaylistExistsException(name);
        }
        PlaylistEntity playlistEntity = new PlaylistEntity();
        playlistEntity.setName(name);
        PlaylistEntity quoteAdded = playlistRepository.save(playlistEntity);
        URI uri = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(quoteAdded.getId())
                .toUri();
        return uri;
    }

    public URI addMusicToPlaylist(Integer id, List<Integer> musicIds) {
        Optional<PlaylistEntity> optionalPlaylist = playlistRepository.findById(id);
        if (optionalPlaylist.isEmpty()) {
            throw new PlaylistNotFoundException(id);
        }
        PlaylistEntity playlistEntity = optionalPlaylist.get();

        for (Integer musicId : musicIds) {
            Optional<MusicEntity> optionalMusic = musicRepository.findById(musicId);
            if (optionalMusic.isEmpty()) {
                throw new PlaylistNotFoundException(musicId);
            }
            MusicEntity musicEntity = optionalMusic.get();
            playlistEntity.setRelations(musicEntity);
            musicEntity.setRelations(playlistEntity);
        }
        playlistRepository.save(playlistEntity);

        URI uri = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(id)
                .toUri();
        return uri;
    }
}
