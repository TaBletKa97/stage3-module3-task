package com.mjc.school.repository.implementation;

import com.mjc.school.repository.model.Author;
import org.hibernate.Session;
import org.springframework.context.annotation.DependsOn;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;

@Repository
@DependsOn("dataSource")
public class AuthorRepositoryImpl extends AbstractRepository<Author, Long> {

    public AuthorRepositoryImpl() {
        super(Author.class);
    }

    @Override
    protected void setUpForCreate(EntityManager manager, Author entity) {
        manager.persist(entity);
        manager.flush();
    }

    @Override
    protected void setUpForUpdateEntity(EntityManager manager, Author oldEntity, Author newEntity) {
        oldEntity.setName(newEntity.getName());
    }
}

