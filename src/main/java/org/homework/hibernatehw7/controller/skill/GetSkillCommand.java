package org.homework.hibernatehw7.controller.skill;

import org.homework.hibernatehw7.config.ScannerConsole;
import org.homework.hibernatehw7.controller.interfaces.Controller;
import org.homework.hibernatehw7.services.SkillServiceImpl;
import org.homework.hibernatehw7.utils.Validator;

import java.util.Scanner;

public class GetSkillCommand implements Controller {

    private final SkillServiceImpl SKILL_SERVICE = SkillServiceImpl.getInstance();
    private final Scanner scanner = ScannerConsole.getInstance();
    private static GetSkillCommand getSkillCommand;

    public static GetSkillCommand getInstance() {
        if (getSkillCommand == null) {
            getSkillCommand = new GetSkillCommand();
        }
        return getSkillCommand;
    }

    @Override
    public void start() {
        System.out.print("\n \uD83D\uDC49 ByID\n \uD83D\uDC49 All\n   \uD83D\uDC49 BACK\n   \uD83D\uDC49 STOP\n\uD83D\uDC49 ");
        String console = scanner.next();
        if (console.equalsIgnoreCase("ByID")) {
            getById();
            start();
        }
        if (console.equalsIgnoreCase("ALL")) {
            getAll();
            start();
        }
        if (console.equalsIgnoreCase("BACK")) {
            SkillCommandImpl.getInstance().start();
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
            if (SKILL_SERVICE.getById(Long.valueOf(next)).get().getId() != null) {
                System.out.println(SKILL_SERVICE.getById(Long.valueOf(next)));
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
        System.out.println(SKILL_SERVICE.getAll());
    }

    @Override
    public void close() {
        System.exit(0);
    }
}
