package org.homework.hibernatehw7.controller.company;



import org.homework.hibernatehw7.config.ScannerConsole;
import org.homework.hibernatehw7.model.Company;
import org.homework.hibernatehw7.services.CompanyServiceImpl;
import org.homework.hibernatehw7.utils.Validator;

import java.util.Optional;
import java.util.Scanner;

public class DeleteCompanyCommand extends CompanyCommandImpl {

    private final Scanner scanner = ScannerConsole.getInstance();

    public void delete(String id) {
        final CompanyServiceImpl companyService = new CompanyServiceImpl();
        if (Validator.validNumber(id)) {
            final Optional<Company> serviceById = companyService.getById(Long.valueOf(id));
            if (serviceById.get().getId() != null) {
                System.out.println("\nAre you sure ?  \n" + serviceById);
                System.out.print("Yes \uD83D\uDC49 Y\nNo  \uD83D\uDC49 N\n\uD83D\uDC49 ");
                final String yesNo = scanner.next();
                if (yesNo.equalsIgnoreCase("y")) {
                    companyService.delete(Long.valueOf(id));
                    System.out.println(" ✅ You removed the Company number \uD83D\uDC49 " + id + " \n");
                }
            } else {
                System.out.print("\nNot found, try again ... ");
                deleteCommand();
            }
        } else {
            System.out.print("\nNot found, try again ... ");
            deleteCommand();
        }
    }
}
