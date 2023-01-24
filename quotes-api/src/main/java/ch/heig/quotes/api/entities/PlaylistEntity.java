package ch.heig.quotes.api.entities;

import jakarta.persistence.*;
import org.hibernate.annotations.Cascade;
import org.openapitools.model.Music;
import org.openapitools.model.Playlist;

import java.util.ArrayList;
import java.util.List;

@Entity(name = "Playlist")
@Table(name = "playlists")
public class PlaylistEntity {
    @TableGenerator(name = "genPlaylists",
            table = "idPlaylists",
            pkColumnName = "name",
            valueColumnName = "val",
            initialValue = 3,
            allocationSize = 100)
    @Id // @GeneratedValue
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "genPlaylists")
    private int id;

    private String name;

    @ManyToMany(cascade = { CascadeType.ALL })
    @JoinTable(
            name = "Playlist_Music",
            joinColumns = { @JoinColumn(name = "playlist_id") },
            inverseJoinColumns = { @JoinColumn(name = "music_id") }
    )
    private List<MusicEntity> musicsEntities = new ArrayList<>();

    public PlaylistEntity() {}
    public PlaylistEntity(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setRelations(MusicEntity music) {
        musicsEntities.add(music);
    }

    public List<Music> getMusics() {
        List<Music> musics  = new ArrayList<>();
        for (MusicEntity musicEntity : musicsEntities) {
            Music music = new Music();
            music.setId(musicEntity.getId());
            music.setAuthor(musicEntity.getAuthor());
            music.setTitle(musicEntity.getTitle());
            musics.add(music);
        }
        return musics;
    }

    public void setMusics(List<Music> musics) {
        for (Music music : musics) {
            MusicEntity musicEntity = new MusicEntity();
            musicEntity.setTitle(music.getTitle());
            musicEntity.setAuthor(music.getAuthor());
            musicEntity.setId(music.getId());
            musicsEntities.add(musicEntity);
        }
    }
}
