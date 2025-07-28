package dev.klaytonfacre.screenmusic.services;

import dev.klaytonfacre.screenmusic.models.AlbumModel;
import dev.klaytonfacre.screenmusic.models.ArtistModel;
import dev.klaytonfacre.screenmusic.models.MusicModel;
import dev.klaytonfacre.screenmusic.models.types.MusicType;
import dev.klaytonfacre.screenmusic.repositories.MusicRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MusicService {
    @Autowired
    private ArtistService artistService;
    @Autowired
    private AlbumService albumService;
    private final MusicRepository musicRepository;

    public MusicService(MusicRepository musicRepository) {
        this.musicRepository = musicRepository;
    }

    public MusicModel make(String title, MusicType type, String artistName, String albumName) {
        ArtistModel artistModel = artistService.searchByName(artistName).get(0);
        AlbumModel albumModel = albumService.searchByName(albumName).get(0);
        MusicModel musicModel = new MusicModel(title, type, artistModel, albumModel);
        return musicModel;
    }

    public MusicModel save(MusicModel musicModel) {
        return musicRepository.save(musicModel);
    }

    public List<MusicModel> searchByName(String name) {
        return musicRepository.findByTitleContainingIgnoreCase(name);
    }

    public List<MusicModel> searchByAlbum(String albumName) {
        return musicRepository.findByAlbum_NameContainingIgnoreCase(albumName);
    }
}
