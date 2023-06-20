package com.mjc.school.controller.commands.authors;

import com.mjc.school.controller.BaseController;
import com.mjc.school.service.dto.author.AuthorDTORequest;
import com.mjc.school.service.dto.author.AuthorDTOResponse;
import com.mjc.school.controller.utils.Utils;

import java.util.Scanner;

import static com.mjc.school.controller.utils.Constants.TYPE_AUTHOR_ID;

public class GetAuthorByIdCommand extends AbstractAuthorCommand {

    public GetAuthorByIdCommand(BaseController<AuthorDTORequest, AuthorDTOResponse, Long> controller) {
        super(controller);
    }

    @Override
    public void execute(Scanner sc) {
        long id = Utils.getLongFromScanner(sc, TYPE_AUTHOR_ID);
        System.out.println(controller.readById(id));
    }

    @Override
    public String getDescription() {
        return "Get author by id.";
    }
}
