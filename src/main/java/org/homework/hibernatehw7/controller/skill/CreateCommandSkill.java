package org.homework.hibernatehw7.controller.skill;

import org.homework.hibernatehw7.config.ScannerConsole;
import org.homework.hibernatehw7.controller.interfaces.Controller;
import org.homework.hibernatehw7.model.Skill;
import org.homework.hibernatehw7.services.ServiceFactory;
import org.homework.hibernatehw7.services.interfaces.Service;
import org.homework.hibernatehw7.utils.Validator;

import java.util.Scanner;

public class CreateCommandSkill implements Controller {

    private final Service<Skill, Long> SKILL_SERVICE = ServiceFactory.of(Skill.class);
    private final Scanner scanner = ScannerConsole.getInstance();
    private static CreateCommandSkill createCommandSkill;

    public static CreateCommandSkill getInstance() {
        if (createCommandSkill == null) {
            createCommandSkill = new CreateCommandSkill();
        }
        return createCommandSkill;
    }

    @Override
    public void start() {
        create();
    }

    private void create() {
        final String activity = enterActivity();
        final String level = enterLevel();
        SKILL_SERVICE.createNewSkill(activity, level);
        System.out.println(" ✅ You created \uD83D\uDC49 " + "new Skill" + "\n");
    }

    private String enterActivity() {
        System.out.print(" ENTER ACTIVITY \n\uD83D\uDC49 ");
        System.out.println(" Example: Java, C+, JS, C# ");
        String activity = scanner.next();
        if (!Validator.validString(activity) | !activity.equalsIgnoreCase("java")
                                               & !activity.equalsIgnoreCase("js")
                                               & !activity.equalsIgnoreCase("c+")
                                               & !activity.equalsIgnoreCase("c#")) {
            System.out.println("Try again");
            return enterActivity();
        }
        return activity;
    }

    private String enterLevel() {
        System.out.print(" ENTER LEVEL \n\uD83D\uDC49 ");
        System.out.println(" Example: Junior, Middle, Senior");
        final String level = scanner.next();
        if (!Validator.validString(level) | !level.equalsIgnoreCase("junior")
                                            & !level.equalsIgnoreCase("middle")
                                            & !level.equalsIgnoreCase("senior")) {
            System.out.println("Try again");
            return enterLevel();
        }
        return level;
    }

    @Override
    public void close() {
        System.exit(0);
    }
}
