package dev.klaytonfacre.screenmusic.services;

import dev.klaytonfacre.screenmusic.models.AlbumModel;
import dev.klaytonfacre.screenmusic.repositories.AlbumRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AlbumService {
    private final AlbumRepository albumRepository;

    public AlbumService(AlbumRepository albumRepository) {
        this.albumRepository = albumRepository;
    }

    public AlbumModel make(String name) {
        AlbumModel albumModel = new AlbumModel(name);
        return albumModel;
    }

    public AlbumModel save(AlbumModel albumModel) {
        return albumRepository.save(albumModel);
    }

    public List<AlbumModel> searchByName(String name) {
        return albumRepository.findByNameContainingIgnoreCase(name);
    }
}
