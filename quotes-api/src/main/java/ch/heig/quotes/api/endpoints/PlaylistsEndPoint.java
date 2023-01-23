package ch.heig.quotes.api.endpoints;

import ch.heig.quotes.api.entities.MusicEntity;
import ch.heig.quotes.api.entities.PlaylistEntity;
import ch.heig.quotes.api.repositories.MusicRepository;
import ch.heig.quotes.api.repositories.PlaylistRepository;
import org.openapitools.api.PlaylistsApi;
import org.openapitools.model.Music;
import org.openapitools.model.Playlist;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class PlaylistsEndPoint implements PlaylistsApi {
    @Autowired
    private PlaylistRepository playlistRepository;

    @Override
    public ResponseEntity<List<Playlist>> getPlaylists() {
        List<PlaylistEntity> playlistEntities= playlistRepository.findAll();
        List<Playlist> playlists  = new ArrayList<>();
        for (PlaylistEntity playlistEntity : playlistEntities) {
            Playlist playlist = new Playlist();
            playlist.setId(playlistEntity.getId());
            playlist.setName(playlistEntity.getName());
            playlist.setMusics(playlistEntity.getMusics());
            playlists.add(playlist);
        }
        return new ResponseEntity<List<Playlist>>(playlists, HttpStatus.OK);
    }
}
