package it.handart.backend.business.repositories;


import it.handart.backend.domain.rest.ArtistaPreferito;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ArtistRepository extends JpaRepository<ArtistaPreferito, Long> {

    @Query("SELECT o FROM ArtistaPreferito o WHERE o.id_utente = :id")
    List<ArtistaPreferito> getFavoritesArtists(@Param("id") long idUtente );

    @Query("SELECT o FROM ArtistaPreferito o WHERE o.id_autore = :idAutore AND o.id_utente = :idUtente")
    ArtistaPreferito getFavoriteArtist(@Param("idAutore") String idAutore, @Param("idUtente") long idUtente );

}
