package org.homework.hibernatehw7.controller.project;

import org.homework.hibernatehw7.config.ScannerConsole;
import org.homework.hibernatehw7.controller.interfaces.Controller;
import org.homework.hibernatehw7.model.Company;
import org.homework.hibernatehw7.model.Customer;
import org.homework.hibernatehw7.model.Developer;
import org.homework.hibernatehw7.model.Project;
import org.homework.hibernatehw7.services.*;
import org.homework.hibernatehw7.services.interfaces.ProjectService;
import org.homework.hibernatehw7.services.interfaces.Service;
import org.homework.hibernatehw7.utils.Validator;

import java.util.Scanner;

public class UpdateProjectCommand implements Controller {

    private final ProjectService PROJECT_SERVICE = new ProjectServiceImpl();
    private final Scanner scanner = ScannerConsole.getInstance();
    private static UpdateProjectCommand updateProjectCommand;

    public static UpdateProjectCommand getInstance() {
        if (updateProjectCommand == null) {
            updateProjectCommand = new UpdateProjectCommand();
        }
        return updateProjectCommand;
    }

    @Override
    public void start() {
        update();
    }

    private void update() {
        final String id = enterId();
        final String name = enterName();
        final String cost = enterCost();
        final String companyId = enterCompanyId();
        final String customerId = enterCustomerId();
        final String developerId = enterDeveloperId();
        PROJECT_SERVICE.updateProject(Long.valueOf(id), name, Long.valueOf(cost), Long.valueOf(companyId), Long.valueOf(customerId), Long.valueOf(developerId));
        System.out.println(" ✅ You updated \uD83D\uDC49 " + PROJECT_SERVICE.findById(Long.valueOf(id)).get() + "\n");
    }

    private String enterId() {
        System.out.print(" ENTER ID \n\uD83D\uDC49 ");
        String id = scanner.next();
        Project project = PROJECT_SERVICE.findById(Long.valueOf(id)).get();
        if (!Validator.validNumber(id) || project.getId()==null) {
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
            if (!Validator.validNumber(companyId) || !CompanyServiceImpl.getInstance().findById(Long.valueOf(companyId)).isPresent()) {
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
            if (!Validator.validNumber(developerId) || !DeveloperServiceImpl.getInstance().findById(Long.valueOf(developerId)).isPresent()) {
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
            if (!Validator.validNumber(customerId) || !CustomerServiceImpl.getInstance().findById(Long.valueOf(customerId)).isPresent()) {
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
