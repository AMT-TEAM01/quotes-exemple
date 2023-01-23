package ch.heig.quotes.api.entities;

import jakarta.persistence.*;
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
            initialValue = 3,
            allocationSize = 100)
    @Id // @GeneratedValue
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "genMusics")
    private int id;
    private String author;
    private String title;

    @ManyToMany(mappedBy = "musicsEntities")
    private List<PlaylistEntity> playlistsEntities = new ArrayList<>();

    public MusicEntity() {}

    public MusicEntity(int id, String author, String title) {
        this.id = id;
        this.author = author;
        this.title = title;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
