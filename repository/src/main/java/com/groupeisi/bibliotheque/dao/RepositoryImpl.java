package com.groupeisi.bibliotheque.dao;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
public class RepositoryImpl<T, I> implements Repository<T, I> {

    private final SessionFactory sessionFactory;

    public RepositoryImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    private Session session() {
        return sessionFactory.getCurrentSession();
    }

    @Override
    public boolean save(T entity) {
        session().persist(entity);
        return true;
    }

    @Override
    public boolean update(T entity) {
        session().merge(entity);
        return true;
    }

    @Override
    public List<T> list(Class<T> clazz) {
        CriteriaBuilder cb = session().getCriteriaBuilder();
        CriteriaQuery<T> query = cb.createQuery(clazz);
        Root<T> root = query.from(clazz);
        query.select(root);

        return session().createQuery(query).getResultList();
    }

    @Override
    public T get(I id, Class<T> clazz) {
        return session().get(clazz, id);
    }

    @Override
    public List<T> listpaginate(Class<T> clazz, int page, int pageSize) {
        CriteriaBuilder cb = session().getCriteriaBuilder();
        CriteriaQuery<T> query = cb.createQuery(clazz);
        Root<T> root = query.from(clazz);
        query.select(root);

        Query<T> typedQuery = session().createQuery(query);
        typedQuery.setFirstResult((page - 1) * pageSize);
        typedQuery.setMaxResults(pageSize);

        return typedQuery.getResultList();
    }

    @Override
    public boolean delete(I id, Class<T> clazz) {
        T entity = session().get(clazz, id);

        if (entity == null)
            return false;

        session().remove(entity);
        return true;
    }
}
