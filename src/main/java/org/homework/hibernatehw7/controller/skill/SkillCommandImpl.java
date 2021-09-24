package org.homework.hibernatehw7.controller.skill;

import org.homework.hibernatehw7.config.ScannerConsole;
import org.homework.hibernatehw7.controller.ControllerImpl;
import org.homework.hibernatehw7.controller.interfaces.Command;

import java.util.InputMismatchException;
import java.util.Scanner;

public class SkillCommandImpl implements Command {

    private final Scanner scanner = ScannerConsole.getInstance();
    private static SkillCommandImpl skillCommand;

    public static SkillCommandImpl getInstance() {
        if (skillCommand == null) {
            skillCommand = new SkillCommandImpl();
        }
        return skillCommand;
    }

    @Override
    public void start() {
        System.out.print(" \n \uD83D\uDC49 GET\n \uD83D\uDC49 CREATE\n \uD83D\uDC49 UPDATE\n \uD83D\uDC49 DELETE \n   \uD83D\uDC49 BACK \n   \uD83D\uDC49 STOP\n\uD83D\uDC49 ");
        String console = scanner.next();
        try {
            if (console.equalsIgnoreCase("GET")) {
                getCommand();
                start();
            }
            if (console.equalsIgnoreCase("CREATE")) {
                createCommand();
                start();
            }
            if (console.equalsIgnoreCase("UPDATE")) {
                updateCommand();
                start();
            }
            if (console.equalsIgnoreCase("DELETE")) {
                deleteCommand();
                start();
            }
            if (console.equalsIgnoreCase("BACK")) {
                new ControllerImpl().start();
            }
            if (console.equalsIgnoreCase("STOP")) {
                close();
            } else {
                System.out.print("        ⛔WRONG⛔\n\uD83D\uDCACPlease, enter again \n");
                start();
            }
        } catch (InputMismatchException s) {
            System.out.print("        ⛔WRONG⛔\n\uD83D\uDCACPlease, enter again \n");
            start();
        }
        start();
    }

    @Override
    public void getCommand() {
        GetSkillCommand.getInstance().start();
    }

    @Override
    public void createCommand() {
        CreateCommandSkill.getInstance().start();
    }

    @Override
    public void updateCommand() {
        UpdateSkillCommand.getInstance().start();
    }

    @Override
    public void deleteCommand() {
        DeleteSkillCommand.getInstance().start();
    }

    @Override
    public void close() {
        System.exit(0);
    }
}
