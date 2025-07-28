package dev.klaytonfacre.screenmusic.models;

import jakarta.persistence.*;

@Entity
@Table(name = "albums")
public class AlbumModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String coverUrl;

    public AlbumModel() {
        // JPA required constructor
    }

    public AlbumModel(String name) {
        setName(name);
        setCoverUrl("");
    }

    public AlbumModel(String name, String coverUrl) {
        setName(name);
        setCoverUrl(coverUrl);
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    private void setName(String name) {
        this.name = name;
    }

    public String getCoverUrl() {
        return coverUrl;
    }

    private void setCoverUrl(String coverUrl) {
        this.coverUrl = coverUrl;
    }
}
