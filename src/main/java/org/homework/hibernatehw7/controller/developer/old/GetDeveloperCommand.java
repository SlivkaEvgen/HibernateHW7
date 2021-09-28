//package org.homework.hibernatehw7.controller.developer;
//
//import org.homework.hibernatehw7.config.ScannerConsole;
//import org.homework.hibernatehw7.controller.GetDeleteCommands;
//import org.homework.hibernatehw7.controller.interfaces.Controller;
//import org.homework.hibernatehw7.model.Developer;
//
//import java.util.Scanner;
//
//public class GetDeveloperCommand implements Controller {
//
//    private final GetDeleteCommands<Developer, Long> generalEnterMethods = new GetDeleteCommands<>(Developer.class);
//    private final Scanner scanner = ScannerConsole.getInstance();
//    private static GetDeveloperCommand getDeveloperCommand;
//
//    public static GetDeveloperCommand getInstance() {
//        if (getDeveloperCommand == null) {
//            getDeveloperCommand = new GetDeveloperCommand();
//        }
//        return getDeveloperCommand;
//    }
//
//    @Override
//    public void start() {
//        System.out.print("\n \uD83D\uDC49 ByID\n \uD83D\uDC49 All\n \uD83D\uDC49 ByProjectID\n \uD83D\uDC49 ByActivity\n \uD83D\uDC49 ByLevel\n \uD83D\uDC49 SumSalaries\n   \uD83D\uDC49 BACK\n   \uD83D\uDC49 STOP\n\uD83D\uDC49 ");
//        final String console = scanner.next();
//        if (console.equalsIgnoreCase("ByID")) {
//            generalEnterMethods.getById();
//            start();
//        }
//        if (console.equalsIgnoreCase("ALL")) {
//            generalEnterMethods.getAll();
//            start();
//        }
//        if (console.equalsIgnoreCase("ByProjectID")) {
//            generalEnterMethods.getByProjectID();
//            start();
//        }
//        if (console.equalsIgnoreCase("ByActivity")) {
//            generalEnterMethods.getByActivity();
//            start();
//        }
//        if (console.equalsIgnoreCase("ByLevel")) {
//            generalEnterMethods.getByLevel();
//            start();
//        }
//        if (console.equalsIgnoreCase("SumSalaries")) {
//            generalEnterMethods.getSumSalaries();
//            start();
//        }
//        if (console.equalsIgnoreCase("BACK")) {
//            DeveloperCommandImpl.getInstance().start();
//        }
//        if (console.equalsIgnoreCase("STOP")) {
//            close();
//        } else {
//            System.out.print("        ⛔WRONG⛔\n\uD83D\uDCACPlease, enter again \n\n");
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
