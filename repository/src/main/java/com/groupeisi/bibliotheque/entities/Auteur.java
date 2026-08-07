package com.groupeisi.bibliotheque.entities;

import jakarta.persistence.*;
import lombok.* ;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Data
public class Auteur {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String nom;
    private String prenom;
    private String nationalite;

    @OneToMany(mappedBy = "auteur")
    private List<Livre> livres;
}
