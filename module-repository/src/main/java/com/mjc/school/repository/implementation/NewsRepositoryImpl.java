package com.mjc.school.repository.implementation;

import com.mjc.school.repository.model.Author;
import com.mjc.school.repository.model.News;
import com.mjc.school.repository.model.Tag;
import org.hibernate.Session;
import org.springframework.context.annotation.DependsOn;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.stream.Collectors;

@Repository
@DependsOn("dataSource")
public class NewsRepositoryImpl extends AbstractRepository<News, Long> {

    public NewsRepositoryImpl() {
        super(News.class);
    }

    @Override
    protected void setUpForCreate(Session session, News entity) {
        Author mergedAuthor = (Author) session.merge(entity.getAuthor());
        entity.setAuthor(mergedAuthor);

        List<Tag> tags = entity.getTags().stream()
                .map(session::merge)
                .map(e -> (Tag) e)
                .collect(Collectors.toList());
        entity.setTags(tags);

        session.persist(entity);
    }

    @Override
    protected void setUpForUpdateEntity(Session session, News oldEntity, News newEntity) {
        Author newAuthor = newEntity.getAuthor();
        if (newAuthor != null) {
            newAuthor = (Author) session.merge(newAuthor);
        }
        oldEntity.setAuthor(newAuthor);
        oldEntity.setTitle(newEntity.getTitle());
        oldEntity.setContent(newEntity.getContent());
        oldEntity.getTags().clear();
        oldEntity.getTags().addAll(
                newEntity.getTags().stream()
                .map(session::merge)
                .map(mergedTag -> (Tag) mergedTag)
                .collect(Collectors.toList()));
    }
}
