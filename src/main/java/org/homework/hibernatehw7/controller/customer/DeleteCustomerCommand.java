package org.homework.hibernatehw7.controller.customer;


import org.homework.hibernatehw7.config.ScannerConsole;
import org.homework.hibernatehw7.model.Customer;
import org.homework.hibernatehw7.services.CustomerServiceImpl;
import org.homework.hibernatehw7.utils.Validator;

import java.util.Optional;
import java.util.Scanner;

public class DeleteCustomerCommand extends CustomerCommandImpl {

    private final Scanner scanner = ScannerConsole.getInstance();

    public void delete(String id) {
        final CustomerServiceImpl customerService =  CustomerServiceImpl.getInstance();
        if (Validator.validNumber(id)) {
            final Customer customer = customerService.getById(Long.valueOf(id)).get();
            if (customer.getId() != null) {
                System.out.println("\n\n" +
                                   "‼️ All \uD83D\uDED1PROJECTS \n" +
                                   "will be ❗DELETED❗ together with the customer!\n" +
                                   "‼️ Are you sure you want to delete the customer❓❓❓  \n" + customer+"\n");
                System.out.print("Yes \uD83D\uDC49 Y\nNo  \uD83D\uDC49 N\n\uD83D\uDC49 ");
                final String yesNo = scanner.next();
                if (yesNo.equalsIgnoreCase("y")) {
                    customerService.delete(Long.valueOf(id));
                    System.out.println(" ✅ You removed the Customer number \uD83D\uDC49 " + id + " \n");
                }
            } else {
                System.out.print("\nNot found, try again ... ");
                deleteCommand();
            }
        } else {
            System.out.print("\nNot found, try again ... ");
            deleteCommand();
        }
    }
}
