package com.mjc.school.controller.commands.authors;

import com.mjc.school.controller.BaseController;
import com.mjc.school.controller.utils.Constants;
import com.mjc.school.service.dto.author.AuthorDTORequest;
import com.mjc.school.service.dto.author.AuthorDTOResponse;

import java.util.Scanner;

public class CreateAuthorCommand extends AbstractAuthorCommand {

    public CreateAuthorCommand(BaseController<AuthorDTORequest, AuthorDTOResponse, Long> controller) {
        super(controller);
    }

    @Override
    public void execute(Scanner sc) {
        System.out.println(Constants.TYPE_A_NAME);
        String name = sc.nextLine();
        AuthorDTORequest request = new AuthorDTORequest();
        request.setName(name);
        AuthorDTOResponse authorDTOResponse = controller.create(request);
        System.out.println(Constants.AUTHOR_CREATED);
        System.out.println(authorDTOResponse);
    }

    @Override
    public String getDescription() {
        return "Create author.";
    }
}
