package it.handart.backend.domain.rest;

import javax.persistence.*;

@Entity
@Table(name = "operePreferite")
public class OperaPreferita {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "idOperaPreferita", nullable = false)
    private String id_opera;

    @Column(name = "titoloOpera", nullable = false)
    private String titolo;

    @Column(name = "imageOpera", nullable = false)
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

    public String getId_opera() {
        return id_opera;
    }

    public void setId_opera(String id_opera) {
        this.id_opera = id_opera;
    }

    public String getTitolo() {
        return titolo;
    }

    public void setTitolo(String titolo) {
        this.titolo = titolo;
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
