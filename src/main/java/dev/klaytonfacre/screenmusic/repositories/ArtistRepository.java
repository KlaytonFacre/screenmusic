package dev.klaytonfacre.screenmusic.repositories;

import dev.klaytonfacre.screenmusic.models.ArtistModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ArtistRepository extends JpaRepository<ArtistModel, Long> {
    List<ArtistModel> findByNameContainingIgnoreCase(String name);
}
