package ch.heig.quotes.api.repositories;

import ch.heig.quotes.api.entities.ArtistEntity;
import ch.heig.quotes.api.entities.MusicEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ArtistRepository extends JpaRepository<ArtistEntity, Integer> {
    ArtistEntity findById(int id);
    List<ArtistEntity> findByNameLike(String pattern);}
