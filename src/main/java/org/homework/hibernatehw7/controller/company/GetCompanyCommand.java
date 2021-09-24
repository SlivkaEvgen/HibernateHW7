package org.homework.hibernatehw7.controller.company;


import org.homework.hibernatehw7.config.ScannerConsole;
import org.homework.hibernatehw7.services.CompanyServiceImpl;
import org.homework.hibernatehw7.utils.Validator;

import java.io.Closeable;
import java.io.IOException;
import java.util.Scanner;

public class GetCompanyCommand implements Closeable {

    private final CompanyServiceImpl COMPANY_SERVICE =  CompanyServiceImpl.getInstance();
    private final Scanner scanner = ScannerConsole.getInstance();

    public void all() {
        System.out.println(COMPANY_SERVICE.getAll());
    }

    public void byId() {
        System.out.print("\n ENTER ID \n\uD83D\uDC49 ");
        getById(scanner.next());
    }

    private void getById(String next) {
        if (Validator.validNumber(next)) {
            if (COMPANY_SERVICE.getById(Long.valueOf(next)).get().getId() != null) {
                System.out.println(COMPANY_SERVICE.getById(Long.valueOf(next)));
            } else {
                System.out.print("\nNot found, try again ... ");
                byId();
            }
        } else {
            System.out.print("\nNot found, try again ... ");
            byId();
        }
    }

    @Override
    public void close() throws IOException {
        System.exit(0);
        scanner.close();
    }
}
