//package org.homework.hibernatehw7.controller;
//
//import org.homework.hibernatehw7.config.ScannerConsole;
//import org.homework.hibernatehw7.controller.company.CompanyCommandImpl;
//import org.homework.hibernatehw7.controller.customer.CustomerCommandImpl;
//import org.homework.hibernatehw7.controller.developer.DeveloperCommandImpl;
//import org.homework.hibernatehw7.controller.interfaces.Controller;
//import org.homework.hibernatehw7.controller.project.ProjectCommandImpl;
//import org.homework.hibernatehw7.controller.skill.SkillCommandImpl;
//import org.homework.hibernatehw7.model.*;
//import java.util.Scanner;
//
//public class ControllerImpl<T extends BaseModel<ID>, ID> implements Controller {
//
//    private final Scanner scanner = ScannerConsole.getInstance();
//
//    @Override
//    public void start() {
//        System.out.print("\n \uD83D\uDC49 DEVELOPER\n \uD83D\uDC49 PROJECT\n \uD83D\uDC49 COMPANY\n \uD83D\uDC49 CUSTOMER\n \uD83D\uDC49 SKILL\n   \uD83D\uDC49 BACK\n   \uD83D\uDC49 STOP\n\uD83D\uDC49 ");
//        final String controller = scanner.next();
//        if (controller.equalsIgnoreCase("DEVELOPER")) {
//            DeveloperCommandImpl.getInstance().start();
//            start();
//        }
//        if (controller.equalsIgnoreCase("PROJECT")) {
//            ProjectCommandImpl.getInstance().start();
//            start();
//        }
//        if (controller.equalsIgnoreCase("COMPANY")) {
//            CompanyCommandImpl.getInstance().start();
//            start();
//        }
//        if (controller.equalsIgnoreCase("CUSTOMER")) {
//            CustomerCommandImpl.getInstance().start();
//            start();
//        }
//        if (controller.equalsIgnoreCase("SKILL")) {
//            SkillCommandImpl.getInstance().start();
//            start();
//        }
//        if (controller.equalsIgnoreCase("BACK")) {
//            new CommandImpl<>().start();
//        }
//        if (controller.equalsIgnoreCase("STOP")) {
//            close();
//        } else {
//            System.out.print("        ⛔WRONG⛔\n\uD83D\uDCACPlease, enter again \n");
//            start();
//        }
//        start();
//    }
//
//    @Override
//    public void close() {
//        System.exit(0);
//    }
//}
