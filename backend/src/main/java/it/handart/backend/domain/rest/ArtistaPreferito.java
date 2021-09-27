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

    @ManyToOne
    @JoinColumn(name = "ID_UTENTE", nullable = false)
    private Utente utente;

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

    public Utente getUtente() {
        return utente;
    }

    public void setUtente(Utente utente) {
        this.utente = utente;
    }
}
