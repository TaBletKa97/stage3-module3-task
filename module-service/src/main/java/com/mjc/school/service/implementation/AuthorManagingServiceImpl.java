package com.mjc.school.service.implementation;

import com.mjc.school.repository.BaseRepository;
import com.mjc.school.repository.model.Author;
import com.mjc.school.service.BaseService;
import com.mjc.school.service.aop.anotations.OnDelete;
import com.mjc.school.service.dto.author.AuthorDTORequest;
import com.mjc.school.service.dto.author.AuthorDTOResponse;
import com.mjc.school.service.exceptions.SearchException;
import com.mjc.school.service.exceptions.ServiceErrorCode;
import com.mjc.school.service.mapper.AuthorMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.DependsOn;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@DependsOn("authorRepositoryImpl")
public class AuthorManagingServiceImpl implements BaseService<AuthorDTORequest, AuthorDTOResponse, Long> {
    private final BaseRepository<Author, Long> authorDao;

    @Autowired
    public AuthorManagingServiceImpl(BaseRepository<Author, Long> authorDao) {
        this.authorDao = authorDao;
    }


    @Override
    public List<AuthorDTOResponse> readAll() {
        return authorDao.readAll().stream()
                .map(AuthorMapper.INSTANCE::mapAuthor)
                .collect(Collectors.toList());
    }

    @Override
    public AuthorDTOResponse readById(Long id) {
        return AuthorMapper.INSTANCE.mapAuthor(
                authorDao.readById(id).orElseThrow(
                        () -> new SearchException(String.format(
                                ServiceErrorCode.AUTHOR_ID_DOES_NOT_EXIST.getMessage(),
                                id))));
    }

    @Override
    public AuthorDTOResponse create(AuthorDTORequest createRequest) {
        Author author = AuthorMapper.INSTANCE.unmapAuthorReq(createRequest);
        return AuthorMapper.INSTANCE.mapAuthor(authorDao.create(author));
    }

    @Override
    public AuthorDTOResponse update(AuthorDTORequest updateRequest) {
        Author author = AuthorMapper.INSTANCE.unmapAuthorReq(updateRequest);
        readById(author.getId());
        return AuthorMapper.INSTANCE.mapAuthor(authorDao.update(author));
    }

    @OnDelete
    @Override
    public boolean deleteById(Long id) {
        return authorDao.deleteById(id);
    }
}
