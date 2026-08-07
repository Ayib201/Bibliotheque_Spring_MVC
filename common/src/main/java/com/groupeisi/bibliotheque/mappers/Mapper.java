package com.groupeisi.bibliotheque.mappers;

import java.util.List;

/**
 * Contrat générique de mapping entité <-> DTO.
 * E = Entité, L = DTO liste, D = DTO détail, C = DTO création.
 * Vit dans "common" car c'est une pure interface, sans dépendance vers une entité JPA
 * précise : n'importe quel module peut s'appuyer dessus sans lien vers "repository".
 */
public interface Mapper<E, L, D, C> {

    // Entité -> DTO liste (champs minimaux, pour affichage en tableau)
    L toListDto(E entity);

    // Entité -> DTO détail (champs complets, pour une fiche)
    D toDetailDto(E entity);

    // DTO création -> Entité (à la création, pas d'ID en entrée)
    E toEntity(C createDto);

    List<L> toListDtoList(List<E> entities);
}
