package ch.heig.quotes.api.entities;

import jakarta.persistence.*;
import org.openapitools.model.Artist;
import org.openapitools.model.Music;
import org.openapitools.model.Playlist;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity(name = "Music")
@Table(name = "musics")
public class MusicEntity {
    @TableGenerator(name = "genMusics",
            table = "idMusics",
            pkColumnName = "name",
            valueColumnName = "val",
            initialValue = 4,
            allocationSize = 100)
    @Id // @GeneratedValue
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "genMusics")
    private int id;
    private String title;

    @ManyToMany(mappedBy = "musicsEntities")
    private List<PlaylistEntity> playlistsEntities = new ArrayList<>();

    @ManyToOne
    @JoinColumn(name = "artist_id", nullable = false)
    private ArtistEntity artistEntity;

    public MusicEntity() {}

    public MusicEntity(int id, String title) {
        this.id = id;
        this.title = title;
    }

    public void setRelations(PlaylistEntity playlist) {
        playlistsEntities.add(playlist);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public ArtistEntity getArtistEntity() {
        return artistEntity;
    }

    public void setArtistEntity(ArtistEntity artistEntity) {
        this.artistEntity = artistEntity;
    }
}
