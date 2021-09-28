package org.homework.hibernatehw7.controller;

import org.homework.hibernatehw7.config.ScannerConsole;
import org.homework.hibernatehw7.controller.interfaces.Controller;
import org.homework.hibernatehw7.model.BaseModel;
import org.homework.hibernatehw7.model.Developer;
import org.homework.hibernatehw7.model.Skill;
import org.homework.hibernatehw7.services.*;
import org.homework.hibernatehw7.services.interfaces.Service;
import org.homework.hibernatehw7.utils.Validator;

import java.util.*;

public class GetDeleteCommands<T extends BaseModel<ID>, ID> implements Controller {

    private final Class<T> modelClass;
    private final Scanner scanner = ScannerConsole.getInstance();
    private final Service<T, ID> service;
    private final DeveloperServiceImpl developerService = DeveloperServiceImpl.getInstance();
    private final Set<Skill> skillSet = new HashSet<>();

    public GetDeleteCommands(Class<T> modelClass) {
        this.modelClass = modelClass;
        this.service = ServiceFactory.of(modelClass);
    }

    @Override
    public void start() {

    }

    public String enterId() {
        System.out.print(" ENTER ID \n\uD83D\uDC49 ");
        String id = scanner.next();
        Optional<T> optional = service.findById((ID) Long.valueOf(id));
        if (!Validator.validNumber(id) | !optional.isPresent()) {
            System.out.println("Try again");
            return enterId();
        }
        return id;
    }

    public String enterCompanyId() {
        System.out.print(" ENTER COMPANY-ID \n\uD83D\uDC49 ");
        String companyId = scanner.next();
        try {
            if (!Validator.validNumber(companyId) | !CompanyServiceImpl.getInstance().findById(Long.valueOf(companyId)).isPresent()) {
                System.out.println("Try again");
                return enterCompanyId();
            }
        } catch (NumberFormatException r) {
            System.out.println("Try again");
            return enterCompanyId();
        }
        return companyId;
    }

    public String enterCustomerId() {
        System.out.print(" ENTER CUSTOMER-ID \n\uD83D\uDC49 ");
        String customerId = scanner.next();
        try {
            if (!Validator.validNumber(customerId) | !CustomerServiceImpl.getInstance().findById(Long.valueOf(customerId)).isPresent()) {
                System.out.println("Try again");
                return enterCustomerId();
            }
        } catch (NumberFormatException r) {
            System.out.println("Try again");
            return enterCustomerId();
        }
        return customerId;
    }

    public String enterProjectId() {
        System.out.print(" ENTER PROJECT-ID \n\uD83D\uDC49 ");
        String projectId = scanner.next();
        try {
            if (!Validator.validNumber(projectId) | !ProjectServiceImpl.getInstance().findById(Long.valueOf(projectId)).isPresent()) {
                System.out.println("Try again");
                return enterProjectId();
            }
        } catch (NumberFormatException r) {
            System.out.println("Try again");
            return enterProjectId();
        }
        return projectId;
    }

    public String enterCost() {
        System.out.print(" ENTER COST \n\uD83D\uDC49 ");
        String cost = scanner.next();
        if (!Validator.validNumber(cost) | cost.length() > 10) {
            System.out.println("Try again");
            return enterCost();
        }
        return cost;
    }

    public String enterAge() {
        System.out.print(" ENTER AGE \n\uD83D\uDC49 ");
        String age = scanner.next();
        if (!Validator.validNumber(age) | age.length() > 2) {
            System.out.println("Try again");
            return enterAge();
        }
        return age;
    }

    public String enterSalary() {
        System.out.print(" ENTER SALARY \n\uD83D\uDC49 ");
        String salary = scanner.next();
        if (!Validator.validNumber(salary) | salary.length() > 8) {
            System.out.println("Try again");
            return enterSalary();
        }
        return salary;
    }

    public String enterGender() {
        System.out.print(" ENTER GENDER \n\uD83D\uDC49 ");
        System.out.print(" Examples  \uD83D\uDC49  Male, Female\n");
        String gender = scanner.next();
        if (!Validator.validString(gender) | !gender.equalsIgnoreCase("male") & !gender.equalsIgnoreCase("female")) {
            System.out.println("Try again");
            return enterGender();
        }
        return gender;
    }

    public String enterEmail() {
        System.out.print(" ENTER EMAIL \n\uD83D\uDC49 ");
        String email = scanner.next();
        if (!email.contains("@") | email.length() > 13) {
            System.out.println("Try again");
            return enterEmail();
        }
        return email;
    }

    public String enterActivity() {
        System.out.print(" ENTER ACTIVITY \n\uD83D\uDC49 ");
        System.out.println(" Example: Java, C+, JS, C# ");
        String activity = scanner.next();
        if (!Validator.validString(activity) | !activity.equalsIgnoreCase("java")
                                               & !activity.equalsIgnoreCase("js")
                                               & !activity.equalsIgnoreCase("c+")
                                               & !activity.equalsIgnoreCase("c#")) {
            System.out.println("Try again");
            return enterActivity();
        }
        return activity;
    }

    public String enterLevel() {
        System.out.print(" ENTER LEVEL \n\uD83D\uDC49 ");
        System.out.println(" Example: Junior, Middle, Senior");
        String level = scanner.next();
        if (!Validator.validString(level) | !level.equalsIgnoreCase("junior")
                                            & !level.equalsIgnoreCase("middle")
                                            & !level.equalsIgnoreCase("senior")) {
            System.out.println("Try again");
            return enterLevel();
        }
        return level;
    }

    private Skill getSkill(String skillId) {
        Skill skill;
        Optional<Skill> optional = ServiceFactory.of(Skill.class).findById(Long.valueOf(skillId));
        try {
            if (!Validator.validNumber(skillId) | !optional.isPresent()) {
                System.out.println("Try again");
                addSetSkills();
            }
        } catch (NumberFormatException r) {
            System.out.println("Try again");
            addSetSkills();
        }
        skill = optional.get();
        return skill;
    }

    public Set<Skill> addSetSkills() {
        System.out.print(" ENTER Skill-ID \n\uD83D\uDC49 ");
        String skillId = scanner.next();
        Skill skill = getSkill(skillId);
        skillSet.add(skill);
        System.out.print("Add More skills? \n YES \uD83D\uDC49 Y \n NO \uD83D\uDC49 N \n\uD83D\uDC49 ");
        if (scanner.next().equalsIgnoreCase("y")) {
            return addSetSkills();
        }
        return skillSet;
    }

    public String enterBudget() {
        System.out.print(" ENTER BUDGET \n\uD83D\uDC49 ");
        String budget = scanner.next();
        if (!Validator.validNumber(budget) | budget.length() > 10) {
            System.out.println("Try again");
            return enterBudget();
        }
        return budget;
    }

    public String enterCity() {
        System.out.print(" ENTER CITY \n\uD83D\uDC49 ");
        String city = scanner.next();
        if (!Validator.validString(city) | city.length() > 15) {
            System.out.println("Try again");
            return enterCity();
        }
        return city;
    }

    public String enterName() {
        System.out.print(" ENTER NAME \n\uD83D\uDC49 ");
        String name = scanner.next();
        if (!Validator.validString(name) | name.length() > 15) {
            System.out.println("Try again");
            return enterName();
        }
        return name;
    }

    public void getByLevel() {
        System.out.print(" ENTER LEVEL \n ✅examples : Junior, Middle, Senior \n\uD83D\uDC49 ");
        String level = scanner.next();
        if (Validator.validString(level)) {
            if (!level.equalsIgnoreCase("middle")) {
                if (!level.equalsIgnoreCase("senior")) {
                    if (!level.equalsIgnoreCase("junior")) {
                        System.out.println("not found, try again ");
                        getByLevel();
                    } else {
                        System.out.println(developerService.getDevelopersByLevel(level) + "\n");
                    }
                } else {
                    System.out.println(developerService.getDevelopersByLevel(level) + "\n");
                }
            } else {
                System.out.println(developerService.getDevelopersByLevel(level) + "\n");
            }
        } else {
            System.out.print("\n not found, try again \n");
            getByLevel();
        }
    }

    public void getSumSalaries() {
        System.out.print(" ENTER PROJECT-ID \n\uD83D\uDC49 ");
        String next = scanner.next();
        if (Validator.validNumber(next)) {
            try {
                if (!next.isEmpty()) {
                    long nextLong = Long.parseLong(next);
                    if (nextLong != 0) {
                        System.out.print(developerService.getSumSalariesDevelopersOfOneProject(nextLong) + "\n");
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

    public void getByProjectID() {
        System.out.print(" ENTER PROJECT-ID \n\uD83D\uDC49 ");
        String projectId = scanner.next();
        if (Validator.validNumber(projectId)) {
            List<Developer> fromOneProject = developerService.getDevelopersFromOneProject(Long.valueOf(projectId));
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

    public void getByActivity() {
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
                            System.out.println(developerService.getDevelopersByActivity(activity) + "\n");
                        }
                    } else {
                        System.out.println(developerService.getDevelopersByActivity(activity) + "\n");
                    }
                } else {
                    System.out.println(developerService.getDevelopersByActivity(activity) + "\n");
                }
            } else {
                System.out.println(developerService.getDevelopersByActivity(activity) + "\n");
            }
        } else {
            System.out.println("not found, try again ");
            getByActivity();
        }
    }

    public void getById() {
        System.out.print("\n ENTER ID \n\uD83D\uDC49 ");
        String next = scanner.next();
        if (Validator.validNumber(next)) {
            Optional<T> optional = ServiceFactory.of(modelClass).findById((ID) Long.valueOf(next));
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

    public void getAll() {
        System.out.println(ServiceFactory.of(modelClass).findAll());
    }

    public void delete() {
        System.out.print("\n ENTER ID \n\uD83D\uDC49 ");
        String id = scanner.next();
        if (Validator.validNumber(id)) {
            if (ServiceFactory.of(modelClass).findById((ID) Long.valueOf(id)).isPresent()) {
                if (modelClass.getSimpleName().equalsIgnoreCase("Customer")) {
                    System.out.println("\n\n" +
                                       "‼️ All \uD83D\uDED1PROJECTS \n" +
                                       "will be ❗DELETED❗ together with the customer!\n" +
                                       "‼️ Are you sure you want to delete the customer❓❓❓  \n"
                                       + service.findById((ID) Long.valueOf(id)).get() + "\n");
                }
                if (modelClass.getSimpleName().equalsIgnoreCase("Company")) {
                    System.out.println("\n\n" +
                                       "‼️ All \uD83D\uDED1PROJECTS and \uD83D\uDED1DEVELOPERS  \n" +
                                       "will be ❗DELETED❗ together with the company!\n" +
                                       "‼️ Are you sure you want to delete the company❓❓❓  \n" +
                                       service.findById((ID) Long.valueOf(id)).get() + "\n");
                } else {
                    System.out.println("\nAre you sure ?  \n" + ServiceFactory.of(modelClass).findById((ID) Long.valueOf(id)).get());
                }
                System.out.print("Yes \uD83D\uDC49 Y\nNo  \uD83D\uDC49 N\n\uD83D\uDC49 ");
                final String yesNo = scanner.next();
                if (yesNo.equalsIgnoreCase("y")) {
                    ServiceFactory.of(modelClass).delete((ID) Long.valueOf(id));
                    System.out.println(" ✅ You removed the " + modelClass.getSimpleName() + " number \uD83D\uDC49 " + id + " \n");
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

    public void getListProjectsWithDate() {
        System.out.println(ProjectServiceImpl.getInstance().getListProjectsWithDate() + "\n");
    }

    @Override
    public void close() {
        System.exit(0);
    }
}
