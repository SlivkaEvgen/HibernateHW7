package org.homework.hibernatehw7.controller.customer;

import org.homework.hibernatehw7.config.ScannerConsole;
import org.homework.hibernatehw7.controller.interfaces.Controller;
import org.homework.hibernatehw7.model.Customer;
import org.homework.hibernatehw7.model.Project;
import org.homework.hibernatehw7.services.ServiceFactory;
import org.homework.hibernatehw7.services.interfaces.Service;
import org.homework.hibernatehw7.utils.Validator;

import java.util.Scanner;

public class CreateCustomerCommand implements Controller {

    private final Service<Customer, Long> CUSTOMER_SERVICE = ServiceFactory.of(Customer.class);
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
        final String name = enterName();
        final String city = enterCity();
        final String budget = enterBudget();
        final String projectId = enterProjectId();
        CUSTOMER_SERVICE.createNewCustomer(name, city, Long.valueOf(budget), Long.valueOf(projectId));
        System.out.println(" ✅ You created \uD83D\uDC49 " + "new Customer" + "\n");
    }

    private String enterName() {
        System.out.print(" ENTER NAME \n\uD83D\uDC49 ");
        String name = scanner.next();
        if (!Validator.validString(name)) {
            System.out.println("Try again");
            return enterName();
        }
        return name;
    }

    private String enterCity() {
        System.out.print(" ENTER CITY \n\uD83D\uDC49 ");
        String city = scanner.next();
        if (!Validator.validString(city)) {
            System.out.println("Try again");
            return enterCity();
        }
        return city;
    }

    private String enterBudget() {
        System.out.print(" ENTER BUDGET \n\uD83D\uDC49 ");
        String budget = scanner.next();
        if (!Validator.validNumber(budget)) {
            System.out.println("Try again");
            return enterBudget();
        }
        return budget;
    }

    private String enterProjectId() {
        System.out.print(" ENTER PROJECT-ID \n\uD83D\uDC49 ");
        String projectId = scanner.next();
        try {
            if (!Validator.validNumber(projectId) | ServiceFactory.of(Project.class).findById(Long.valueOf(projectId)).get().getId() == null) {
                System.out.println("Try again");
                return enterProjectId();
            }
        } catch (NumberFormatException r) {
            System.out.println("Try again");
            return enterProjectId();
        }
        return projectId;
    }

    @Override
    public void close() {
        System.exit(0);
    }
}
