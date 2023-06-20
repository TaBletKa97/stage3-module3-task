package com.mjc.school.controller.commands.authors;

import com.mjc.school.controller.BaseController;
import com.mjc.school.controller.commands.BaseCommand;
import com.mjc.school.service.dto.author.AuthorDTORequest;
import com.mjc.school.service.dto.author.AuthorDTOResponse;
import org.springframework.stereotype.Service;

@Service
public abstract class AbstractAuthorCommand implements BaseCommand {
    BaseController<AuthorDTORequest, AuthorDTOResponse, Long> controller;

    public AbstractAuthorCommand(BaseController<AuthorDTORequest, AuthorDTOResponse, Long> controller) {
        this.controller = controller;
    }
}
