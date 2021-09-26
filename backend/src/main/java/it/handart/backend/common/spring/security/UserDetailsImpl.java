package it.handart.backend.common.spring.security;

import com.fasterxml.jackson.annotation.JsonIgnore;
import it.handart.backend.domain.rest.Utente;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@SuppressWarnings("serial")
public class UserDetailsImpl implements UserDetails {

	private Utente utente;

	public UserDetailsImpl(Utente utente) {
		this.utente = utente;
	}

	@Override
	public String getUsername() {
		return utente.getUsername();
	}

	@JsonIgnore
	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@JsonIgnore
	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@JsonIgnore
	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@JsonIgnore
	@Override
	public String getPassword() {
		return utente.getPassword();
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		List<GrantedAuthority> result = new ArrayList<>();


		GrantedAuthorityImpl authorityImpl = new GrantedAuthorityImpl("USER");
		result.add(authorityImpl);

		return result;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}

	public Utente getUtente() {
		return utente;
	}

}