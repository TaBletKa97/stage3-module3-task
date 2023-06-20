package com.mjc.school.controller.menu;

import com.mjc.school.controller.commands.BaseCommand;
import com.mjc.school.controller.commands.ShutdownCommand;
import com.mjc.school.controller.commands.authors.*;
import com.mjc.school.controller.commands.news.*;
import com.mjc.school.controller.commands.tags.*;
import com.mjc.school.controller.implementation.AuthorControllerImpl;
import com.mjc.school.controller.implementation.NewsControllerImpl;
import com.mjc.school.controller.implementation.TagControllerImpl;
import org.springframework.stereotype.Component;

import javax.annotation.PreDestroy;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.stream.Collectors;

import static com.mjc.school.controller.utils.Constants.INCORRECT_VALUE;

@Component
public class Menu {
    private final Map<String, BaseCommand> commands;
    private final Scanner sc;

    public Menu(NewsControllerImpl newsController,
                AuthorControllerImpl authorController,
                TagControllerImpl tagController) {
        this.commands = new HashMap<>();
        commands.put("1", new GetAllNewsCommand(newsController));
        commands.put("2", new GetNewsByIdCommand(newsController));
        commands.put("3", new CreateNewsCommand(newsController));
        commands.put("4", new UpdateNewsCommand(newsController));
        commands.put("5", new DeleteNewsCommand(newsController));
        commands.put("6", new GetAllAuthorsCommand(authorController));
        commands.put("7", new GetAuthorByIdCommand(authorController));
        commands.put("8", new CreateAuthorCommand(authorController));
        commands.put("9", new UpdateAuthorCommand(authorController));
        commands.put("10", new DeleteAuthorCommand(authorController));
        commands.put("11", new GetAllTagsCommand(tagController));
        commands.put("12", new GetTagByIdCommand(tagController));
        commands.put("13", new CreateTagCommand(tagController));
        commands.put("14", new UpdateTagCommand(tagController));
        commands.put("15", new DeleteTagCommand(tagController));
        commands.put("16", new GetNewsByParamsCommand(newsController));
        commands.put("17", new GetAuthorByNewsIdCommand(authorController, newsController));
        commands.put("18", new GetTagByNewsIdCommand(newsController));
        commands.put("0", new ShutdownCommand());
        this.sc = new Scanner(System.in);
    }

    public void start() {
        final String option = "Choose an option:\n";
        String menu = commands.entrySet().stream()
                .sorted(Comparator.comparingInt(e -> Integer.parseInt(e.getKey())))
                .map(e -> e.getKey() + ". " + e.getValue().getDescription())
                .skip(1)
                .collect(Collectors.joining("\n", "", "\n0. Exit."));

        while (true) {
            System.out.println(option + menu);
            try {
                String selectedVariant = sc.nextLine();
                if (!commands.containsKey(selectedVariant)) {
                    System.out.println(INCORRECT_VALUE);
                    continue;
                }
                commands.get(selectedVariant).execute(sc);
            } catch (RuntimeException e) {
                e.printStackTrace();
                System.err.println(e.getMessage());
            }
        }
    }

    @PreDestroy
    private void close() {
        if (sc != null) {
            sc.close();
        }
    }
}
