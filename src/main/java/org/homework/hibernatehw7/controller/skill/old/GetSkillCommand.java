//package org.homework.hibernatehw7.controller.skill;
//
//import org.homework.hibernatehw7.config.ScannerConsole;
//import org.homework.hibernatehw7.controller.GetDeleteCommands;
//import org.homework.hibernatehw7.controller.interfaces.Controller;
//import org.homework.hibernatehw7.model.Skill;
//
//import java.util.Scanner;
//
//public class GetSkillCommand implements Controller {
//
//    private final GetDeleteCommands<Skill, Long> generalEnterMethods = new GetDeleteCommands<>(Skill.class);
//    private final Scanner scanner = ScannerConsole.getInstance();
//    private static GetSkillCommand getSkillCommand;
//
//    public static GetSkillCommand getInstance() {
//        if (getSkillCommand == null) {
//            getSkillCommand = new GetSkillCommand();
//        }
//        return getSkillCommand;
//    }
//
//    @Override
//    public void start() {
//        System.out.print("\n \uD83D\uDC49 ByID\n \uD83D\uDC49 All\n   \uD83D\uDC49 BACK\n   \uD83D\uDC49 STOP\n\uD83D\uDC49 ");
//        String console = scanner.next();
//        if (console.equalsIgnoreCase("ByID")) {
//            generalEnterMethods.getById();
//            start();
//        }
//        if (console.equalsIgnoreCase("ALL")) {
//            generalEnterMethods.getAll();
//            start();
//        }
//        if (console.equalsIgnoreCase("BACK")) {
//            SkillCommandImpl.getInstance().start();
//        }
//        if (console.equalsIgnoreCase("STOP")) {
//            close();
//        } else {
//            System.out.print("        ⛔WRONG⛔\n\uD83D\uDCACPlease, enter again \n");
//            start();
//        }
//        start();
//    }
//
//    @Override
//    public void close() {
//        System.exit(0);
//    }
//}
