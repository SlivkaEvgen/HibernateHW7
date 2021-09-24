package org.homework.hibernatehw7.controller.company;



import org.homework.hibernatehw7.config.ScannerConsole;
import org.homework.hibernatehw7.controller.interfaces.Controller;
import org.homework.hibernatehw7.services.CompanyServiceImpl;
import org.homework.hibernatehw7.services.CustomerServiceImpl;
import org.homework.hibernatehw7.services.DeveloperServiceImpl;
import org.homework.hibernatehw7.services.ProjectServiceImpl;
import org.homework.hibernatehw7.utils.Validator;

import java.util.Scanner;

public class UpdateCompanyCommand implements Controller {

    private final CompanyServiceImpl COMPANY_SERVICE =  CompanyServiceImpl.getInstance();
    private final Scanner scanner = ScannerConsole.getInstance();

    private void update() {
        final String id = enterId();
        final String name = enterName();
        final String city = enterCity();
        final String projectId = enterProjectId();
        final String developerId = enterDeveloperId();
//        final String customerId = enterCustomerId();
        COMPANY_SERVICE.update(Long.valueOf(id), name, city,Long.valueOf(projectId),Long.valueOf(developerId));
        System.out.println(" ✅ You updated \uD83D\uDC49 " + COMPANY_SERVICE.getById(Long.valueOf(id)).get() + "\n");
    }

    private String enterId() {
        System.out.print(" ENTER ID \n\uD83D\uDC49 ");
        String id = scanner.next();
        if (!Validator.validNumber(id) || COMPANY_SERVICE.getById(Long.valueOf(id)).get().getId() == null) {
            System.out.println("Try again");
            return enterId();
        }
        return id;
    }

    private String enterProjectId() {
        System.out.print(" ENTER PROJECT-ID \n\uD83D\uDC49 ");
        String projectId = scanner.next();
        if (!Validator.validNumber(projectId) ||  ProjectServiceImpl.getInstance().getById(Long.valueOf(projectId)).get().getId() == null) {
            System.out.println("Try again");
            return enterProjectId();
        }
        return projectId;
    }

    private String enterDeveloperId() {
        System.out.print(" ENTER DEVELOPER-ID \n\uD83D\uDC49 ");
        String developerId = scanner.next();
        if (!Validator.validNumber(developerId) ||  DeveloperServiceImpl.getInstance().getById(Long.valueOf(developerId)).get().getId() == null) {
            System.out.println("Try again");
            return enterDeveloperId();
        }
        return developerId;
    }
//    private String enterCustomerId() {
//        System.out.print(" ENTER CUSTOMER-ID \n\uD83D\uDC49 ");
//        String customerId = scanner.next();
//        if (!Validator.validNumber(customerId) || new CustomerServiceImpl().getById(Long.valueOf(customerId)).get().getId() == null) {
//            System.out.println("Try again");
//            return enterCustomerId();
//        }
//        return customerId;
//    }

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

    @Override
    public void start() {
        update();
    }

    @Override
    public void close() {
        System.exit(0);
        scanner.close();
    }
}
