package com.groupeisi.bibliotheque.dao.livre;

import com.groupeisi.bibliotheque.dao.RepositoryImpl;
import com.groupeisi.bibliotheque.entities.Livre;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

@Repository
public class LivreDao extends RepositoryImpl<Livre,Long> implements ILivreDao {
    public LivreDao(SessionFactory sessionFactory) {
        super(sessionFactory);
    }
}
