package org.homework.hibernatehw7.controller.company;

import org.homework.hibernatehw7.config.ScannerConsole;
import org.homework.hibernatehw7.controller.interfaces.Controller;
import org.homework.hibernatehw7.model.Company;
import org.homework.hibernatehw7.services.CompanyServiceImpl;
import org.homework.hibernatehw7.utils.Validator;
import java.util.Scanner;

public class DeleteCompanyCommand implements Controller {

    private final Scanner scanner = ScannerConsole.getInstance();
    private static DeleteCompanyCommand deleteCompanyCommand;

    public static DeleteCompanyCommand getInstance() {
        if (deleteCompanyCommand == null) {
            deleteCompanyCommand = new DeleteCompanyCommand();
        }
        return deleteCompanyCommand;
    }

    @Override
    public void start() {
        delete();
    }

    public void delete() {
        System.out.print("\n ENTER ID \n\uD83D\uDC49 ");
        String id = scanner.next();
        final CompanyServiceImpl companyService = CompanyServiceImpl.getInstance();
        if (Validator.validNumber(id)) {
            final Company company = companyService.getById(Long.valueOf(id)).get();
            if (company.getId() != null) {
                System.out.println("\n\n" +
                                   "‼️ All \uD83D\uDED1PROJECTS and \uD83D\uDED1DEVELOPERS  \n" +
                                   "will be ❗DELETED❗ together with the company!\n" +
                                   "‼️ Are you sure you want to delete the company❓❓❓  \n" + company + "\n");
                System.out.print("Yes \uD83D\uDC49 Y\nNo  \uD83D\uDC49 N\n\uD83D\uDC49 ");
                final String yesNo = scanner.next();
                if (yesNo.equalsIgnoreCase("y")) {
                    companyService.delete(Long.valueOf(id));
                    System.out.println(" ✅ You removed the Company number \uD83D\uDC49 " + id + " \n");
                }
            } else {
                System.out.print("\nNot found, try again ... ");
                delete();
            }
        } else {
            System.out.print("\nNot found, try again ... ");
            delete();
        }
    }

    @Override
    public void close() {
        System.exit(0);
    }
}
