package dev.klaytonfacre.screenmusic.models;

import dev.klaytonfacre.screenmusic.models.types.MusicType;
import jakarta.persistence.*;

@Entity
@Table(name = "musics")
public class MusicModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    @Enumerated(EnumType.STRING)
    private MusicType type;
    @ManyToOne
    @JoinColumn(name = "artist_id", nullable = false)
    private ArtistModel artistModel;
    @JoinColumn(name = "album_id")
    @ManyToOne
    private AlbumModel album;

    public MusicModel() {
        // JPA required contructor
    }

    public MusicModel(String title, MusicType type, ArtistModel artistModel, AlbumModel album) {
        setTitle(title);
        setType(type);
        setArtist(artistModel);
        setAlbum(album);
    }

    public Long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public MusicType getType() {
        return type;
    }

    public void setType(MusicType type) {
        this.type = type;
    }

    public ArtistModel getArtist() {
        return artistModel;
    }

    public void setArtist(ArtistModel artistModel) {
        this.artistModel = artistModel;
    }

    public AlbumModel getAlbum() {
        return album;
    }

    public void setAlbum(AlbumModel albumModel) {
        this.album = albumModel;
    }

    @Override
    public String toString() {
        return "ID: %s, Título: %s, Tipo: %s, Artista: %s".formatted(id, title, type, artistModel.getName());
    }
}