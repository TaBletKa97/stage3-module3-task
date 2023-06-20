package com.mjc.school.controller.implementation;

import com.mjc.school.controller.BaseController;
import com.mjc.school.service.BaseService;
import com.mjc.school.service.dto.news.NewsDTORequest;
import com.mjc.school.service.dto.news.NewsDTOResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.DependsOn;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
@DependsOn("newsManagingServiceImpl")
public class NewsControllerImpl implements BaseController<NewsDTORequest, NewsDTOResponse, Long> {
    private final BaseService<NewsDTORequest, NewsDTOResponse, Long> manager;

    @Autowired
    public NewsControllerImpl(BaseService<NewsDTORequest, NewsDTOResponse, Long> manager) {
        this.manager = manager;
    }

    @Override
    public List<NewsDTOResponse> readAll() {
        return manager.readAll();
    }

    @Override
    public NewsDTOResponse readById(Long id) {
        return manager.readById(id);
    }

    @Override
    public NewsDTOResponse create(NewsDTORequest createRequest) {
        return manager.create(createRequest);
    }

    @Override
    public NewsDTOResponse update(NewsDTORequest updateRequest) {
        return manager.update(updateRequest);
    }

    @Override
    public boolean deleteById(Long id) {
        return manager.deleteById(id);
    }
}
