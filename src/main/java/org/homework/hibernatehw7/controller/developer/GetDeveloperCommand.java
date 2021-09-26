package org.homework.hibernatehw7.controller.developer;

import org.homework.hibernatehw7.config.ScannerConsole;
import org.homework.hibernatehw7.controller.interfaces.Controller;
import org.homework.hibernatehw7.model.Developer;
import org.homework.hibernatehw7.services.DeveloperServiceImpl;
import org.homework.hibernatehw7.services.interfaces.DeveloperService;
import org.homework.hibernatehw7.utils.Validator;

import java.util.List;
import java.util.Optional;
import java.util.Scanner;

public class GetDeveloperCommand implements Controller {

    private final DeveloperService DEVELOPER_SERVICE = DeveloperServiceImpl.getInstance();
    private final Scanner scanner = ScannerConsole.getInstance();
    private static GetDeveloperCommand getDeveloperCommand;

    public static GetDeveloperCommand getInstance() {
        if (getDeveloperCommand == null) {
            getDeveloperCommand = new GetDeveloperCommand();
        }
        return getDeveloperCommand;
    }

    @Override
    public void start() {
        System.out.print("\n \uD83D\uDC49 ByID\n \uD83D\uDC49 All\n \uD83D\uDC49 ByProjectID\n \uD83D\uDC49 ByActivity\n \uD83D\uDC49 ByLevel\n \uD83D\uDC49 SumSalaries\n   \uD83D\uDC49 BACK\n   \uD83D\uDC49 STOP\n\uD83D\uDC49 ");
        final String console = scanner.next();
        if (console.equalsIgnoreCase("ByID")) {
            getById();
            start();
        }
        if (console.equalsIgnoreCase("ALL")) {
            getAll();
            start();
        }
        if (console.equalsIgnoreCase("ByProjectID")) {
            getByProjectID();
            start();
        }
        if (console.equalsIgnoreCase("ByActivity")) {
            getByActivity();
            start();
        }
        if (console.equalsIgnoreCase("ByLevel")) {
            getByLevel();
            start();
        }
        if (console.equalsIgnoreCase("SumSalaries")) {
            getSumSalaries();
            start();
        }
        if (console.equalsIgnoreCase("BACK")) {
            DeveloperCommandImpl.getInstance().start();
        }
        if (console.equalsIgnoreCase("STOP")) {
            close();
        } else {
            System.out.print("        ⛔WRONG⛔\n\uD83D\uDCACPlease, enter again \n\n");
            start();
        }
        start();
    }

    private void getAll() {
        System.out.println(DEVELOPER_SERVICE.findAll());
    }

    private void getById() {
        System.out.print("\n ENTER ID \n\uD83D\uDC49 ");
        String next = scanner.next();
        if (Validator.validNumber(next)) {
            Optional<Developer> optional = DEVELOPER_SERVICE.findById(Long.valueOf(next));
            if (optional.isPresent()) {
                System.out.println(optional.get());
            } else {
                System.out.print("\nNot found, try again ... ");
                getById();
            }
        } else {
            System.out.print("\nNot found, try again ... ");
            getById();
        }
    }

    private void getByProjectID() {
        System.out.print(" ENTER PROJECT-ID \n\uD83D\uDC49 ");
        String projectId = scanner.next();
        if (Validator.validNumber(projectId)) {
            List<Developer> fromOneProject = DEVELOPER_SERVICE.getDevelopersFromOneProject(Long.valueOf(projectId));
            if (!fromOneProject.isEmpty()) {
                System.out.println(fromOneProject + "\n");
            } else {
                System.out.println("not found, try again ");
                getByProjectID();
            }
        } else {
            System.out.println("not found, try again ");
            getByProjectID();
        }
    }

    private void getByActivity() {
        System.out.print(" ENTER ACTIVITY \n ✅examples : Java, JS, C+, C# \n\uD83D\uDC49 ");
        String activity = scanner.next();
        if (Validator.validString(activity)) {
            if (!activity.equalsIgnoreCase("js")) {
                if (!activity.equalsIgnoreCase("java")) {
                    if (!activity.equalsIgnoreCase("C+")) {
                        if (!activity.equalsIgnoreCase("C#")) {
                            System.out.println("not found, try again ");
                            getByActivity();
                        } else {
                            System.out.println(DEVELOPER_SERVICE.getDevelopersByActivity(activity) + "\n");
                        }
                    } else {
                        System.out.println(DEVELOPER_SERVICE.getDevelopersByActivity(activity) + "\n");
                    }
                } else {
                    System.out.println(DEVELOPER_SERVICE.getDevelopersByActivity(activity) + "\n");
                }
            } else {
                System.out.println(DEVELOPER_SERVICE.getDevelopersByActivity(activity) + "\n");
            }
        } else {
            System.out.println("not found, try again ");
            getByActivity();
        }
    }

    private void getByLevel() {
        System.out.print(" ENTER LEVEL \n ✅examples : Junior, Middle, Senior \n\uD83D\uDC49 ");
        String level = scanner.next();
        if (Validator.validString(level)) {
            if (!level.equalsIgnoreCase("middle")) {
                if (!level.equalsIgnoreCase("senior")) {
                    if (!level.equalsIgnoreCase("junior")) {
                        System.out.println("not found, try again ");
                        getByLevel();
                    } else {
                        System.out.println(DEVELOPER_SERVICE.getDevelopersByLevel(level) + "\n");
                    }
                } else {
                    System.out.println(DEVELOPER_SERVICE.getDevelopersByLevel(level) + "\n");
                }
            } else {
                System.out.println(DEVELOPER_SERVICE.getDevelopersByLevel(level) + "\n");
            }
        } else {
            System.out.print("\n not found, try again \n");
            getByLevel();
        }
    }

    private void getSumSalaries() {
        System.out.print(" ENTER PROJECT-ID \n\uD83D\uDC49 ");
        String next = scanner.next();
        if (Validator.validNumber(next)) {
            try {
                if (!next.isEmpty()) {
                    long nextLong = Long.parseLong(next);
                    if (nextLong != 0) {
                        System.out.print(DEVELOPER_SERVICE.getSumSalariesDevelopersOfOneProject(nextLong) + "\n");
                    } else {
                        System.out.print("\nnot found, try again \n");
                        getSumSalaries();
                    }
                } else {
                    System.out.print("\nnot found, try again \n");
                    getSumSalaries();
                }
            } catch (IndexOutOfBoundsException t) {
                System.out.print("\nnot found, try again \n");
                getSumSalaries();
            }
        } else {
            System.out.print("\nnot found, try again \n");
            getSumSalaries();
        }

    }

    @Override
    public void close() {
        System.exit(0);
    }
}
