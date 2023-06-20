package com.mjc.school.repository.implementation;

import com.mjc.school.repository.model.Tag;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.context.annotation.DependsOn;
import org.springframework.stereotype.Repository;

@Repository
@DependsOn("dataSource")
public class TagRepositoryImpl extends AbstractRepository<Tag, Long> {

    public TagRepositoryImpl() {
        super(Tag.class);
    }

    @Override
    protected void setUpForCreate(Session session, Tag entity) {
        session.persist(entity);
    }

    @Override
    protected void setUpForUpdateEntity(Session session, Tag oldEntity, Tag newEntity) {
        oldEntity.setName(newEntity.getName());
    }
}
