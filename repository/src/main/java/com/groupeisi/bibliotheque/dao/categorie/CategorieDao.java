package com.groupeisi.bibliotheque.dao.categorie;

import com.groupeisi.bibliotheque.dao.RepositoryImpl;
import com.groupeisi.bibliotheque.entities.Categorie;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

@Repository
public class CategorieDao extends RepositoryImpl<Categorie, Long> implements ICategorieDao {

    public CategorieDao(SessionFactory sessionFactory) {
        super(sessionFactory);
    }

}
