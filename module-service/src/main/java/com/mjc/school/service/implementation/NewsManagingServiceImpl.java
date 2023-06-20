package com.mjc.school.service.implementation;

import com.mjc.school.repository.BaseRepository;
import com.mjc.school.repository.model.Author;
import com.mjc.school.repository.model.News;
import com.mjc.school.repository.model.Tag;
import com.mjc.school.service.BaseService;
import com.mjc.school.service.dto.news.NewsDTORequest;
import com.mjc.school.service.dto.news.NewsDTOResponse;
import com.mjc.school.service.exceptions.SearchException;
import com.mjc.school.service.exceptions.ServiceErrorCode;
import com.mjc.school.service.mapper.NewsMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.DependsOn;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static com.mjc.school.service.utils.Constants.ERROR_MSG_AUTHOR_SEARCH;

@Service
@DependsOn("newsRepositoryImpl")
public class NewsManagingServiceImpl implements BaseService<NewsDTORequest,
        NewsDTOResponse, Long> {
    private final BaseRepository<News, Long> newsDao;
    private final BaseRepository<Author, Long> authorDao;
    private final BaseRepository<Tag, Long> tagDao;

    @Autowired
    public NewsManagingServiceImpl(BaseRepository<News, Long> newsDao,
                                   BaseRepository<Author, Long> authorDao, BaseRepository<Tag, Long> tagDao) {
        this.newsDao = newsDao;
        this.authorDao = authorDao;
        this.tagDao = tagDao;
    }

    @Override
    public List<NewsDTOResponse> readAll() {
        return newsDao.readAll().stream()
                .map(NewsMapper.INSTANCE::mapNews)
                .collect(Collectors.toList());
    }

    @Override
    public NewsDTOResponse readById(Long id) {
        News news = newsDao.readById(id).orElseThrow(
                () -> new SearchException(String.format(
                        ServiceErrorCode.NEWS_ID_DOES_NOT_EXIST.getMessage(), id))
        );
        return NewsMapper.INSTANCE.mapNews(news);
    }

    @Override
    public NewsDTOResponse create(NewsDTORequest req) {
        Author author = authorDao.readById(req.getAuthorId()).orElseThrow(
                () -> new SearchException(
                        ServiceErrorCode.AUTHOR_ID_DOES_NOT_EXIST.getMessage())
        );
        News news = new News();
        news.setTitle(req.getTitle());
        news.setContent(req.getContent());
        news.setAuthor(author);

        List<Tag> tags = req.getTags().stream()
                .map(tagDao::readById)
                .map(Optional::get)
                .collect(Collectors.toList());
        news.setTags(tags);

        news = newsDao.create(news);
        return NewsMapper.INSTANCE.mapNews(news);
    }

    @Override
    public NewsDTOResponse update(NewsDTORequest req)  {
        News news = NewsMapper.INSTANCE.unmapNewsReq(req);
        readById(req.getId());
        Author author = authorDao.readById(req.getAuthorId()).orElseThrow(
                () -> new SearchException(ERROR_MSG_AUTHOR_SEARCH)
        );
        news.setAuthor(author);

        List<Tag> tags = req.getTags().stream()
                .map(tagDao::readById)
                .map(Optional::get)
                .collect(Collectors.toList());
        news.setTags(tags);

        News update = newsDao.update(news);
        return NewsMapper.INSTANCE.mapNews(update);
    }

    @Override
    public boolean deleteById(Long id) {
        return newsDao.deleteById(id);
    }
}
