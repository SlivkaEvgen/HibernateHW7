//package org.homework.hibernatehw7.controller.project;
//
//import org.homework.hibernatehw7.config.ScannerConsole;
//import org.homework.hibernatehw7.controller.GetDeleteCommands;
//import org.homework.hibernatehw7.controller.interfaces.Controller;
//import org.homework.hibernatehw7.model.Project;
//
//import java.util.Scanner;
//
//public class GetProjectCommand implements Controller {
//
//    private final Scanner scanner = ScannerConsole.getInstance();
//    private final GetDeleteCommands<Project, Long> generalEnterMethods = new GetDeleteCommands<>(Project.class);
//    private static GetProjectCommand getProjectCommand;
//
//    public static GetProjectCommand getInstance() {
//        if (getProjectCommand == null) {
//            getProjectCommand = new GetProjectCommand();
//        }
//        return getProjectCommand;
//    }
//
//    @Override
//    public void start() {
//        System.out.print("\n \uD83D\uDC49 ById \n \uD83D\uDC49 All \n \uD83D\uDC49 ListProjectsWithDate \n \uD83D\uDC49 BACK \n \uD83D\uDC49 STOP\n\uD83D\uDC49");
//        final String console = scanner.next();
//        if (console.equalsIgnoreCase("ByID")) {
//            generalEnterMethods.getById();
//            start();
//        }
//        if (console.equalsIgnoreCase("ALL")) {
//            generalEnterMethods.getAll();
//            start();
//        }
//        if (console.equalsIgnoreCase("ListProjectsWithDate")) {
//            generalEnterMethods.getListProjectsWithDate();
//            start();
//        }
//        if (console.equalsIgnoreCase("BACK")) {
//            ProjectCommandImpl.getInstance().start();
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
