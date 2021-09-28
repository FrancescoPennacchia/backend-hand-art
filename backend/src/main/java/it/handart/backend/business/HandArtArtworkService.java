package it.handart.backend.business;

import it.handart.backend.domain.rest.OperaPreferita;
import it.handart.backend.domain.rest.Utente;

import java.util.List;

public interface HandArtArtworkService {

    void deleteFavoriteArtwork(long  idOperaPreferita) throws BusinessException;

    void addFavoriteArtwork(OperaPreferita opera) throws BusinessException;

    List<OperaPreferita> getFavoritesArtworks(Long idUtente) throws BusinessException;

    OperaPreferita getFavoriteArtwork(String idOpera, Long idUtente) throws BusinessException;

}
