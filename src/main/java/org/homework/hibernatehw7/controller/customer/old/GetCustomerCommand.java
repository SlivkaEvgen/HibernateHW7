//package org.homework.hibernatehw7.controller.customer;
//
//import org.homework.hibernatehw7.config.ScannerConsole;
//import org.homework.hibernatehw7.controller.GetDeleteCommands;
//import org.homework.hibernatehw7.controller.interfaces.Controller;
//import org.homework.hibernatehw7.model.Customer;
//
//import java.util.Scanner;
//
//public class GetCustomerCommand implements Controller {
//
//    private final GetDeleteCommands<Customer, Long> generalEnterMethods = new GetDeleteCommands<>(Customer.class);
//    private final Scanner scanner = ScannerConsole.getInstance();
//    private static GetCustomerCommand getCustomerCommand;
//
//    public static GetCustomerCommand getInstance() {
//        if (getCustomerCommand == null) {
//            getCustomerCommand = new GetCustomerCommand();
//        }
//        return getCustomerCommand;
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
//            generalEnterMethods.getById();
//            start();
//        }
//        if (console.equalsIgnoreCase("BACK")) {
//            CustomerCommandImpl.getInstance().start();
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
