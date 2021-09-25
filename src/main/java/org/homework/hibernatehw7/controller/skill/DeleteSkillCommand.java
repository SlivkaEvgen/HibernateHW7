package org.homework.hibernatehw7.controller.skill;

import org.homework.hibernatehw7.config.ScannerConsole;
import org.homework.hibernatehw7.controller.interfaces.Controller;
import org.homework.hibernatehw7.model.Skill;
import org.homework.hibernatehw7.services.ServiceFactory;
import org.homework.hibernatehw7.services.SkillServiceImpl;
import org.homework.hibernatehw7.services.interfaces.Service;
import org.homework.hibernatehw7.services.interfaces.SkillService;
import org.homework.hibernatehw7.utils.Validator;

import java.util.Scanner;

public class DeleteSkillCommand implements Controller {

    private final SkillService SKILL_SERVICE = new SkillServiceImpl();
    private final Scanner scanner = ScannerConsole.getInstance();
    private static DeleteSkillCommand deleteSkillCommand;

    public static DeleteSkillCommand getInstance() {
        if (deleteSkillCommand == null) {
            deleteSkillCommand = new DeleteSkillCommand();
        }
        return deleteSkillCommand;
    }

    @Override
    public void start() {
        delete();
    }

    private void delete() {
        System.out.print("\n ENTER ID \n\uD83D\uDC49 ");
        String id = scanner.next();
        if (Validator.validNumber(id)) {
            if (SKILL_SERVICE.findById(Long.valueOf(id)).isPresent()) {
                System.out.println("\nAre you sure ?  \n" + SKILL_SERVICE.findById(Long.valueOf(id)).get());
                System.out.print("Yes \uD83D\uDC49 Y\nNo  \uD83D\uDC49 N\n\uD83D\uDC49");
                String yesNo = scanner.next();
                if (yesNo.equalsIgnoreCase("y")) {
                    SKILL_SERVICE.delete(Long.valueOf(id));
                    System.out.println(" ✅ You removed the Skill number \uD83D\uDC49 " + id + " \n");
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
