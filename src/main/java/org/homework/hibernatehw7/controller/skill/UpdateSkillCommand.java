package org.homework.hibernatehw7.controller.skill;

import org.homework.hibernatehw7.config.ScannerConsole;
import org.homework.hibernatehw7.controller.interfaces.Controller;
import org.homework.hibernatehw7.services.DeveloperServiceImpl;
import org.homework.hibernatehw7.services.SkillServiceImpl;
import org.homework.hibernatehw7.services.interfaces.SkillService;
import org.homework.hibernatehw7.utils.Validator;

import java.util.Scanner;

public class UpdateSkillCommand implements Controller {

    private final SkillService SKILL_SERVICE =  SkillServiceImpl.getInstance();
    private final Scanner scanner = ScannerConsole.getInstance();
    private static UpdateSkillCommand updateSkillCommand;

    public static UpdateSkillCommand getInstance() {
        if (updateSkillCommand == null) {
            updateSkillCommand = new UpdateSkillCommand();
        }
        return updateSkillCommand;
    }

    @Override
    public void start() {
        update();
    }

    private void update() {
        final String id = enterId();
        SKILL_SERVICE.updateSkill(Long.valueOf(id), enterActivity(), enterLevel());
        System.out.println(" ✅ You updated \uD83D\uDC49 " + SKILL_SERVICE.findById(Long.valueOf(id)).get() + "\n");
    }

    private String enterId() {
        System.out.print(" ENTER ID \n\uD83D\uDC49 ");
        String id = scanner.next();
        if (!Validator.validNumber(id) | !SKILL_SERVICE.findById(Long.valueOf(id)).isPresent()) {
            System.out.println("Try again");
            return enterId();
        }
        return id;
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
    public void close() {
        System.exit(0);
    }
//    private String enterDeveloperId() {
//        System.out.print(" ENTER DEVELOPER-ID \n\uD83D\uDC49 ");
//        String developerId = scanner.next();
//        if (!Validator.validNumber(developerId) | !DeveloperServiceImpl.getInstance().findById(Long.valueOf(developerId)).isPresent()) {
//            System.out.println("Try again");
//            return enterDeveloperId();
//        }
//        return developerId;
//    }
}
