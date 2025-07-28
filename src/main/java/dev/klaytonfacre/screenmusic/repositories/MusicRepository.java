package dev.klaytonfacre.screenmusic.repositories;

import dev.klaytonfacre.screenmusic.models.MusicModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MusicRepository extends JpaRepository<MusicModel, Long> {
    List<MusicModel> findByTitleContainingIgnoreCase(String title);

    List<MusicModel> findByAlbum_NameContainingIgnoreCase(String albumName);

    List<MusicModel> findByArtist_NameContainingIgnoreCase(String artistName);
}
