package com.mjc.school.controller.commands.tags;

import com.mjc.school.controller.BaseController;
import com.mjc.school.service.dto.tag.TagDTORequest;
import com.mjc.school.service.dto.tag.TagDTOResponse;

import java.util.Scanner;

public class GetAllTagsCommand extends AbstractTagCommand {

    public GetAllTagsCommand(BaseController<TagDTORequest, TagDTOResponse, Long> controller) {
        super(controller);
    }

    @Override
    public void execute(Scanner sc) {
        controller.readAll().forEach(System.out::println);
    }

    @Override
    public String getDescription() {
        return "Get all tags.";
    }
}
