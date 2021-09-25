package org.homework.hibernatehw7.controller.company;

import org.homework.hibernatehw7.config.ScannerConsole;
import org.homework.hibernatehw7.controller.interfaces.Controller;
import org.homework.hibernatehw7.model.Company;
import org.homework.hibernatehw7.model.Developer;
import org.homework.hibernatehw7.model.Project;
import org.homework.hibernatehw7.services.CompanyServiceImpl;
import org.homework.hibernatehw7.services.DeveloperServiceImpl;
import org.homework.hibernatehw7.services.ProjectServiceImpl;
import org.homework.hibernatehw7.services.ServiceFactory;
import org.homework.hibernatehw7.services.interfaces.CompanyService;
import org.homework.hibernatehw7.services.interfaces.Service;
import org.homework.hibernatehw7.utils.Validator;

import java.util.Scanner;

public class UpdateCompanyCommand implements Controller {

    private final CompanyService COMPANY_SERVICE = new CompanyServiceImpl();
    private final Scanner scanner = ScannerConsole.getInstance();
    private static UpdateCompanyCommand updateCompanyCommand;

    public static UpdateCompanyCommand getInstance() {
        if (updateCompanyCommand == null) {
            updateCompanyCommand = new UpdateCompanyCommand();
        }
        return updateCompanyCommand;
    }

    @Override
    public void start() {
        update();
    }

    private void update() {
        final String id = enterId();
        final String name = enterName();
        final String city = enterCity();
        final String projectId = enterProjectId();
        final String developerId = enterDeveloperId();
        COMPANY_SERVICE.updateCompany(Long.valueOf(id), name, city, Long.valueOf(projectId), Long.valueOf(developerId));
        System.out.println(" ✅ You updated \uD83D\uDC49 " + COMPANY_SERVICE.findById(Long.valueOf(id)).get() + "\n");
    }

    private String enterId() {
        System.out.print(" ENTER ID \n\uD83D\uDC49 ");
        String id = scanner.next();
        if (!Validator.validNumber(id) || !COMPANY_SERVICE.findById(Long.valueOf(id)).isPresent()) {
            System.out.println("Try again");
            return enterId();
        }
        return id;
    }

    private String enterName() {
        System.out.print(" ENTER NAME \n\uD83D\uDC49 ");
        String name = scanner.next();
        if (!Validator.validString(name)|name.length()>15) {
            System.out.println("Try again");
            return enterName();
        }
        return name;
    }

    private String enterCity() {
        System.out.print(" ENTER CITY \n\uD83D\uDC49 ");
        String city = scanner.next();
        if (!Validator.validString(city)|city.length()>15) {
            System.out.println("Try again");
            return enterCity();
        }
        return city;
    }

    private String enterProjectId() {
        System.out.print(" ENTER PROJECT-ID \n\uD83D\uDC49 ");
        String projectId = scanner.next();
        if (!Validator.validNumber(projectId) || !ProjectServiceImpl.getInstance().findById(Long.valueOf(projectId)).isPresent()) {
            System.out.println("Try again");
            return enterProjectId();
        }
        return projectId;
    }

    private String enterDeveloperId() {
        System.out.print(" ENTER DEVELOPER-ID \n\uD83D\uDC49 ");
        String developerId = scanner.next();
        if (!Validator.validNumber(developerId) || !DeveloperServiceImpl.getInstance().findById(Long.valueOf(developerId)).isPresent()) {
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
