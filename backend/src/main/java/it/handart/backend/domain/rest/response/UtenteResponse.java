package it.handart.backend.domain.rest.response;


import it.handart.backend.domain.rest.Utente;

public class UtenteResponse {

	private Long id_utente;
	private String username;
	private String nome;
	private String cognome;
	private String email;
	
	public UtenteResponse() {
	}
	
	public UtenteResponse(Utente utente) {
		this.id_utente = utente.getId();
		this.nome = utente.getNome();
		this.cognome = utente.getCognome();
		this.username = utente.getUsername();
		this.email = utente.getEmail();
	}

	public Long getId_utente() {
		return id_utente;
	}

	public void setId_utente(Long id_utente) {
		this.id_utente = id_utente;
	}

	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getCognome() {
		return cognome;
	}
	public void setCognome(String cognome) {
		this.cognome = cognome;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}

}