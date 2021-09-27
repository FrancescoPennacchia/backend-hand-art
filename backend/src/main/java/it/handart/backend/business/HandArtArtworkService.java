package it.handart.backend.business;

import it.handart.backend.domain.rest.OperaPreferita;
import it.handart.backend.domain.rest.Utente;

import java.util.List;

public interface HandArtArtworkService {

    void deleteFavoriteArtwork(long  idOperaPreferita) throws BusinessException;

    void addFavoriteArtwork(OperaPreferita opera) throws BusinessException;

    List<OperaPreferita> getFavoriteArtwork(Long idUtente) throws BusinessException;

}
