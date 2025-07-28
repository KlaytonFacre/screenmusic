package dev.klaytonfacre.screenmusic.repositories;

import dev.klaytonfacre.screenmusic.models.AlbumModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AlbumRepository extends JpaRepository<AlbumModel, Long> {
    List<AlbumModel> findByNameContainingIgnoreCase(String name);
}
