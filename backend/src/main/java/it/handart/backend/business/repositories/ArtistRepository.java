package it.handart.backend.business.repositories;


import it.handart.backend.domain.rest.ArtistaPreferito;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ArtistRepository extends JpaRepository<ArtistaPreferito, Long> {

    @Query("SELECT o FROM ArtistaPreferito o WHERE o.utente.id = :id")
    List<ArtistaPreferito> getFavoriteArtist(@Param("id") long idUtente );

}
