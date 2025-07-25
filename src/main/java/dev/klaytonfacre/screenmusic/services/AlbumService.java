package dev.klaytonfacre.screenmusic.services;

import dev.klaytonfacre.screenmusic.models.Album;
import dev.klaytonfacre.screenmusic.models.Artist;
import dev.klaytonfacre.screenmusic.models.Music;
import dev.klaytonfacre.screenmusic.models.MusicType;
import dev.klaytonfacre.screenmusic.repositories.AlbumRepository;
import dev.klaytonfacre.screenmusic.repositories.MusicRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AlbumService {
    private final AlbumRepository albumRepository;

    public AlbumService(AlbumRepository albumRepository) {
        this.albumRepository = albumRepository;
    }

    public Album make(String name) {
        Album album = new Album(name);
        return album;
    }

    public Album save(Album album) {
        return albumRepository.save(album);
    }

    public List<Album> searchByName(String name) {
        return albumRepository.findByNameContainingIgnoreCase(name);
    }
}
