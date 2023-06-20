package com.mjc.school.controller.commands.authors;

import com.mjc.school.controller.BaseController;
import com.mjc.school.controller.utils.Constants;
import com.mjc.school.controller.utils.Utils;
import com.mjc.school.service.dto.author.AuthorDTORequest;
import com.mjc.school.service.dto.author.AuthorDTOResponse;

import java.util.Scanner;

public class UpdateAuthorCommand extends AbstractAuthorCommand {

    public UpdateAuthorCommand(BaseController<AuthorDTORequest, AuthorDTOResponse, Long> controller) {
        super(controller);
    }

    @Override
    public void execute(Scanner sc) {
        long id = Utils.getLongFromScanner(sc, Constants.TYPE_AUTHOR_ID);
        System.out.println(Constants.TYPE_A_NEW_NAME);
        String newName = sc.nextLine();
        AuthorDTORequest request = new AuthorDTORequest();
        request.setId(id);
        request.setName(newName);
        AuthorDTOResponse update = controller.update(request);
        System.out.println(Constants.AUTHOR_UPDATED);
        System.out.println(update);
    }

    @Override
    public String getDescription() {
        return "Update author.";
    }
}
