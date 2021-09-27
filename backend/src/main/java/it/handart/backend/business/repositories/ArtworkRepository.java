package it.handart.backend.business.repositories;

import it.handart.backend.domain.rest.OperaPreferita;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ArtworkRepository extends JpaRepository<OperaPreferita, Long> {
    // List<OperaPreferita> getFavoriteArtwork(long idUtente);
}
