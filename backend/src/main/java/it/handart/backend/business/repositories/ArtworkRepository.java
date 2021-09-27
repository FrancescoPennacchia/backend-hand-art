package it.handart.backend.business.repositories;

import it.handart.backend.domain.rest.OperaPreferita;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ArtworkRepository extends JpaRepository<OperaPreferita, Long> {

    @Query("SELECT o FROM OperaPreferita o WHERE o.utente.id = :id")
    List<OperaPreferita> getFavoriteArtwork( @Param("id") long idUtente );

}
