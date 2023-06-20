package com.mjc.school.controller.commands.authors;

import com.mjc.school.controller.BaseController;
import com.mjc.school.controller.utils.Constants;
import com.mjc.school.controller.utils.Utils;
import com.mjc.school.service.dto.author.AuthorDTORequest;
import com.mjc.school.service.dto.author.AuthorDTOResponse;
import com.mjc.school.service.dto.news.NewsDTORequest;
import com.mjc.school.service.dto.news.NewsDTOResponse;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Scanner;

public class GetAuthorByNewsIdCommand extends AbstractAuthorCommand {

    private BaseController<NewsDTORequest, NewsDTOResponse, Long> newsController;

    @Autowired
    public GetAuthorByNewsIdCommand(BaseController<AuthorDTORequest, AuthorDTOResponse, Long> controller, BaseController<NewsDTORequest, NewsDTOResponse, Long> newsController) {
        super(controller);
        this.newsController = newsController;
    }

    @Override
    public void execute(Scanner sc) {
        long id = Utils.getLongFromScanner(sc, Constants.TYPE_NEWS_ID);
        NewsDTOResponse news = newsController.readById(id);
        System.out.println(controller.readById(news.getAuthorId()));
    }

    @Override
    public String getDescription() {
        return "Get author by news id";
    }
}
