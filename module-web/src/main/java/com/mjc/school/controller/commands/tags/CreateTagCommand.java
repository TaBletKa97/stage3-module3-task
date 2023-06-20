package com.mjc.school.controller.commands.tags;

import com.mjc.school.controller.BaseController;
import com.mjc.school.controller.utils.Constants;
import com.mjc.school.service.dto.tag.TagDTORequest;
import com.mjc.school.service.dto.tag.TagDTOResponse;

import java.util.Scanner;

public class CreateTagCommand extends AbstractTagCommand {

    public CreateTagCommand(BaseController<TagDTORequest, TagDTOResponse, Long> controller) {
        super(controller);
    }

    @Override
    public void execute(Scanner sc) {
        System.out.println(Constants.TYPE_A_NAME);
        String name = sc.nextLine();
        TagDTORequest request = new TagDTORequest();
        request.setName(name);
        TagDTOResponse tagDTOResponse = controller.create(request);
        System.out.println(Constants.TAG_CREATED);
        System.out.println(tagDTOResponse);
    }

    @Override
    public String getDescription() {
        return "Create tags.";
    }
}
