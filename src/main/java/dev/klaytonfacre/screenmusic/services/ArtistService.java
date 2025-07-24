package dev.klaytonfacre.screenmusic.services;

import dev.klaytonfacre.screenmusic.models.Artist;
import dev.klaytonfacre.screenmusic.models.ArtistType;
import dev.klaytonfacre.screenmusic.repositories.ArtistRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ArtistService {
    private final ArtistRepository artistRepository;
    private final ConsultaChatGPT consultaChatGPT;

    public ArtistService(ArtistRepository artistRepository, ConsultaChatGPT consultaChatGPT) {
        this.artistRepository = artistRepository;
        this.consultaChatGPT = consultaChatGPT;
    }

    public Artist make(String name, ArtistType type) {
        String bio;
        try {
            bio = consultaChatGPT.obterBiografia(name);
        } catch (Exception e) {
            System.err.println("Erro ao obter biografia do artista: " + e.getMessage());
            bio = "Biografia não disponível.";
        }
        Artist artist = new Artist(name, type, bio);
        return artist;
    }

    public Artist save(Artist artist) {
        return artistRepository.save(artist);
    }

    public List<Artist> searchByName(String name) {
        return artistRepository.findByNameContainingIgnoreCase(name);
    }
}
