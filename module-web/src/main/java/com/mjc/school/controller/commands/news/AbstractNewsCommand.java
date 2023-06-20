package com.mjc.school.controller.commands.news;

import com.mjc.school.controller.BaseController;
import com.mjc.school.controller.commands.BaseCommand;
import com.mjc.school.service.dto.news.NewsDTORequest;
import com.mjc.school.service.dto.news.NewsDTOResponse;
import org.springframework.stereotype.Service;

@Service
public abstract class AbstractNewsCommand implements BaseCommand {
    BaseController<NewsDTORequest, NewsDTOResponse, Long> controller;

    public AbstractNewsCommand(BaseController<NewsDTORequest, NewsDTOResponse, Long> controller) {
        this.controller = controller;
    }
}
