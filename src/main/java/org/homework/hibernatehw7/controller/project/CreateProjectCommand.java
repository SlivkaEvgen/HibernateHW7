package org.homework.hibernatehw7.controller.project;

import org.homework.hibernatehw7.config.ScannerConsole;
import org.homework.hibernatehw7.controller.interfaces.Controller;
import org.homework.hibernatehw7.model.Company;
import org.homework.hibernatehw7.model.Customer;
import org.homework.hibernatehw7.model.Developer;
import org.homework.hibernatehw7.model.Project;
import org.homework.hibernatehw7.services.ProjectServiceImpl;
import org.homework.hibernatehw7.services.ServiceFactory;
import org.homework.hibernatehw7.services.interfaces.ProjectService;
import org.homework.hibernatehw7.services.interfaces.Service;
import org.homework.hibernatehw7.utils.Validator;

import java.util.Scanner;

public class CreateProjectCommand implements Controller {

    private final ProjectService PROJECT_SERVICE =new ProjectServiceImpl();
    private final Scanner scanner = ScannerConsole.getInstance();
    private static CreateProjectCommand createProjectCommand;

    public static CreateProjectCommand getInstance() {
        if (createProjectCommand == null) {
            createProjectCommand = new CreateProjectCommand();
        }
        return createProjectCommand;
    }

    @Override
    public void start() {
        create();
    }

    private void create() {
        final String name = enterName();
        final String cost = enterCost();
        final String companyId = enterCompanyId();
        final String customerId = enterCustomerId();
        final String developerId = enterDeveloperId();
        PROJECT_SERVICE.createNewProject(name, Long.valueOf(cost), Long.valueOf(companyId), Long.valueOf(customerId), Long.valueOf(developerId));
        System.out.println(" ✅ You created \uD83D\uDC49 " + "new Project" + "\n");
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

    private String enterCost() {
        System.out.print(" ENTER COST \n\uD83D\uDC49 ");
        String cost = scanner.next();
        if (!Validator.validNumber(cost)) {
            System.out.println("Try again");
            return enterCost();
        }
        return cost;
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

    private String enterDeveloperId() {
        System.out.print(" ENTER DEVELOPER-ID \n\uD83D\uDC49 ");
        String developerId = scanner.next();
        try {
            if (!Validator.validNumber(developerId) | ServiceFactory.of(Developer.class).findById(Long.valueOf(developerId)).get().getId() == null) {
                System.out.println("Try again");
                return enterDeveloperId();
            }
        } catch (NumberFormatException r) {
            System.out.println("Try again");
            return enterDeveloperId();
        }
        return developerId;
    }

    private String enterCustomerId() {
        System.out.print(" ENTER CUSTOMER-ID \n\uD83D\uDC49 ");
        String customerId = scanner.next();
        try {
            if (!Validator.validNumber(customerId) | ServiceFactory.of(Customer.class).findById(Long.valueOf(customerId)).get().getId() == null) {
                System.out.println("Try again");
                return enterCustomerId();
            }
        } catch (NumberFormatException r) {
            System.out.println("Try again");
            return enterCustomerId();
        }
        return customerId;
    }

    @Override
    public void close() {
        System.exit(0);
    }
}
