package com.mjc.school.controller.commands.news;

import com.mjc.school.controller.BaseController;
import com.mjc.school.controller.utils.Utils;
import com.mjc.school.service.dto.news.NewsDTORequest;
import com.mjc.school.service.dto.news.NewsDTOResponse;

import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

import static com.mjc.school.controller.utils.Constants.*;

public class GetNewsByParamsCommand extends AbstractNewsCommand {

    public GetNewsByParamsCommand(BaseController<NewsDTORequest,
            NewsDTOResponse, Long> controller) {
        super(controller);
    }

    @Override
    public void execute(Scanner sc) {
        System.out.println(TYPE_TITLE);
        System.out.println(TYPE_SKIP);
        String title = sc.nextLine();

        System.out.println(TYPE_ARTICLE);
        System.out.println(TYPE_SKIP);
        String article = sc.nextLine();

        System.out.println(TYPE_AUTHOR_NAME);
        System.out.println(TYPE_SKIP);
        String authorName = sc.nextLine();

        Long tagId = Utils.getLongFromScanner(sc, TYPE_TAG_ID +
                "\n" + TYPE_SKIP);

        System.out.println(TYPE_TAG_NAME);
        System.out.println(TYPE_SKIP);
        String tagName = sc.nextLine();

        List<NewsDTOResponse> news = controller.readAll();
        if (!title.equals("0")) {
            news = news.stream()
                    .filter(n -> title.equals(n.getTitle()))
                    .collect(Collectors.toList());
        }
        if (!news.isEmpty() && !article.equals("0")) {
            news = news.stream()
                    .filter(n-> article.equals(n.getContent()))
                    .collect(Collectors.toList());
        }
        if (!news.isEmpty() && !authorName.equals("0")) {
            news = news.stream()
                    .filter(n-> authorName.equals(n.getAuthorName()))
                    .collect(Collectors.toList());
        }
        if (!news.isEmpty() && !tagName.equals("0")) {
            news = news.stream()
                    .filter(n-> n.getTags().toString().contains(tagName))
                    .collect(Collectors.toList());
        }
        if (!news.isEmpty() && tagId != 0) {
            news = news.stream()
                    .filter(n-> n.getTags().toString().contains("id=" + tagId))
                    .collect(Collectors.toList());
        }
        if (news.isEmpty()) {
            System.out.println(ARE_NOT_CORRESPONDING_NEWS);
        } else {
            System.out.println(news);
        }
    }

    @Override
    public String getDescription() {
        return "Get News by tag names, tag ids, author name, title, content";
    }
}
