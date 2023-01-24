package ch.heig.quotes.api.endpoints;

import ch.heig.quotes.api.entities.MusicEntity;
import ch.heig.quotes.api.entities.PlaylistEntity;
import ch.heig.quotes.api.repositories.MusicRepository;
import ch.heig.quotes.api.repositories.PlaylistRepository;
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
import java.util.List;
import java.util.Optional;

@RestController
public class PlaylistsEndPoint implements PlaylistsApi {
    @Autowired
    private PlaylistRepository playlistRepository;

    @Autowired
    private MusicRepository musicRepository;

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

    @Override
    public ResponseEntity<Void> createPlaylist(@RequestBody String name) {
        PlaylistEntity playlistEntity = new PlaylistEntity();
        playlistEntity.setName(name);
        PlaylistEntity quoteAdded = playlistRepository.save(playlistEntity);
        URI uri = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(quoteAdded.getId())
                .toUri();
        return ResponseEntity.created(uri).build();
    }

    //checker si parametre present et dans service checker les paramètres
    @Override
    public ResponseEntity<Void> addMusicToPlaylist(Integer id, @RequestBody List<Integer> musicIds) {
        Optional<PlaylistEntity> optionalPlaylist = playlistRepository.findById(id);
        if (optionalPlaylist.isEmpty()) {
            //return error
        }
        PlaylistEntity playlistEntity = optionalPlaylist.get();

        for (Integer musicId : musicIds) {
            Optional<MusicEntity> optionalMusic = musicRepository.findById(musicId);
            if (optionalMusic.isEmpty()) {
                //return error
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
        return ResponseEntity.created(uri).build();
    }
}
