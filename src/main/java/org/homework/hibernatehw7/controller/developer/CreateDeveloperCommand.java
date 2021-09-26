package org.homework.hibernatehw7.controller.developer;

import org.homework.hibernatehw7.config.ScannerConsole;
import org.homework.hibernatehw7.controller.interfaces.Controller;
import org.homework.hibernatehw7.model.Skill;
import org.homework.hibernatehw7.services.CompanyServiceImpl;
import org.homework.hibernatehw7.services.DeveloperServiceImpl;
import org.homework.hibernatehw7.services.ProjectServiceImpl;
import org.homework.hibernatehw7.services.SkillServiceImpl;
import org.homework.hibernatehw7.services.interfaces.DeveloperService;
import org.homework.hibernatehw7.utils.Validator;

import java.util.HashSet;
import java.util.Optional;
import java.util.Scanner;
import java.util.Set;

public class CreateDeveloperCommand implements Controller {

    private final DeveloperService DEVELOPER_SERVICE = new DeveloperServiceImpl();
    private final Scanner scanner = ScannerConsole.getInstance();
    private static CreateDeveloperCommand createDeveloperCommand;
    private final SkillServiceImpl skillService = SkillServiceImpl.getInstance();

    public static CreateDeveloperCommand getInstance() {
        if (createDeveloperCommand == null) {
            createDeveloperCommand = new CreateDeveloperCommand();
        }
        return createDeveloperCommand;
    }

    @Override
    public void start() {
        create();
    }

    private void create() {
        final String name = enterName();
        final String age = enterAge();
        final String gender = enterGender();
        final String email = enterEmail();
        final String salary = enterSalary();
        final Set<Skill> skillSet = (Set<Skill>) addSetSkills();
        final String companyId = enterCompanyId();
        final String projectId = enterProjectId();
        DEVELOPER_SERVICE.createNewDeveloper(name, Long.valueOf(age), gender, email, Long.valueOf(salary), skillSet, Long.valueOf(companyId), Long.valueOf(projectId));
        System.out.println(" ✅ You create \uD83D\uDC49   new Developer  \n");
    }

    private String enterName() {
        System.out.print(" ENTER NAME \n\uD83D\uDC49 ");
        String name = scanner.next();
        if (!Validator.validString(name) | name.length() > 15) {
            System.out.println("Try again");
            return enterName();
        }
        return name;
    }

    private String enterAge() {
        System.out.print(" ENTER AGE \n\uD83D\uDC49 ");
        String age = scanner.next();
        if (!Validator.validNumber(age) | age.length() > 3) {
            System.out.println("Try again");
            return enterAge();
        }
        return age;
    }

    private String enterSalary() {
        System.out.print(" ENTER SALARY \n\uD83D\uDC49 ");
        String salary = scanner.next();
        if (!Validator.validNumber(salary) | salary.length() > 8) {
            System.out.println("Try again");
            return enterSalary();
        }
        return salary;
    }

    private String enterGender() {
        System.out.print(" ENTER GENDER \n\uD83D\uDC49 ");
        System.out.print(" Examples  \uD83D\uDC49  Male, Female\n");
        String gender = scanner.next();
        if (!Validator.validString(gender) | !gender.equalsIgnoreCase("male") & !gender.equalsIgnoreCase("female")) {
            System.out.println("Try again");
            return enterGender();
        }
        return gender;
    }

    private String enterEmail() {
        System.out.print(" ENTER EMAIL \n\uD83D\uDC49 ");
        String email = scanner.next();
        if (!email.contains("@") | email.length() > 13) {
            System.out.println("Try again");
            return enterEmail();
        }
        return email;
    }

    private String enterCompanyId() {
        System.out.print(" ENTER COMPANY-ID \n\uD83D\uDC49 ");
        String companyId = scanner.next();
        try {
            if (!Validator.validNumber(companyId) || !CompanyServiceImpl.getInstance().findById(Long.valueOf(companyId)).isPresent()) {
                System.out.println("Try again");
                return enterCompanyId();
            }
        } catch (NumberFormatException r) {
            System.out.println("Try again");
            return enterCompanyId();
        }
        return companyId;
    }

    private String enterProjectId() {
        System.out.print(" ENTER Project-ID \n\uD83D\uDC49 ");
        String projectId = scanner.next();
        try {
            if (!Validator.validNumber(projectId) || !ProjectServiceImpl.getInstance().findById(Long.valueOf(projectId)).isPresent()) {
                System.out.println("Try again");
                return enterProjectId();
            }
        } catch (NumberFormatException r) {
            System.out.println("Try again");
            return enterProjectId();
        }
        return projectId;
    }

    private Skill enterSkillId() {
        System.out.print(" ENTER Skill-ID \n\uD83D\uDC49 ");
        String skillId = scanner.next();
        try {
            if (!Validator.validNumber(skillId) | !skillService.findById(Long.valueOf(skillId)).isPresent()) {
                System.out.println("Try again");
                return enterSkillId();
            }
        } catch (NumberFormatException r) {
            System.out.println("Try again");
            return enterSkillId();
        }
        return skillService.findById(Long.valueOf(skillId)).get();
    }

    private Skill addSetSkills() {
        System.out.print(" ENTER Skill-ID \n\uD83D\uDC49 ");
        String skillId = scanner.next();
//        try {
//            if (!Validator.validNumber(skillId) | !skillService.findById(Long.valueOf(skillId)).isPresent()) {
//                System.out.println("Try again");
//                return enterSkillId();
//            }
//        } catch (NumberFormatException r) {
//            System.out.println("Try again");
//            return enterSkillId();
//        }
        return skillService.findById(Long.valueOf(skillId)).get();
    }

    @Override
    public void close() {
        System.exit(0);
    }
}
