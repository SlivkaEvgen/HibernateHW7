package org.homework.hibernatehw7.controller.developer;



import org.homework.hibernatehw7.config.ScannerConsole;
import org.homework.hibernatehw7.model.Developer;
import org.homework.hibernatehw7.services.DeveloperServiceImpl;
import org.homework.hibernatehw7.utils.Validator;

import java.util.Optional;
import java.util.Scanner;

public class DeleteDeveloperCommand extends DeveloperCommandImpl {

    private final Scanner scanner = ScannerConsole.getInstance();

    public void delete(String id) {
        final DeveloperServiceImpl developerService =  DeveloperServiceImpl.getInstance();
        if (Validator.validNumber(id)) {
            final Optional<Developer> serviceById = developerService.getById(Long.valueOf(id));
            if (serviceById.get().getId() != null) {
                System.out.println("\nAre you sure ?  \n" + serviceById);
                System.out.print("Yes \uD83D\uDC49 Y\nNo  \uD83D\uDC49 N\n\uD83D\uDC49 ");
                final String yesNo = scanner.next();
                if (yesNo.equalsIgnoreCase("y")) {
                    developerService.delete(Long.valueOf(id));
                    System.out.println(" ✅ You removed the Developer number \uD83D\uDC49 " + id + " \n");
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
