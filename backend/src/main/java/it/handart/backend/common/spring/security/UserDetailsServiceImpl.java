package it.handart.backend.common.spring.security;

import it.handart.backend.business.HandArtUserService;
import it.handart.backend.domain.rest.Utente;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

	@Autowired
	private HandArtUserService service;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Utente utente = service.findUtenteByUsername(username);
		if (utente == null) {
			throw new UsernameNotFoundException("utente inesistente");
		}
		return org.springframework.security.core.userdetails.User
				.withUsername(username)
				.password(utente.getPassword())
				.roles("USER")
				.authorities(Collections.emptyList())
				.accountExpired(false)
				.accountLocked(false)
				.credentialsExpired(false)
				.disabled(false)
				.build();

	}

}
