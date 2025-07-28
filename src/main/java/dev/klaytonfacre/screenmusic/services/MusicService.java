package dev.klaytonfacre.screenmusic.services;

import dev.klaytonfacre.screenmusic.models.Album;
import dev.klaytonfacre.screenmusic.models.Artist;
import dev.klaytonfacre.screenmusic.models.Music;
import dev.klaytonfacre.screenmusic.models.MusicType;
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

    public Music make(String title, MusicType type, String artistName, String albumName) {
        Artist artist = artistService.searchByName(artistName).get(0);
        Album album = albumService.searchByName(albumName).get(0);
        Music music = new Music(title, type, artist, album);
        return music;
    }

    public Music save(Music music) {
        return musicRepository.save(music);
    }

    public List<Music> searchByName(String name) {
        return musicRepository.findByTitleContainingIgnoreCase(name);
    }

    public List<Music> searchByAlbum(String albumName) {
        return musicRepository.findByAlbum_NameContainingIgnoreCase(albumName);
    }
}
