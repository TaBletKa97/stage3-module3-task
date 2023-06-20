package com.mjc.school.controller.commands.authors;

import com.mjc.school.controller.BaseController;
import com.mjc.school.controller.utils.Utils;
import com.mjc.school.service.dto.author.AuthorDTORequest;
import com.mjc.school.service.dto.author.AuthorDTOResponse;

import java.util.Scanner;

public class DeleteAuthorCommand extends AbstractAuthorCommand {

    public DeleteAuthorCommand(BaseController<AuthorDTORequest, AuthorDTOResponse, Long> controller) {
        super(controller);
    }

    @Override
    public void execute(Scanner sc) {
        final String typeAuthorId = "Type author id:";
        long longFromScanner = Utils.getLongFromScanner(sc, typeAuthorId);
        controller.deleteById(longFromScanner);
    }

    @Override
    public String getDescription() {
        return "Delete author by id.";
    }
}
