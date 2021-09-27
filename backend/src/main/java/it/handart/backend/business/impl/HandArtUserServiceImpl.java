package it.handart.backend.business.impl;

import it.handart.backend.business.BusinessException;
import it.handart.backend.business.HandArtUserService;
import it.handart.backend.business.repositories.UtenteRepository;
import it.handart.backend.domain.rest.Utente;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class HandArtUserServiceImpl implements HandArtUserService {

	@Autowired
	private UtenteRepository utenteRepository;

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Override
	public Utente findUtenteByUsername(String username) throws BusinessException {
		return utenteRepository.findByUsername(username);
	}

	@Override
	public Utente updateProfilo(Utente profilo) throws BusinessException {
		Utente utente = utenteRepository.findByUsername(profilo.getUsername());
		utente.setEmail(profilo.getEmail());
		return utente;
	}

	@Override
	public Utente registerUtente(Utente utente) throws BusinessException {
		Utente nuovoUtente = new Utente();
		nuovoUtente.setEmail(utente.getEmail());
		nuovoUtente.setPassword(passwordEncoder.encode(utente.getPassword()));
		nuovoUtente.setUsername(utente.getUsername());
		nuovoUtente.setNome(utente.getNome());
		nuovoUtente.setCognome(utente.getCognome());
		nuovoUtente = utenteRepository.save(nuovoUtente);

		return nuovoUtente;
	}


}
