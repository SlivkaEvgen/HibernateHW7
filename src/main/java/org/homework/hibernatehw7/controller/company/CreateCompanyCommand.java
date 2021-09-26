package org.homework.hibernatehw7.controller.company;

import org.homework.hibernatehw7.config.ScannerConsole;
import org.homework.hibernatehw7.controller.interfaces.Controller;
import org.homework.hibernatehw7.model.Company;
import org.homework.hibernatehw7.services.CompanyServiceImpl;
import org.homework.hibernatehw7.services.interfaces.CompanyService;
import org.homework.hibernatehw7.utils.Validator;

import java.util.Scanner;

public class CreateCompanyCommand implements Controller {

    private final CompanyService COMPANY_SERVICE = CompanyServiceImpl.getInstance();
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
        Company company = COMPANY_SERVICE.createNewCompany(enterName(), enterCity());
        System.out.println(" ✅ You created \uD83D\uDC49  " + "new Company " + company + " \n");
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

    @Override
    public void close() {
        System.exit(0);
    }
//    private String enterProjectId() {
//        System.out.print(" ENTER PROJECT-ID \n\uD83D\uDC49 ");
//        String projectId = scanner.next();
//        try {
//            if (!Validator.validNumber(projectId) || !ProjectServiceImpl.getInstance().findById(Long.valueOf(projectId)).isPresent()) {
//                System.out.println("Try again");
//                return enterProjectId();
//            }
//        } catch (NumberFormatException r) {
//            System.out.println("Try again");
//            return enterProjectId();
//        }
//        return projectId;
//    }
//
//    private String enterDeveloperId() {
//        System.out.print(" ENTER DEVELOPER-ID \n\uD83D\uDC49 ");
//        String developerId = scanner.next();
//        try {
//            if (!Validator.validNumber(developerId) || !DeveloperServiceImpl.getInstance().findById(Long.valueOf(developerId)).isPresent()) {
//                System.out.println("Try again");
//                return enterDeveloperId();
//            }
//        } catch (NumberFormatException r) {
//            System.out.println("Try again");
//            return enterDeveloperId();
//        }
//        return developerId;
//    }
}
