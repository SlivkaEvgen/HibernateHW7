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
import java.util.Scanner;
import java.util.Set;

public class UpdateDeveloperCommand implements Controller {

    private final DeveloperService DEVELOPER_SERVICE = new DeveloperServiceImpl();
    private final Scanner scanner = ScannerConsole.getInstance();
    private static UpdateDeveloperCommand updateDeveloperCommand;

    public static UpdateDeveloperCommand getInstance() {
        if (updateDeveloperCommand == null) {
            updateDeveloperCommand = new UpdateDeveloperCommand();
        }
        return updateDeveloperCommand;
    }

    @Override
    public void start() {
        update();
    }

    private void update() {
        final String id = enterId();
        final String name = enterName();
        final String age = enterAge();
        final String gender = enterGender();
        final String email = enterEmail();
        final String salary = enterSalary();
//        Set<Skill> skillSet = enterSkillId();
        Set<Skill> skillSet = createSet();
        final String companyId = enterCompanyId();
        final String projectId = enterProjectId();
        DEVELOPER_SERVICE.updateDeveloper(Long.valueOf(id), name, Long.valueOf(age), gender, email, Long.valueOf(salary),  skillSet, Long.valueOf(companyId),Long.valueOf( projectId));
        System.out.println(" ✅ You updated \uD83D\uDC49 " + DEVELOPER_SERVICE.findById(Long.valueOf(id)).get() + "\n");
    }

    private String enterId() {
        System.out.print(" ENTER ID \n\uD83D\uDC49 ");
        String id = scanner.next();
        if (!Validator.validNumber(id) || !DEVELOPER_SERVICE.findById(Long.valueOf(id)).isPresent()) {
            System.out.println("Try again");
            return enterId();
        }
        return id;
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
        if (!Validator.validNumber(salary) | salary.length() > 10) {
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
        System.out.print(" ENTER PROJECT-ID \n\uD83D\uDC49 ");
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

    private String enterSkillId() {
        System.out.print(" ENTER SKILL-ID \n\uD83D\uDC49 ");
        String skillId = scanner.next();
        try {
            if (!Validator.validNumber(skillId) || !SkillServiceImpl.getInstance().findById(Long.valueOf(skillId)).isPresent()) {
                System.out.println("Try again");
                return enterSkillId();
            }
        } catch (NumberFormatException r) {
            System.out.println("Try again");
            return enterSkillId();
        }
        return skillId;
    }

    private Set<Skill> createSet() {
        Set<Skill>skillSet = new HashSet<>();
        System.out.print(" ENTER SKILL-ID \n\uD83D\uDC49 ");
        String skillId = scanner.next();
        try {
            if (!Validator.validNumber(skillId) || !SkillServiceImpl.getInstance().findById(Long.valueOf(skillId)).isPresent()) {
                System.out.println("Try again");
                return createSet();
            }
        } catch (NumberFormatException r) {
            System.out.println("Try again");
            return createSet();
        }
        skillSet.add(SkillServiceImpl.getInstance().findById(Long.valueOf(skillId)).get());
        return skillSet;
    }

    @Override
    public void close() {
        System.exit(0);
    }
}
