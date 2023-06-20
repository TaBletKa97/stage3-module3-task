package com.mjc.school.controller.commands.tags;

import com.mjc.school.controller.BaseController;
import com.mjc.school.controller.commands.BaseCommand;
import com.mjc.school.controller.utils.Constants;
import com.mjc.school.controller.utils.Utils;
import com.mjc.school.service.dto.news.NewsDTORequest;
import com.mjc.school.service.dto.news.NewsDTOResponse;

import java.util.Scanner;

public class GetTagByNewsIdCommand implements BaseCommand {

    private final BaseController<NewsDTORequest, NewsDTOResponse, Long> newsController;

    public GetTagByNewsIdCommand(BaseController<NewsDTORequest, NewsDTOResponse, Long> newsController) {
        this.newsController = newsController;
    }

    @Override
    public void execute(Scanner sc) {
        long id = Utils.getLongFromScanner(sc, Constants.TYPE_NEWS_ID);
        NewsDTOResponse news = newsController.readById(id);
        System.out.println(news.getTags().toString());
    }

    @Override
    public String getDescription() {
        return "Get tags by news id";
    }
}
