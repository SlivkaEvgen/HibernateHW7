package org.homework.hibernatehw7.controller.company;



import org.homework.hibernatehw7.config.ScannerConsole;
import org.homework.hibernatehw7.model.Company;
import org.homework.hibernatehw7.model.Project;
import org.homework.hibernatehw7.services.CompanyServiceImpl;
import org.homework.hibernatehw7.utils.Validator;

import java.util.Optional;
import java.util.Scanner;
import java.util.Set;

public class DeleteCompanyCommand extends CompanyCommandImpl {

    private final Scanner scanner = ScannerConsole.getInstance();

    public void delete(String id) {
        final CompanyServiceImpl companyService =  CompanyServiceImpl.getInstance();
        if (Validator.validNumber(id)) {
            final Company company = companyService.getById(Long.valueOf(id)).get();
            if (company.getId() != null) {
                System.out.println("\n\n" +
                                   "‼️ All \uD83D\uDED1PROJECTS and \uD83D\uDED1DEVELOPERS  \n" +
                                   "will be ❗DELETED❗ together with the company!\n" +
                                   "‼️ Are you sure you want to delete the company❓❓❓  \n" + company+"\n");
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
