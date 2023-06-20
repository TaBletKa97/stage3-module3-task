package com.mjc.school.controller.commands.tags;

import com.mjc.school.controller.BaseController;
import com.mjc.school.controller.utils.Constants;
import com.mjc.school.controller.utils.Utils;
import com.mjc.school.service.dto.tag.TagDTORequest;
import com.mjc.school.service.dto.tag.TagDTOResponse;

import java.util.Scanner;

public class UpdateTagCommand extends AbstractTagCommand {

    public UpdateTagCommand(BaseController<TagDTORequest, TagDTOResponse, Long> controller) {
        super(controller);
    }

    @Override
    public void execute(Scanner sc) {
        Long id = Utils.getLongFromScanner(sc, Constants.TYPE_TAG_ID);

        System.out.println(Constants.TYPE_A_NEW_TAG_NAME);
        String newName = sc.nextLine();

        TagDTORequest request = new TagDTORequest();
        request.setId(id);
        request.setName(newName);

        System.out.println(controller.update(request));
    }

    @Override
    public String getDescription() {
        return "Update tag.";
    }
}
