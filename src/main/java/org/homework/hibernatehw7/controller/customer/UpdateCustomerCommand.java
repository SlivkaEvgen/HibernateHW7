package org.homework.hibernatehw7.controller.customer;

import org.homework.hibernatehw7.config.ScannerConsole;
import org.homework.hibernatehw7.controller.interfaces.Controller;
import org.homework.hibernatehw7.model.Company;
import org.homework.hibernatehw7.model.Customer;
import org.homework.hibernatehw7.model.Project;
import org.homework.hibernatehw7.services.CustomerServiceImpl;
import org.homework.hibernatehw7.services.ServiceFactory;
import org.homework.hibernatehw7.services.interfaces.CustomerService;
import org.homework.hibernatehw7.services.interfaces.Service;
import org.homework.hibernatehw7.utils.Validator;

import java.util.Scanner;

public class UpdateCustomerCommand implements Controller {

    private final CustomerService CUSTOMER_SERVICE = new CustomerServiceImpl();
    private final Scanner scanner = ScannerConsole.getInstance();
    private static UpdateCustomerCommand updateCustomerCommand;

    public static UpdateCustomerCommand getInstance() {
        if (updateCustomerCommand == null) {
            updateCustomerCommand = new UpdateCustomerCommand();
        }
        return updateCustomerCommand;
    }

    @Override
    public void start() {
        update();
    }

    private void update() {
        final String id = enterId();
        final String name = enterName();
        final String city = enterCity();
        final String budget = enterBudget();
        final String companyId = enterCompanyId();
        final String projectId = enterProjectId();
        CUSTOMER_SERVICE.updateCustomer(Long.valueOf(id), name, city, Long.valueOf(budget), Long.valueOf(companyId), Long.valueOf(projectId));
        System.out.println(" ✅ You updated \uD83D\uDC49 " + CUSTOMER_SERVICE.findById(Long.valueOf(id)).get() + "\n");
    }

    private String enterId() {
        System.out.print(" ENTER ID \n\uD83D\uDC49 ");
        String id = scanner.next();
        if (!Validator.validNumber(id) || CUSTOMER_SERVICE.findById(Long.valueOf(id)).get().getId() == null) {
            System.out.println("Try again");
            return enterId();
        }
        return id;
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
            return enterName();
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

    private String enterCompanyId() {
        System.out.print(" ENTER COMPANY-ID \n\uD83D\uDC49 ");
        String companyId = scanner.next();
        try {
            if (!Validator.validNumber(companyId) | ServiceFactory.of(Company.class).findById(Long.valueOf(companyId)).get().getId() == null) {
                System.out.println("Try again");
                return enterCompanyId();
            }
        } catch (NumberFormatException r) {
            System.out.println("Try again");
            return enterCompanyId();
        }
        return companyId;
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
