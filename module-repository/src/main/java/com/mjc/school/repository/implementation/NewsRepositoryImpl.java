package com.mjc.school.repository.implementation;

import com.mjc.school.repository.model.Author;
import com.mjc.school.repository.model.News;
import com.mjc.school.repository.model.Tag;
import org.hibernate.Session;
import org.springframework.context.annotation.DependsOn;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.stream.Collectors;

@Repository
@DependsOn("dataSource")
public class NewsRepositoryImpl extends AbstractRepository<News, Long> {

    public NewsRepositoryImpl() {
        super(News.class);
    }

    @Override
    protected void setUpForCreate(EntityManager manager, News entity) {
        Author mergedAuthor = manager.merge(entity.getAuthor());
        entity.setAuthor(mergedAuthor);

        List<Tag> tags = entity.getTags().stream()
                .map(manager::merge)
                .collect(Collectors.toList());
        entity.setTags(tags);

        manager.persist(entity);
    }

    @Override
    protected void setUpForUpdateEntity(EntityManager manager, News oldEntity, News newEntity) {
        Author newAuthor = newEntity.getAuthor();
        if (newAuthor != null) {
            newAuthor = manager.merge(newAuthor);
        }
        oldEntity.setAuthor(newAuthor);
        oldEntity.setTitle(newEntity.getTitle());
        oldEntity.setContent(newEntity.getContent());
        oldEntity.getTags().clear();
        oldEntity.getTags().addAll(
                newEntity.getTags().stream()
                .map(manager::merge)
                .collect(Collectors.toList()));
    }
}
