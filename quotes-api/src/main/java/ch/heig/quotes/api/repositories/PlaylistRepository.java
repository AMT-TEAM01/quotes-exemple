package ch.heig.quotes.api.repositories;

import ch.heig.quotes.api.entities.MusicEntity;
import ch.heig.quotes.api.entities.PlaylistEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PlaylistRepository extends JpaRepository<PlaylistEntity, Integer> {
    PlaylistEntity findById(int id);}
