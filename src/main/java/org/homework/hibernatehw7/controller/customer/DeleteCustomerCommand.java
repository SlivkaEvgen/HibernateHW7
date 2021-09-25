package org.homework.hibernatehw7.controller.customer;

import org.homework.hibernatehw7.config.ScannerConsole;
import org.homework.hibernatehw7.controller.interfaces.Controller;
import org.homework.hibernatehw7.model.Customer;
import org.homework.hibernatehw7.services.ServiceFactory;
import org.homework.hibernatehw7.services.interfaces.Service;
import org.homework.hibernatehw7.utils.Validator;

import java.util.Scanner;

public class DeleteCustomerCommand implements Controller {

    private final Service<Customer, Long> CUSTOMER_SERVICE = ServiceFactory.of(Customer.class);
    private final Scanner scanner = ScannerConsole.getInstance();
    private static DeleteCustomerCommand deleteCustomerCommand;

    public static DeleteCustomerCommand getInstance() {
        if (deleteCustomerCommand == null) {
            deleteCustomerCommand = new DeleteCustomerCommand();
        }
        return deleteCustomerCommand;
    }

    @Override
    public void start() {
        delete();
    }

    private void delete() {
        System.out.print("\n ENTER ID \n\uD83D\uDC49 ");
        String id = scanner.next();
        if (Validator.validNumber(id)) {
            if (CUSTOMER_SERVICE.findById(Long.valueOf(id)).isPresent()) {
                System.out.println("\n\n" +
                                   "‼️ All \uD83D\uDED1PROJECTS \n" +
                                   "will be ❗DELETED❗ together with the customer!\n" +
                                   "‼️ Are you sure you want to delete the customer❓❓❓  \n"
                                   + CUSTOMER_SERVICE.findById(Long.valueOf(id)).get() + "\n");
                System.out.print("Yes \uD83D\uDC49 Y\nNo  \uD83D\uDC49 N\n\uD83D\uDC49 ");
                final String yesNo = scanner.next();
                if (yesNo.equalsIgnoreCase("y")) {
                    CUSTOMER_SERVICE.delete(Long.valueOf(id));
                    System.out.println(" ✅ You removed the Customer number \uD83D\uDC49 " + id + " \n");
                }
            } else {
                System.out.print("\nNot found, try again ... ");
                delete();
            }
        } else {
            System.out.print("\nNot found, try again ... ");
            delete();
        }
    }

    @Override
    public void close() {
        System.exit(0);
    }
}
