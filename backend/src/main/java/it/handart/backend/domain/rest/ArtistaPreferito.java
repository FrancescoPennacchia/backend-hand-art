package it.handart.backend.domain.rest;

import javax.persistence.*;

@Entity
@Table(name = "artistiPreferiti")
public class ArtistaPreferito {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "idAutorePreferito", nullable = false)
    private Long id_autore;

    @Column(name = "nomeAutore", nullable = false)
    private String nome;

    @Column(name = "imageAutore", nullable = false)
    private String image;

    @Column(name = "id_utente", nullable = false)
    private Long id_utente;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId_autore() {
        return id_autore;
    }

    public void setId_autore(Long id_autore) {
        this.id_autore = id_autore;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Long getId_utente() {
        return id_utente;
    }

    public void setId_utente(Long id_utente) {
        this.id_utente = id_utente;
    }
}
