package it.handart.backend.business.impl;

import it.handart.backend.business.BusinessException;
import it.handart.backend.business.repositories.ArtistRepository;
import it.handart.backend.business.repositories.ArtworkRepository;
import it.handart.backend.domain.rest.OperaPreferita;
import it.handart.backend.domain.rest.Utente;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class HandArtArtworkService implements it.handart.backend.business.HandArtArtworkService {

    @Autowired
    private ArtworkRepository artworkRepository;


    @Override
    public void deleteFavoriteArtwork(long idOpera) throws BusinessException {
        artworkRepository.deleteById(idOpera);
    }

    @Override
    public void addFavoriteArtwork(OperaPreferita opera) throws BusinessException {
        artworkRepository.save(opera);
    }


    @Override
    public List<OperaPreferita> getFavoriteArtwork(Long idUtente) throws BusinessException {
       return artworkRepository.getFavoriteArtwork(idUtente);
    }
}
