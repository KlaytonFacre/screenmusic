package dev.klaytonfacre.screenmusic.services;

import dev.klaytonfacre.screenmusic.models.Artist;
import dev.klaytonfacre.screenmusic.models.Music;
import dev.klaytonfacre.screenmusic.models.MusicType;
import dev.klaytonfacre.screenmusic.repositories.MusicRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MusicService {
    private final ArtistService artistService;
    private final MusicRepository musicRepository;

    public MusicService(MusicRepository musicRepository, ArtistService artistService) {
        this.musicRepository = musicRepository;
        this.artistService = artistService;
    }

    public Music make(String title, MusicType type, String artistName, String albumName) {
        Artist artist = artistService.searchByName(artistName).get(0);
        Music music = new Music(title, type, artist, null);
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
