package dev.klaytonfacre.screenmusic.repositories;

import dev.klaytonfacre.screenmusic.models.Music;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MusicRepository extends JpaRepository<Music, Long> {
    List<Music> findByTitleContainingIgnoreCase(String title);
}
