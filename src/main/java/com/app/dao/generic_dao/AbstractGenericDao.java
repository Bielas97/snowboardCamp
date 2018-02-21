package com.app.dao.generic_dao;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;
import java.lang.reflect.ParameterizedType;
import java.util.List;
import java.util.Optional;

@Transactional
public abstract class AbstractGenericDao<T> implements GenericDao<T>{
    @PersistenceContext //wyciaga z konfiguracji instancje EntityManager
    private EntityManager entityManager;
    private Class<T> entityClass = (Class<T>)((ParameterizedType)(this.getClass().getGenericSuperclass())).getActualTypeArguments()[0];

    @Override
    public void add(T t) {
        if (entityManager != null && t != null)
        {
            entityManager.persist(t);
        }
    }

    @Override
    public void update(T t) {
        if (entityManager != null && t != null)
        {
            entityManager.merge(t);
        }
    }

    @Override
    public void delete(Long id) {
        if (entityManager != null && id != null)
        {
            T t = entityManager.find(entityClass, id);
            if (t != null)
            {
                entityManager.remove(t);
            }
        }
    }

    @Override
    public Optional<T> findOneById(Long id) {
        Optional<T> element = Optional.empty();

        if (entityManager != null && id != null)
        {
            element = Optional.of((T)entityManager.find(entityClass, id));
        }

        return element;
    }

    @Override
    public List<T> findAll() {
        List<T> elements = null;

        if (entityManager != null)
        {
            Query query = entityManager.createQuery("select e from " + entityClass.getCanonicalName() + " e");
            elements = query.getResultList();
        }

        return elements;
    }
}
