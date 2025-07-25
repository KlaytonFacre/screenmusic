package dev.klaytonfacre.screenmusic.repositories;

import dev.klaytonfacre.screenmusic.models.Album;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AlbumRepository extends JpaRepository<Album, Long> {
    List<Album> findByNameContainingIgnoreCase(String name);
}
