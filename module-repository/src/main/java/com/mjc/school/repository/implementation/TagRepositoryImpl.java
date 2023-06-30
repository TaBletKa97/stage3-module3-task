package com.mjc.school.repository.implementation;

import com.mjc.school.repository.model.Tag;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.context.annotation.DependsOn;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;

@Repository
@DependsOn("dataSource")
public class TagRepositoryImpl extends AbstractRepository<Tag, Long> {

    public TagRepositoryImpl() {
        super(Tag.class);
    }

    @Override
    protected void setUpForCreate(EntityManager manager, Tag entity) {
        manager.persist(entity);
    }

    @Override
    protected void setUpForUpdateEntity(EntityManager manager, Tag oldEntity, Tag newEntity) {
        oldEntity.setName(newEntity.getName());
    }
}
