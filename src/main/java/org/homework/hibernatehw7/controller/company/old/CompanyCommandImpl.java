//package org.homework.hibernatehw7.controller.company;
//
//import org.homework.hibernatehw7.config.ScannerConsole;
//import org.homework.hibernatehw7.controller.interfaces.Command;
//import java.util.InputMismatchException;
//import java.util.Scanner;
//
//public class CompanyCommandImpl implements Command {
//
//    private final Scanner scanner = ScannerConsole.getInstance();
//    private static CompanyCommandImpl companyCommand;
//
//    public static CompanyCommandImpl getInstance() {
//        if (companyCommand == null) {
//            companyCommand = new CompanyCommandImpl();
//        }
//        return companyCommand;
//    }
//
//    @Override
//    public void start() {
//        System.out.print("\n \uD83D\uDC49 GET\n \uD83D\uDC49 CREATE\n \uD83D\uDC49 UPDATE\n \uD83D\uDC49 DELETE \n   \uD83D\uDC49 BACK \n   \uD83D\uDC49 STOP\n\uD83D\uDC49 ");
//        final String console = this.scanner.next();
//        try {
//            if (console.equalsIgnoreCase("GET")) {
//                getCommand();
//                start();
//            }
//            if (console.equalsIgnoreCase("CREATE")) {
//                createCommand();
//                start();
//            }
//            if (console.equalsIgnoreCase("UPDATE")) {
//                updateCommand();
//                start();
//            }
//            if (console.equalsIgnoreCase("DELETE")) {
//                deleteCommand();
//                start();
//            }
//            if (console.equalsIgnoreCase("BACK")) {
//               // new ControllerImpl<>().start();
//            }
//            if (console.equalsIgnoreCase("STOP")) {
//                close();
//            } else {
//                System.out.print("        ⛔WRONG⛔\n\uD83D\uDCACPlease, enter again \n");
//                start();
//            }
//        } catch (InputMismatchException s) {
//            System.out.print("        ⛔WRONG⛔\n\uD83D\uDCACPlease, enter again \n");
//            start();
//        }
//        start();
//    }
//
//    @Override
//    public void getCommand() {
//        GetCompanyCommand.getInstance().start();
//    }
//
//    @Override
//    public void createCommand() {
//        CreateCompanyCommand.getInstance().start();
//    }
//
//    @Override
//    public void updateCommand() {
//        UpdateCompanyCommand.getInstance().start();
//    }
//
//    @Override
//    public void deleteCommand() {
//        DeleteCompanyCommand.getInstance().start();
//    }
//
//    @Override
//    public void close() {
//        System.exit(0);
//    }
//}
