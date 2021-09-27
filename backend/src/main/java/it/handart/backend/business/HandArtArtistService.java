package it.handart.backend.business;

import it.handart.backend.domain.rest.ArtistaPreferito;

public interface HandArtArtistService {

    void deleteFavoriteArtist(ArtistaPreferito artista) throws BusinessException;

    void addFavoriteArtist(ArtistaPreferito artista) throws BusinessException;

}
