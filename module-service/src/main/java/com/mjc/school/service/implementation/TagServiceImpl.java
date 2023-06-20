package com.mjc.school.service.implementation;

import com.mjc.school.repository.BaseRepository;
import com.mjc.school.repository.model.Tag;
import com.mjc.school.service.BaseService;
import com.mjc.school.service.dto.tag.TagDTORequest;
import com.mjc.school.service.dto.tag.TagDTOResponse;
import com.mjc.school.service.exceptions.SearchException;
import com.mjc.school.service.exceptions.ServiceErrorCode;
import com.mjc.school.service.mapper.TagMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TagServiceImpl implements BaseService<TagDTORequest, TagDTOResponse, Long> {

    private final BaseRepository<Tag, Long> tagDao;

    @Autowired
    public TagServiceImpl(BaseRepository<Tag, Long> tagDao) {
        this.tagDao = tagDao;
    }

    @Override
    public List<TagDTOResponse> readAll() {
        return tagDao.readAll().stream()
                .map(TagMapper.INSTANCE::mapTag)
                .collect(Collectors.toList());
    }

    @Override
    public TagDTOResponse readById(Long id) {
        return TagMapper.INSTANCE.mapTag(tagDao.readById(id).orElseThrow(() ->
                new SearchException(ServiceErrorCode.TAG_ID_DOES_NOT_EXIST.getMessage())));
    }

    @Override
    public TagDTOResponse create(TagDTORequest createRequest) {
        return TagMapper.INSTANCE.mapTag(
                tagDao.create(TagMapper.INSTANCE.unmapTagReq(createRequest)));
    }

    @Override
    public TagDTOResponse update(TagDTORequest updateRequest) {
        Tag update = tagDao.update(TagMapper.INSTANCE.unmapTagReq(updateRequest));
        if (update == null) {
            throw new SearchException(ServiceErrorCode.TAG_ID_DOES_NOT_EXIST.getMessage());
        }
        return TagMapper.INSTANCE.mapTag(update);
    }

    @Override
    public boolean deleteById(Long id) {
        return tagDao.deleteById(id);
    }
}
