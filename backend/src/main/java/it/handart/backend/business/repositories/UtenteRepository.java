package it.handart.backend.business.repositories;


import it.handart.backend.domain.rest.Utente;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UtenteRepository extends JpaRepository<Utente, Long> {

	Utente findByUsername(String username);
}
