package org.homework.hibernatehw7.controller.skill;


import org.homework.hibernatehw7.config.ScannerConsole;
import org.homework.hibernatehw7.controller.interfaces.Controller;
import org.homework.hibernatehw7.services.DeveloperServiceImpl;
import org.homework.hibernatehw7.services.SkillServiceImpl;
import org.homework.hibernatehw7.utils.Validator;

import java.util.Scanner;

public class UpdateSkillCommand implements Controller {

    private final SkillServiceImpl SKILL_SERVICE =  SkillServiceImpl.getInstance();
    private final Scanner scanner = ScannerConsole.getInstance();

    private void update() {
        final String id = enterId();
        final String activity = enterActivity();
        final String level = enterLevel();
        final String developerId = enterDeveloperId();
        SKILL_SERVICE.update(Long.valueOf(id), activity, level,Long.valueOf(developerId));
        System.out.println(" ✅ You updated \uD83D\uDC49 " + SKILL_SERVICE.getById(Long.valueOf(id)).get() + "\n");
    }

    private String enterId() {
        System.out.print(" ENTER ID \n\uD83D\uDC49 ");
        String id = scanner.next();
        if (!Validator.validNumber(id) || SKILL_SERVICE.getById(Long.valueOf(id)).get().getId() == null) {
            System.out.println("Try again");
            return enterId();
        }
        return id;
    }

    private String enterActivity() {
        System.out.print(" ENTER ACTIVITY \n\uD83D\uDC49 ");
        System.out.println(" Example: Java, C++, JS, C# ");
        String activity = scanner.next();
        if (!Validator.validString(activity) | !activity.equalsIgnoreCase("java")
                                               & !activity.equalsIgnoreCase("js")
                                               & !activity.equalsIgnoreCase("c++")
                                               & !activity.equalsIgnoreCase("c#")) {
            System.out.println("Try again");
            return enterActivity();
        }
        return activity;
    }

    private String enterDeveloperId() {
        System.out.print(" ENTER DEVELOPER-ID \n\uD83D\uDC49 ");
        String developerId = scanner.next();
        if (!Validator.validNumber(developerId) ||  DeveloperServiceImpl.getInstance().getById(Long.valueOf(developerId)).get().getId() == null) {
            System.out.println("Try again");
            return enterDeveloperId();
        }
        return developerId;
    }

    private String enterLevel() {
        System.out.print(" ENTER LEVEL \n\uD83D\uDC49 ");
        System.out.println(" Example: Junior, Middle, Senior");
        String level = scanner.next();
        if (!Validator.validString(level) | !level.equalsIgnoreCase("junior")
                                            & !level.equalsIgnoreCase("middle")
                                            & !level.equalsIgnoreCase("senior")) {
            System.out.println("Try again");
            return enterLevel();
        }
        return level;
    }

    @Override
    public void start() {
        update();
    }

    @Override
    public void close() {
        System.exit(0);
        scanner.close();
    }
}
