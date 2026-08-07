package com.groupeisi.bibliotheque.mappers;

import com.groupeisi.bibliotheque.dto.auteur.AuteurCreateDto;
import com.groupeisi.bibliotheque.dto.auteur.AuteurDetailDto;
import com.groupeisi.bibliotheque.dto.auteur.AuteurListDto;
import com.groupeisi.bibliotheque.entities.Auteur;
import org.springframework.stereotype.Component;

@Component
public class AuteurMapper
        extends MapperImpl<Auteur, AuteurListDto, AuteurDetailDto, AuteurCreateDto> {

    @Override
    public AuteurListDto toListDto(Auteur entity) {
        return AuteurListDto.builder()
                .id(entity.getId())
                .nom(entity.getNom())
                .prenom(entity.getPrenom())
                .nationalite(entity.getNationalite())
                .build();
    }

    @Override
    public AuteurDetailDto toDetailDto(Auteur entity) {
        return AuteurDetailDto.builder()
                .id(entity.getId())
                .nom(entity.getNom())
                .prenom(entity.getPrenom())
                .nationalite(entity.getNationalite())
                .nombreLivres(entity.getLivres() != null ? entity.getLivres().size() : 0)
                .build();
    }

    @Override
    public Auteur toEntity(AuteurCreateDto createDto) {
        return Auteur.builder()
                .id(createDto.getId())
                .nom(createDto.getNom())
                .prenom(createDto.getPrenom())
                .nationalite(createDto.getNationalite())
                .build();
    }
}
