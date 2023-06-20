package com.mjc.school.repository.implementation;

import com.mjc.school.repository.model.Author;
import org.hibernate.Session;
import org.springframework.context.annotation.DependsOn;
import org.springframework.stereotype.Repository;

@Repository
@DependsOn("dataSource")
public class AuthorRepositoryImpl extends AbstractRepository<Author, Long> {

    public AuthorRepositoryImpl() {
        super(Author.class);
    }

    @Override
    protected void setUpForCreate(Session session, Author entity) {
        session.persist(entity);
        session.flush();
    }

    @Override
    protected void setUpForUpdateEntity(Session session, Author oldEntity, Author newEntity) {
        oldEntity.setName(newEntity.getName());
    }
}

