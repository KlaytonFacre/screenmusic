package dev.klaytonfacre.screenmusic.services;

import dev.klaytonfacre.screenmusic.exceptions.AlbumNotFoundException;
import dev.klaytonfacre.screenmusic.exceptions.ArtistNotFoundException;
import dev.klaytonfacre.screenmusic.models.AlbumModel;
import dev.klaytonfacre.screenmusic.models.ArtistModel;
import dev.klaytonfacre.screenmusic.models.MusicModel;
import dev.klaytonfacre.screenmusic.models.types.MusicType;
import dev.klaytonfacre.screenmusic.repositories.MusicRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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
        List<ArtistModel> artistModel = artistService.searchByName(artistName);
        if (artistModel.isEmpty()) {
            throw new ArtistNotFoundException();
        }

        List<AlbumModel> albumModel = albumService.searchByName(albumName);
        if (albumModel.isEmpty()) {
            throw new AlbumNotFoundException();
        }

        MusicModel musicModel = new MusicModel(title, type, artistModel.get(0), albumModel.get(0));
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

    public List<MusicModel> searchByArtist(String artistName) {
        return musicRepository.findByArtist_NameContainingIgnoreCase(artistName);
    }
}
