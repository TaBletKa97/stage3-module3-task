package com.mjc.school.controller.implementation;

import com.mjc.school.controller.BaseController;
import com.mjc.school.service.BaseService;
import com.mjc.school.service.dto.author.AuthorDTORequest;
import com.mjc.school.service.dto.author.AuthorDTOResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.DependsOn;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
@DependsOn("authorManagingServiceImpl")
public class AuthorControllerImpl implements BaseController<AuthorDTORequest, AuthorDTOResponse, Long> {
    private final BaseService<AuthorDTORequest, AuthorDTOResponse, Long> authorManagingService;

    @Autowired
    public AuthorControllerImpl(BaseService<AuthorDTORequest, AuthorDTOResponse, Long> authorManagingService) {
        this.authorManagingService = authorManagingService;
    }

    @Override
    public List<AuthorDTOResponse> readAll() {
        return authorManagingService.readAll();
    }

    @Override
    public AuthorDTOResponse readById(Long id) {
        return authorManagingService.readById(id);
    }

    @Override
    public AuthorDTOResponse create(AuthorDTORequest createRequest) {
        return authorManagingService.create(createRequest);
    }

    @Override
    public AuthorDTOResponse update(AuthorDTORequest updateRequest) {
        return authorManagingService.update(updateRequest);
    }

    @Override
    public boolean deleteById(Long id) {
        return authorManagingService.deleteById(id);
    }
}
