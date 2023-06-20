package com.mjc.school.controller.implementation;

import com.mjc.school.controller.BaseController;
import com.mjc.school.service.dto.tag.TagDTORequest;
import com.mjc.school.service.dto.tag.TagDTOResponse;
import com.mjc.school.service.implementation.TagServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.DependsOn;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
@DependsOn("tagServiceImpl")
public class TagControllerImpl implements BaseController<TagDTORequest, TagDTOResponse, Long> {

    private final TagServiceImpl tagService;

    @Autowired
    public TagControllerImpl(TagServiceImpl tagService) {
        this.tagService = tagService;
    }

    @Override
    public List<TagDTOResponse> readAll() {
        return tagService.readAll();
    }

    @Override
    public TagDTOResponse readById(Long id) {
        return tagService.readById(id);
    }

    @Override
    public TagDTOResponse create(TagDTORequest createRequest) {
        return tagService.create(createRequest);
    }

    @Override
    public TagDTOResponse update(TagDTORequest updateRequest) {
        return tagService.update(updateRequest);
    }

    @Override
    public boolean deleteById(Long id) {
        return tagService.deleteById(id);
    }
}
