package ch.heig.quotes.api.entities;

import jakarta.persistence.*;
import org.openapitools.model.Artist;
import org.openapitools.model.Music;

import java.util.ArrayList;
import java.util.List;

@Entity(name = "Artist")
@Table(name = "artists")
public class ArtistEntity {
    @TableGenerator(name = "genArtists",
            table = "idArtists",
            pkColumnName = "name",
            valueColumnName = "val",
            initialValue = 4,
            allocationSize = 100)
    @Id // @GeneratedValue
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "genArtists")
    private int id;
    private String name;
    private String style;

    @OneToMany(mappedBy = "artistEntity")
    private List<MusicEntity> musicEntities;

    public ArtistEntity(){}

    public ArtistEntity(int id, String name, String style) {
        this.id = id;
        this.name = name;
        this.style = style;
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

    public String getStyle() {
        return style;
    }

    public void setStyle(String style) {
        this.style = style;
    }

    public List<MusicEntity> getMusicEntities() {
        return musicEntities;
    }

    public void setMusicEntities(List<MusicEntity> musicEntities) {
        this.musicEntities = musicEntities;
    }
}
