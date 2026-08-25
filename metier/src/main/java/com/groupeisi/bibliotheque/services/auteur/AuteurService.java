package com.groupeisi.bibliotheque.services.auteur;

import com.groupeisi.bibliotheque.dao.auteur.IAuteurDao;
import com.groupeisi.bibliotheque.dto.auteur.AuteurCreateDto;
import com.groupeisi.bibliotheque.dto.auteur.AuteurDetailDto;
import com.groupeisi.bibliotheque.dto.auteur.AuteurListDto;
import com.groupeisi.bibliotheque.entities.Auteur;
import com.groupeisi.bibliotheque.mappers.AuteurMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class AuteurService implements IAuteurService {

    private final IAuteurDao auteurDao;
    private final AuteurMapper auteurMapper;

    @Autowired
    public AuteurService(IAuteurDao auteurDao, AuteurMapper auteurMapper) {
        this.auteurDao = auteurDao;
        this.auteurMapper = auteurMapper;
    }

    @Override
    public List<AuteurListDto> getAll() {
        List<Auteur> auteurs = auteurDao.list(Auteur.class);
        return auteurs.stream()
                .map(auteurMapper::toListDto)
                .toList();
    }

    @Override
    public boolean save(AuteurCreateDto auteurCreateDto) {
        if (auteurCreateDto == null) {
            return false;
        }
        Auteur auteur = auteurMapper.toEntity(auteurCreateDto);
        return auteurDao.save(auteur);
    }

    @Override
    public boolean delete(Long id) {
        if (id == null) {
            return false;
        }
        return auteurDao.delete(id, Auteur.class);
    }

    @Override
    public boolean update(AuteurCreateDto auteurCreateDto) {
        if (auteurCreateDto == null) {
            return false;
        }
        Auteur auteur = auteurMapper.toEntity(auteurCreateDto);
        return auteurDao.update(auteur);
    }

    @Override
    @Transactional
    public Optional<AuteurDetailDto> get(Long id) {
        if (id == null) {
            return Optional.empty();
        }
        Auteur auteur = auteurDao.get(id, Auteur.class);
        if (auteur == null) {
            return Optional.empty();
        }
        return Optional.of(auteurMapper.toDetailDto(auteur));
    }
}
