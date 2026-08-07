package com.groupeisi.bibliotheque.entities;

import com.groupeisi.bibliotheque.enums.StatutEmprunt;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity

public class Emprunt {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private LocalDate dateEmprunt;
    private LocalDate dateRetourPrevue;
    private LocalDate dateRetourEffective; // null si pas encore rendu

    @Enumerated(EnumType.STRING)
    private StatutEmprunt statut; // EN_COURS, RENDU, EN_RETARD

    @ManyToOne
    private Livre livre;

    @ManyToOne
    private Emprunteur emprunteur;
}
