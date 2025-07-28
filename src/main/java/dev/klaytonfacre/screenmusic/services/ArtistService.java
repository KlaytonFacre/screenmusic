package dev.klaytonfacre.screenmusic.services;

import dev.klaytonfacre.screenmusic.exceptions.ArtistNotFoundException;
import dev.klaytonfacre.screenmusic.models.ArtistModel;
import dev.klaytonfacre.screenmusic.models.types.ArtistType;
import dev.klaytonfacre.screenmusic.repositories.ArtistRepository;
import dev.klaytonfacre.screenmusic.services.apis.ConsultaChatGPT;
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

    public ArtistModel make(String name, ArtistType type) {
        String bio;
        try {
            bio = consultaChatGPT.obterBiografia(name);
        } catch (Exception e) {
            System.err.println("Erro ao obter biografia do artista: " + e.getMessage());
            bio = "Biografia não disponível.";
        }
        ArtistModel artistModel = new ArtistModel(name, type, bio);
        return artistModel;
    }

    public ArtistModel save(ArtistModel artistModel) {
        return artistRepository.save(artistModel);
    }

    public List<ArtistModel> searchByName(String name) {
        return artistRepository.findByNameContainingIgnoreCase(name);
    }
}
