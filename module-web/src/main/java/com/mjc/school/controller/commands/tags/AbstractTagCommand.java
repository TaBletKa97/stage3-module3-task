package com.mjc.school.controller.commands.tags;

import com.mjc.school.controller.BaseController;
import com.mjc.school.controller.commands.BaseCommand;
import com.mjc.school.service.dto.tag.TagDTORequest;
import com.mjc.school.service.dto.tag.TagDTOResponse;
import org.springframework.stereotype.Service;

@Service
public abstract class AbstractTagCommand implements BaseCommand {

    BaseController<TagDTORequest, TagDTOResponse, Long> controller;

    public AbstractTagCommand(BaseController<TagDTORequest, TagDTOResponse, Long> controller) {
        this.controller = controller;
    }
}
