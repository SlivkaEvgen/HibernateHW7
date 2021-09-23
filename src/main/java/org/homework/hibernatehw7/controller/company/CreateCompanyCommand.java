package org.homework.hibernatehw7.controller.company;



import org.homework.hibernatehw7.config.ScannerConsole;
import org.homework.hibernatehw7.controller.interfaces.Controller;
import org.homework.hibernatehw7.services.CompanyServiceImpl;
import org.homework.hibernatehw7.services.CustomerServiceImpl;
import org.homework.hibernatehw7.services.DeveloperServiceImpl;
import org.homework.hibernatehw7.services.ProjectServiceImpl;
import org.homework.hibernatehw7.utils.Validator;

import java.util.Scanner;

public class CreateCompanyCommand implements Controller {

    private final Scanner scanner = ScannerConsole.getInstance();

    private void create() {
        final String name = enterName();
        final String city = enterCity();
//        final String projectId = enterProjectId();
//        final String customerId = enterCustomerId();
        final String developerId = enterDeveloperId();
        new CompanyServiceImpl().createNewCompany(name, city,Long.valueOf(developerId));//Long.valueOf(projectId)
        System.out.println(" ✅ You created \uD83D\uDC49  " + "new Company " + " \n");
    }

//    private String enterProjectId() {
// System.out.print(" ENTER PROJECT-ID \n\uD83D\uDC49 ");
//    String projectId = scanner.next();
//    try {
//        if (!Validator.validNumber(projectId) | new ProjectServiceImpl().getById(Long.valueOf(projectId)).get().getId() == null) {
//            System.out.println("Try again");
//            return enterProjectId();
//        }
//    } catch (NumberFormatException r) {
//        System.out.println("Try again");
//        return enterProjectId();
//    }
//    return projectId;
//    }

        private String enterDeveloperId() {
 System.out.print(" ENTER DEVELOPER-ID \n\uD83D\uDC49 ");
    String developerId = scanner.next();
    try {
        if (!Validator.validNumber(developerId) | new DeveloperServiceImpl().getById(Long.valueOf(developerId)).get().getId() == null) {
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
        if (!Validator.validNumber(customerId) | new CustomerServiceImpl().getById(Long.valueOf(customerId)).get().getId() == null) {
            System.out.println("Try again");
            return enterCustomerId();
        }
    } catch (NumberFormatException r) {
        System.out.println("Try again");
        return enterCustomerId();
    }
    return customerId;
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

    @Override
    public void start() {
        create();
    }

    @Override
    public void close() {
        System.exit(0);
        scanner.close();
    }
}
