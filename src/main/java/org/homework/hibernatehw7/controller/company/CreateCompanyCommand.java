package org.homework.hibernatehw7.controller.company;

import org.homework.hibernatehw7.config.ScannerConsole;
import org.homework.hibernatehw7.controller.interfaces.Controller;
import org.homework.hibernatehw7.model.Company;
import org.homework.hibernatehw7.model.Developer;
import org.homework.hibernatehw7.model.Project;
import org.homework.hibernatehw7.services.ServiceFactory;
import org.homework.hibernatehw7.services.interfaces.Service;
import org.homework.hibernatehw7.utils.Validator;

import java.util.Scanner;

public class CreateCompanyCommand implements Controller {

    private final Service<Company, Long> COMPANY_SERVICE = ServiceFactory.of(Company.class);
    private final Scanner scanner = ScannerConsole.getInstance();
    private static CreateCompanyCommand createCompanyCommand;

    public static CreateCompanyCommand getInstance() {
        if (createCompanyCommand == null) {
            createCompanyCommand = new CreateCompanyCommand();
        }
        return createCompanyCommand;
    }

    @Override
    public void start() {
        create();
    }

    private void create() {
        final String name = enterName();
        final String city = enterCity();
        final String projectId = enterProjectId();
        final String developerId = enterDeveloperId();
        COMPANY_SERVICE.createNewCompany(name, city, Long.valueOf(projectId), Long.valueOf(developerId));
        System.out.println(" ✅ You created \uD83D\uDC49  " + "new Company " + " \n");
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

    @Override
    public void close() {
        System.exit(0);
    }
}
