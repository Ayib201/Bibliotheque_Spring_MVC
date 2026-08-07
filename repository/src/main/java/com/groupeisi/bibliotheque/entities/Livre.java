package com.groupeisi.bibliotheque.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class Livre {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String titre;
    private String isbn;
    private int anneePublication;
    private int nombreExemplaires;
    private int exemplairesDisponibles;

    @ManyToOne
    private Auteur auteur;

    @ManyToOne
    private Categorie categorie;
}
