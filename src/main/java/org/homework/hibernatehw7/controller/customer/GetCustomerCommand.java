package org.homework.hibernatehw7.controller.customer;

import org.homework.hibernatehw7.config.ScannerConsole;
import org.homework.hibernatehw7.controller.interfaces.Controller;
import org.homework.hibernatehw7.model.Customer;
import org.homework.hibernatehw7.services.ServiceFactory;
import org.homework.hibernatehw7.services.interfaces.Service;
import org.homework.hibernatehw7.utils.Validator;

import java.util.Scanner;

public class GetCustomerCommand implements Controller {

    private final Service<Customer, Long> CUSTOMER_SERVICE = ServiceFactory.of(Customer.class);
    private final Scanner scanner = ScannerConsole.getInstance();
    private static GetCustomerCommand getCustomerCommand;

    public static GetCustomerCommand getInstance() {
        if (getCustomerCommand == null) {
            getCustomerCommand = new GetCustomerCommand();
        }
        return getCustomerCommand;
    }

    @Override
    public void start() {
        System.out.print("\n \uD83D\uDC49 ByID\n \uD83D\uDC49 All\n   \uD83D\uDC49 BACK\n   \uD83D\uDC49 STOP\n\uD83D\uDC49 ");
        final String console = scanner.next();
        if (console.equalsIgnoreCase("ByID")) {
            getById();
            start();
        }
        if (console.equalsIgnoreCase("ALL")) {
            getAll();
            start();
        }
        if (console.equalsIgnoreCase("BACK")) {
            CustomerCommandImpl.getInstance().start();
        }
        if (console.equalsIgnoreCase("STOP")) {
            close();
        } else {
            System.out.print("        ⛔WRONG⛔\n\uD83D\uDCACPlease, enter again \n");
            start();
        }
        start();
    }

    private void getById() {
        System.out.print("\n ENTER ID \n\uD83D\uDC49 ");
        String next = scanner.next();
        if (Validator.validNumber(next)) {
            Customer customer = CUSTOMER_SERVICE.findById(Long.valueOf(next)).get();
            if (customer.getId() != null) {
                System.out.println(customer);
            } else {
                System.out.print("\nNot found, try again ... ");
                getById();
            }
        } else {
            System.out.print("\nNot found, try again ... ");
            getById();
        }
    }

    private void getAll() {
        System.out.println(CUSTOMER_SERVICE.findAll());
    }

    @Override
    public void close() {
        System.exit(0);
    }
}
