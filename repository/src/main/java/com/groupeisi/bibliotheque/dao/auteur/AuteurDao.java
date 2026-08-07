package com.groupeisi.bibliotheque.dao.auteur;

import com.groupeisi.bibliotheque.dao.RepositoryImpl;
import com.groupeisi.bibliotheque.entities.Auteur;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

@Repository
public class AuteurDao extends RepositoryImpl<Auteur,Long> implements IAuteurDao {
    public AuteurDao(SessionFactory sessionFactory) {
        super(sessionFactory);
    }
}
