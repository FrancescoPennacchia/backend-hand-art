package it.handart.backend.business;


import it.handart.backend.domain.rest.Utente;


public interface HandArtService {

	Utente findUtenteByUsername(String username) throws BusinessException;

	Utente updateProfilo(Utente utente) throws BusinessException;

	Utente  registerUtente(Utente utente) throws  BusinessException;

}
