package com.groupeisi.bibliotheque.services.auteur;

import com.groupeisi.bibliotheque.dto.auteur.AuteurCreateDto;
import com.groupeisi.bibliotheque.dto.auteur.AuteurDetailDto;
import com.groupeisi.bibliotheque.dto.auteur.AuteurListDto;

import java.util.List;
import java.util.Optional;

public interface IAuteurService {
    List<AuteurListDto> getAll();
    boolean save(AuteurCreateDto auteurCreateDto) ;
    boolean delete(Long id);
    boolean update(AuteurCreateDto auteurCreateDto);
    Optional<AuteurDetailDto> get(Long id);
}
