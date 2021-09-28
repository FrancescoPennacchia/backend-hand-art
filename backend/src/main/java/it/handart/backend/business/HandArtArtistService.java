package it.handart.backend.business;

import it.handart.backend.domain.rest.ArtistaPreferito;

import java.util.List;

public interface HandArtArtistService {

    void deleteFavoriteArtist(Long idUtene) throws BusinessException;

    void addFavoriteArtist(ArtistaPreferito artista) throws BusinessException;

    List<ArtistaPreferito> getFavoritesArtists(Long idUtente) throws BusinessException;

    ArtistaPreferito getFavoriteArtist(Long idUtente, String idAutore) throws  BusinessException;

}
