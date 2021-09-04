package it.handart.backend.domain.rest.response;

import it.handart.backend.domain.rest.Utente;

public class UtenteResponseToken extends UtenteResponse{
    private String token;

    public UtenteResponseToken(String token) {
        this.token = token;
    }

    public UtenteResponseToken(Utente utente, String token) {
        super(utente);
        this.token = token;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
