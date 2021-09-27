package it.handart.backend.business.impl;

import it.handart.backend.business.BusinessException;
import it.handart.backend.business.repositories.ArtistRepository;
import it.handart.backend.domain.rest.ArtistaPreferito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class HandArtArtistService implements it.handart.backend.business.HandArtArtistService {

    @Autowired
    private ArtistRepository artistRepository;

    @Override
    public void deleteFavoriteArtist(Long idUtente) throws BusinessException {
        artistRepository.deleteById(idUtente);
    }

    @Override
    public void addFavoriteArtist(ArtistaPreferito artista) throws BusinessException {
        artistRepository.save(artista);
    }

    @Override
    public List<ArtistaPreferito> getFavoriteArtists(Long idUtente) throws BusinessException {
        return artistRepository.getFavoriteArtist(idUtente);
    }


}
