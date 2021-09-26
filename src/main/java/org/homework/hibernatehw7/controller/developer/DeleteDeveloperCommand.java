package org.homework.hibernatehw7.controller.developer;

import org.homework.hibernatehw7.config.ScannerConsole;
import org.homework.hibernatehw7.controller.interfaces.Controller;
import org.homework.hibernatehw7.services.DeveloperServiceImpl;
import org.homework.hibernatehw7.services.interfaces.DeveloperService;
import org.homework.hibernatehw7.utils.Validator;

import java.util.Scanner;

public class DeleteDeveloperCommand implements Controller {

    private final DeveloperService DEVELOPER_SERVICE = DeveloperServiceImpl.getInstance();
    private final Scanner scanner = ScannerConsole.getInstance();
    private static DeleteDeveloperCommand deleteDeveloperCommand;

    public static DeleteDeveloperCommand getInstance() {
        if (deleteDeveloperCommand == null) {
            deleteDeveloperCommand = new DeleteDeveloperCommand();
        }
        return deleteDeveloperCommand;
    }

    @Override
    public void start() {
        delete();
    }

    private void delete() {
        System.out.print("\n ENTER ID \n\uD83D\uDC49 ");
        String id = scanner.next();
        if (Validator.validNumber(id)) {
            if (DEVELOPER_SERVICE.findById(Long.valueOf(id)).isPresent()) {
                System.out.println("\nAre you sure ?  \n" + DEVELOPER_SERVICE.findById(Long.valueOf(id)).get());
                System.out.print("Yes \uD83D\uDC49 Y\nNo  \uD83D\uDC49 N\n\uD83D\uDC49 ");
                final String yesNo = scanner.next();
                if (yesNo.equalsIgnoreCase("y")) {
                    DEVELOPER_SERVICE.delete(Long.valueOf(id));
                    System.out.println(" ✅ You removed the Developer number \uD83D\uDC49 " + id + " \n");
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