package org.homework.hibernatehw7.controller.skill;

import org.homework.hibernatehw7.config.ScannerConsole;
import org.homework.hibernatehw7.controller.interfaces.Controller;
import org.homework.hibernatehw7.model.Skill;
import org.homework.hibernatehw7.services.SkillServiceImpl;
import org.homework.hibernatehw7.services.interfaces.SkillService;
import org.homework.hibernatehw7.utils.Validator;

import java.util.Scanner;

public class CreateCommandSkill implements Controller {

    private final SkillService SKILL_SERVICE = SkillServiceImpl.getInstance();
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
        Skill skill = SKILL_SERVICE.createNewSkill(enterActivity(), enterLevel());
        System.out.println(" ✅ You created \uD83D\uDC49 " + "new Skill " + skill + "\n");
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
