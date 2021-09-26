package org.homework.hibernatehw7.controller.customer;

import org.homework.hibernatehw7.config.ScannerConsole;
import org.homework.hibernatehw7.controller.interfaces.Controller;
import org.homework.hibernatehw7.model.Customer;
import org.homework.hibernatehw7.services.CustomerServiceImpl;
import org.homework.hibernatehw7.services.interfaces.CustomerService;
import org.homework.hibernatehw7.utils.Validator;

import java.util.Scanner;

public class CreateCustomerCommand implements Controller {

    private final CustomerService CUSTOMER_SERVICE = new CustomerServiceImpl();
    private final Scanner scanner = ScannerConsole.getInstance();
    private static CreateCustomerCommand createCustomerCommand;

    public static CreateCustomerCommand getInstance() {
        if (createCustomerCommand == null) {
            createCustomerCommand = new CreateCustomerCommand();
        }
        return createCustomerCommand;
    }

    @Override
    public void start() {
        create();
    }

    private void create() {
        Customer customer = CUSTOMER_SERVICE.createNewCustomer(enterName(), enterCity(), Long.valueOf(enterBudget()));
        System.out.println(" ✅ You created \uD83D\uDC49 " + "new Customer " + customer + "\n");
    }

    private String enterName() {
        System.out.print(" ENTER NAME \n\uD83D\uDC49 ");
        String name = scanner.next();
        if (!Validator.validString(name) | name.length() > 15) {
            System.out.println("Try again");
            return enterName();
        }
        return name;
    }

    private String enterCity() {
        System.out.print(" ENTER CITY \n\uD83D\uDC49 ");
        String city = scanner.next();
        if (!Validator.validString(city) | city.length() > 15) {
            System.out.println("Try again");
            return enterCity();
        }
        return city;
    }

    private String enterBudget() {
        System.out.print(" ENTER BUDGET \n\uD83D\uDC49 ");
        String budget = scanner.next();
        if (!Validator.validNumber(budget) | budget.length() > 10) {
            System.out.println("Try again");
            return enterBudget();
        }
        return budget;
    }

    @Override
    public void close() {
        System.exit(0);
    }
}
