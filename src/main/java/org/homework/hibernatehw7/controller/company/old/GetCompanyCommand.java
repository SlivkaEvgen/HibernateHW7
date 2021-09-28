//package org.homework.hibernatehw7.controller.company;
//
//import org.homework.hibernatehw7.config.ScannerConsole;
//import org.homework.hibernatehw7.controller.GetDeleteCommands;
//import org.homework.hibernatehw7.controller.interfaces.Controller;
//import org.homework.hibernatehw7.model.Company;
//
//import java.util.Scanner;
//
//public class GetCompanyCommand implements Controller {
//
//    private final GetDeleteCommands<Company, Long> generalEnterMethods = new GetDeleteCommands<>(Company.class);
//    private final Scanner scanner = ScannerConsole.getInstance();
//    private static GetCompanyCommand getCompanyCommand;
//
//    public static GetCompanyCommand getInstance() {
//        if (getCompanyCommand == null) {
//            getCompanyCommand = new GetCompanyCommand();
//        }
//        return getCompanyCommand;
//    }
//
//    @Override
//    public void start() {
//        System.out.print("\n \uD83D\uDC49 ByID\n \uD83D\uDC49 All\n   \uD83D\uDC49 BACK\n   \uD83D\uDC49 STOP\n\uD83D\uDC49 ");
//        final String console = scanner.next();
//        if (console.equalsIgnoreCase("ByID")) {
//            generalEnterMethods.getById();
//            start();
//        }
//        if (console.equalsIgnoreCase("ALL")) {
//            generalEnterMethods.getAll();
//            start();
//        }
//        if (console.equalsIgnoreCase("BACK")) {
//            CompanyCommandImpl.getInstance().start();
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
