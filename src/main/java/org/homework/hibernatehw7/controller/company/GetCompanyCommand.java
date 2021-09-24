package org.homework.hibernatehw7.controller.company;

import org.homework.hibernatehw7.config.ScannerConsole;
import org.homework.hibernatehw7.controller.interfaces.Controller;
import org.homework.hibernatehw7.model.Company;
import org.homework.hibernatehw7.services.CompanyServiceImpl;
import org.homework.hibernatehw7.utils.Validator;

import java.util.Scanner;

public class GetCompanyCommand implements Controller {

    private final CompanyServiceImpl COMPANY_SERVICE = CompanyServiceImpl.getInstance();
    private final Scanner scanner = ScannerConsole.getInstance();
    private static GetCompanyCommand getCompanyCommand;

    public static GetCompanyCommand getInstance() {
        if (getCompanyCommand == null) {
            getCompanyCommand = new GetCompanyCommand();
        }
        return getCompanyCommand;
    }

    @Override
    public void start() {
        System.out.print("\n \uD83D\uDC49 ByID\n \uD83D\uDC49 All\n   \uD83D\uDC49 BACK\n   \uD83D\uDC49 STOP\n\uD83D\uDC49 ");
        final String console = scanner.next();
        if (console.equalsIgnoreCase("ByID")) {
            getById();
            start();
        }
        if (console.equalsIgnoreCase("ALL")) {
            getAll();
            start();
        }
        if (console.equalsIgnoreCase("BACK")) {
            CompanyCommandImpl.getInstance().start();
        }
        if (console.equalsIgnoreCase("STOP")) {
            close();
        } else {
            System.out.print("        ⛔WRONG⛔\n\uD83D\uDCACPlease, enter again \n");
            start();
        }
        start();
    }

    private void getById() {
        System.out.print("\n ENTER ID \n\uD83D\uDC49 ");
        String next = scanner.next();
        if (Validator.validNumber(next)) {
            Company company = COMPANY_SERVICE.getById(Long.valueOf(next)).get();
            if (company.getId() != null) {
                System.out.println(company);
            } else {
                System.out.print("\nNot found, try again ... ");
                getById();
            }
        } else {
            System.out.print("\nNot found, try again ... ");
            getById();
        }
    }

    private void getAll() {
        System.out.println(COMPANY_SERVICE.getAll());
    }

    @Override
    public void close() {
        System.exit(0);
    }
}
