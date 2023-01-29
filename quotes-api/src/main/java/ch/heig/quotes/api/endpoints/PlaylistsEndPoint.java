package ch.heig.quotes.api.endpoints;

import ch.heig.quotes.api.entities.MusicEntity;
import ch.heig.quotes.api.entities.PlaylistEntity;
import ch.heig.quotes.api.repositories.MusicRepository;
import ch.heig.quotes.api.repositories.PlaylistRepository;
import ch.heig.quotes.api.services.PlaylistsService;
import io.swagger.models.auth.In;
import org.openapitools.api.PlaylistsApi;
import org.openapitools.model.Playlist;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

@RestController
public class PlaylistsEndPoint implements PlaylistsApi {
    @Autowired
    private PlaylistsService playlistsService;

    @Override
    public ResponseEntity<List<Playlist>> getPlaylists() {
        return new ResponseEntity<List<Playlist>>(playlistsService.getPlaylists(), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Void> createPlaylist(@RequestBody String name) {
        if (name == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return ResponseEntity.created(playlistsService.createPlaylist(name)).build();
    }

    @Override
    public ResponseEntity<Void> addMusicToPlaylist(Integer id, @RequestBody List<Integer> musicIds) {
        if (id == null || musicIds == null || musicIds.size() == 0 || hasDoubles(musicIds)) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return ResponseEntity.created(playlistsService.addMusicToPlaylist(id, musicIds)).build();
    }

    private boolean hasDoubles(List<Integer> ids) {
        for (int i = 0; i < ids.size(); i++){
            for (int j = i + 1; j < ids.size(); j++) {
                if (ids.get(i).intValue() == ids.get(j).intValue()) {
                    return true;
                }
            }
        }
        return false;
    }
}
