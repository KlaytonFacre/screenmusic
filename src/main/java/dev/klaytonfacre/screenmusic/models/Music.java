package dev.klaytonfacre.screenmusic.models;

import jakarta.persistence.*;

@Entity
@Table(name = "musics")
public class Music {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    @Enumerated(EnumType.STRING)
    private MusicType type;

    @ManyToOne
    @JoinColumn(name = "artist_id", nullable = false)
    private Artist artist;
    @JoinColumn(name = "album_id")
    @ManyToOne
    private Album album;

    public Music() {
        // JPA required contructor
    }

    public Music(String title, MusicType type, Artist artist, Album album) {
        setTitle(title);
        setType(type);
        setArtist(artist);
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

    public Artist getArtist() {
        return artist;
    }

    public void setArtist(Artist artist) {
        this.artist = artist;
    }

    public Album getAlbum() {
        return album;
    }

    public void setAlbum(Album album) {
        this.album = album;
    }

    @Override
    public String toString() {
        return "ID: %s, Título: %s, Tipo: %s, Artista: %s".formatted(id, title, type, artist.getName());
    }
}