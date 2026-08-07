package com.groupeisi.bibliotheque.mappers;

import java.util.List;

/**
 * Base abstraite pour les mappers concrets (ex: AuteurMapper dans le module "metier").
 * Elle factorise toListDtoList() une bonne fois pour toutes : chaque mapper concret n'a
 * plus qu'à implémenter toListDto/toDetailDto/toEntity (principe Open/Closed : on ajoute
 * un nouveau mapper par entité sans jamais modifier ce code partagé).
 */
public abstract class MapperImpl<E, L, D, C> implements Mapper<E, L, D, C> {
    @Override
    public List<L> toListDtoList(List<E> entities) {
        return entities.stream()
                .map(this::toListDto)
                .toList();
    }
}
