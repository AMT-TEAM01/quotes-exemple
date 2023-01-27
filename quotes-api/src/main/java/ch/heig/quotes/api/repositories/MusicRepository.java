package ch.heig.quotes.api.repositories;

import ch.heig.quotes.api.entities.ArtistEntity;
import ch.heig.quotes.api.entities.MusicEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MusicRepository extends JpaRepository<MusicEntity, Integer> {
    MusicEntity findById(int id);}
