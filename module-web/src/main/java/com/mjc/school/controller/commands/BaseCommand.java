package com.mjc.school.controller.commands;

import java.util.Scanner;

public interface BaseCommand {
    void execute(Scanner sc);
    String getDescription();
}
