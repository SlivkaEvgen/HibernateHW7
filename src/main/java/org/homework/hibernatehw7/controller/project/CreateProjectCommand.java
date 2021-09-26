package org.homework.hibernatehw7.controller.project;

import org.homework.hibernatehw7.config.ScannerConsole;
import org.homework.hibernatehw7.controller.interfaces.Controller;
import org.homework.hibernatehw7.model.Project;
import org.homework.hibernatehw7.services.CompanyServiceImpl;
import org.homework.hibernatehw7.services.CustomerServiceImpl;
import org.homework.hibernatehw7.services.ProjectServiceImpl;
import org.homework.hibernatehw7.services.interfaces.ProjectService;
import org.homework.hibernatehw7.utils.Validator;

import java.util.Scanner;

public class CreateProjectCommand implements Controller {

    private final ProjectService PROJECT_SERVICE = ProjectServiceImpl.getInstance();
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
        Project project = PROJECT_SERVICE.createNewProject(enterName(), Long.valueOf(enterCost()), Long.valueOf(enterCompanyId()),
                Long.valueOf(enterCustomerId()));
        System.out.println(" ✅ You created \uD83D\uDC49 " + "new Project " + project + "\n");
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

    private String enterCost() {
        System.out.print(" ENTER COST \n\uD83D\uDC49 ");
        String cost = scanner.next();
        if (!Validator.validNumber(cost) | cost.length() > 10) {
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
