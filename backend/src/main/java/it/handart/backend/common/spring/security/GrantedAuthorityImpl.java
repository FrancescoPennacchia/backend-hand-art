package it.handart.backend.common.spring.security;

import org.springframework.security.core.GrantedAuthority;

@SuppressWarnings("serial")
public class GrantedAuthorityImpl implements GrantedAuthority {
	
	private static final String ROLE_PREFIX = "ROLE_";

	private String name;

	public GrantedAuthorityImpl(String name) {
		super();
		this.name = name;
	}

	@Override
	public String getAuthority() {
		return  ROLE_PREFIX + name;
	}

}
