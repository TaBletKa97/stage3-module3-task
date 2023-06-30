package com.mjc.school.repository.implementation;

import com.mjc.school.repository.BaseRepository;
import com.mjc.school.repository.model.BaseEntity;
import org.springframework.context.annotation.DependsOn;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;
import java.util.Optional;

@Repository
@Transactional
@DependsOn("dataSource")
public abstract class AbstractRepository<T extends BaseEntity<K>, K> implements BaseRepository<T, K> {
    @PersistenceContext
    private EntityManager manager;
    private final Class<T> tClass;

    public AbstractRepository(Class<T> tClass) {
        this.tClass = tClass;
    }

    @Override
    public List<T> readAll() {
        CriteriaBuilder cb = manager.getCriteriaBuilder();
        CriteriaQuery<T> tQuery = cb.createQuery(tClass);
        Root<T> root = tQuery.from(tClass);

        Query query = manager.createQuery(tQuery.select(root));
        return query.getResultList();
    }

    @Override
    public Optional<T> readById(K id) {
        return Optional.ofNullable(manager.find(tClass, id));
    }

    @Override
    public T create(T entity) {
        setUpForCreate(manager, entity);
        manager.persist(entity);
        manager.flush();
        return entity;
    }

    @Override
    public T update(T entity) {
        T oldEntity = manager.find(tClass, entity.getId());
        setUpForUpdateEntity(manager, oldEntity, entity);
        manager.flush();
        return oldEntity;
    }

    @Override
    public boolean deleteById(K id) {
        T entity = manager.find(tClass, id);
        if (entity == null) {
            return false;
        }
        manager.remove(entity);
        manager.flush();
        return true;
    }

    @Override
    public boolean existById(K id) {
        T entity = manager.find(tClass, id);
        return entity != null;
    }

    protected abstract void setUpForCreate(EntityManager manager, T entity);

    protected abstract void setUpForUpdateEntity(EntityManager manager, T oldEntity, T newEntity);

}