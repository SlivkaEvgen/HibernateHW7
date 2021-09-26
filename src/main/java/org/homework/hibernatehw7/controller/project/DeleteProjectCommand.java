package org.homework.hibernatehw7.controller.project;

import org.homework.hibernatehw7.config.ScannerConsole;
import org.homework.hibernatehw7.controller.interfaces.Controller;
import org.homework.hibernatehw7.services.ProjectServiceImpl;
import org.homework.hibernatehw7.services.interfaces.ProjectService;
import org.homework.hibernatehw7.utils.Validator;

import java.util.Scanner;

public class DeleteProjectCommand implements Controller {

    private final ProjectService PROJECT_SERVICE = new ProjectServiceImpl();
    private final Scanner scanner = ScannerConsole.getInstance();
    private static DeleteProjectCommand deleteProjectCommand;

    public static DeleteProjectCommand getInstance() {
        if (deleteProjectCommand == null) {
            deleteProjectCommand = new DeleteProjectCommand();
        }
        return deleteProjectCommand;
    }

    @Override
    public void start() {
        delete();
    }

    private void delete() {
        System.out.print("\n ENTER ID \n\uD83D\uDC49 ");
        String id = scanner.next();
        if (Validator.validNumber(id)) {
            if (PROJECT_SERVICE.findById(Long.valueOf(id)).isPresent()) {
                System.out.println("\nAre you sure ?  \n" + PROJECT_SERVICE.findById(Long.valueOf(id)).get());
                System.out.print("Yes \uD83D\uDC49 Y\nNo  \uD83D\uDC49 N\n\uD83D\uDC49");
                final String yesNo = scanner.next();
                if (yesNo.equalsIgnoreCase("y")) {
                    PROJECT_SERVICE.delete(Long.valueOf(id));
                    System.out.println(" ✅ You removed the Project number \uD83D\uDC49 " + id + " \n");
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
