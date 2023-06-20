package com.mjc.school.controller.commands.tags;

import com.mjc.school.controller.BaseController;
import com.mjc.school.controller.utils.Constants;
import com.mjc.school.controller.utils.Utils;
import com.mjc.school.service.dto.tag.TagDTORequest;
import com.mjc.school.service.dto.tag.TagDTOResponse;

import java.util.Scanner;

public class GetTagByIdCommand extends AbstractTagCommand {

    public GetTagByIdCommand(BaseController<TagDTORequest, TagDTOResponse, Long> controller) {
        super(controller);
    }

    @Override
    public void execute(Scanner sc) {
        Long id = Utils.getLongFromScanner(sc, Constants.TYPE_TAG_ID);
        System.out.println(controller.readById(id));
    }

    @Override
    public String getDescription() {
        return "Get tag by id.";
    }
}
