package com.mjc.school.repository.implementation;

import com.mjc.school.repository.BaseRepository;
import com.mjc.school.repository.model.BaseEntity;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.DependsOn;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.io.Serializable;
import java.util.List;
import java.util.Optional;

@Repository
@DependsOn("dataSource")
public abstract class AbstractRepository<T extends BaseEntity<K>, K> implements BaseRepository<T, K> {
    @Autowired
    private EntityManager entityManager;
    private final Class<T> tClass;

    public AbstractRepository(Class<T> tClass) {
        this.tClass = tClass;
    }

    @Transactional
    @Override
    public List<T> readAll() {
        final String query = "from " + tClass.getSimpleName();
        List<T> resultList;
        try (Session session = entityManager.unwrap(Session.class)) {
            Query from_news = session.createQuery(query);
            resultList = from_news.getResultList();
            session.flush();
        }
        return resultList;
    }

    @Transactional
    @Override
    public Optional<T> readById(K id) {
        try (Session session = entityManager.unwrap(Session.class)) {
            T t = session.get(tClass, (Serializable) id);
            session.flush();
            if (t == null) {
                return Optional.empty();
            } else {
                return Optional.of(t);
            }
        }
    }

    @Transactional
    @Override
    public T create(T entity) {
        try (Session session = entityManager.unwrap(Session.class)) {
            setUpForCreate(session, entity);
            session.flush();
        }
        return entity;
    }

    @Transactional
    @Override
    public T update(T entity) {
        if (!existById(entity.getId())) {
            return null;
        }
        try (Session session = entityManager.unwrap(Session.class)) {
            T oldEntity = session.get(tClass, (Serializable) entity.getId());
            setUpForUpdateEntity(session, oldEntity, entity);
            session.flush();
            return oldEntity;
        }

    }

    @Transactional
    @Override
    public boolean deleteById(K id) {
        if (!existById(id)) {
            return false;
        }
        try (Session session = entityManager.unwrap(Session.class)) {
            T object = session.get(tClass, (Serializable) id);
            session.delete(object);
            session.flush();    ;
            return true;
        }
    }

    @Transactional
    @Override
    public boolean existById(K id) {
        try (Session session = entityManager.unwrap(Session.class)) {
            T object = session.get(tClass, (Serializable) id);
            session.flush();
            return object != null;
        }
    }

    protected abstract void setUpForCreate(Session session, T entity);

    protected abstract void setUpForUpdateEntity(Session session, T oldEntity, T newEntity);

}
