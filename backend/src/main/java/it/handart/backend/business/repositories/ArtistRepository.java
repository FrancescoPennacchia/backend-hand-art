package it.handart.backend.business.repositories;


import it.handart.backend.domain.rest.ArtistaPreferito;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ArtistRepository extends JpaRepository<ArtistaPreferito, Long> {
}
