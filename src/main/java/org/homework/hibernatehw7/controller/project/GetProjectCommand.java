package org.homework.hibernatehw7.controller.project;

import org.homework.hibernatehw7.config.ScannerConsole;
import org.homework.hibernatehw7.controller.interfaces.Controller;
import org.homework.hibernatehw7.model.Project;
import org.homework.hibernatehw7.services.ProjectServiceImpl;
import org.homework.hibernatehw7.services.ServiceFactory;
import org.homework.hibernatehw7.services.interfaces.ProjectService;
import org.homework.hibernatehw7.services.interfaces.Service;
import org.homework.hibernatehw7.utils.Validator;

import java.util.Scanner;

public class GetProjectCommand implements Controller {

    private final ProjectService PROJECT_SERVICE = new ProjectServiceImpl();
    private final Scanner scanner = ScannerConsole.getInstance();
    private static GetProjectCommand getProjectCommand;

    public static GetProjectCommand getInstance() {
        if (getProjectCommand == null) {
            getProjectCommand = new GetProjectCommand();
        }
        return getProjectCommand;
    }

    @Override
    public void start() {
        System.out.print("\n \uD83D\uDC49 ById \n \uD83D\uDC49 All \n \uD83D\uDC49 ListProjectsWithDate \n \uD83D\uDC49 BACK \n \uD83D\uDC49 STOP\n\uD83D\uDC49");
        final String console = scanner.next();
        if (console.equalsIgnoreCase("ByID")) {
            getById();
            start();
        }
        if (console.equalsIgnoreCase("ALL")) {
            getAll();
            start();
        }
        if (console.equalsIgnoreCase("ListProjectsWithDate")) {
            getListProjectsWithDate();
            start();
        }
        if (console.equalsIgnoreCase("BACK")) {
            ProjectCommandImpl.getInstance().start();
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
            Project project = PROJECT_SERVICE.findById(Long.valueOf(next)).get();
            if (project.getId() != null) {
                System.out.println(project);
            } else {
                System.out.print("\nNot found, try again ... ");
                getById();
            }
        } else {
            System.out.print("\nNot found, try again ... ");
            getById();
        }
        getById();
    }

    private void getAll() {
        System.out.println(PROJECT_SERVICE.findAll());
    }

    private void getListProjectsWithDate() {
        System.out.println(PROJECT_SERVICE.getListProjectsWithDate() + "\n");
    }

    @Override
    public void close() {
        System.exit(0);
    }
}
