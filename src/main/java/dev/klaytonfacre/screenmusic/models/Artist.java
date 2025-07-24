package dev.klaytonfacre.screenmusic.models;

import dev.klaytonfacre.screenmusic.services.ConsultaChatGPT;
import jakarta.persistence.*;
import org.springframework.beans.factory.annotation.Autowired;

@Entity
@Table(name = "artists")
public class Artist {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    @Enumerated(EnumType.STRING)
    private ArtistType type;
    private String bio;

    public Artist() {
        // JPA required constructor
    }

    public Artist(String name, ArtistType type, String bio) {
        setName(name);
        setType(type);
        setBio(bio);
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ArtistType getType() {
        return type;
    }

    public void setType(ArtistType type) {
        this.type = type;
    }

    public String getBio() {
        return bio;
    }

    private void setBio(String bio) {
        this.bio = bio;
    }

    @Override
    public String toString() {
        return "%s (%s), tipo %s. Bio: %s".formatted(name, id, type, bio);
    }
}
