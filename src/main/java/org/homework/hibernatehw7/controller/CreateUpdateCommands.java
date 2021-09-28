package org.homework.hibernatehw7.controller;

import org.homework.hibernatehw7.controller.interfaces.Controller;
import org.homework.hibernatehw7.model.*;
import org.homework.hibernatehw7.services.*;
import org.homework.hibernatehw7.services.interfaces.*;

public class CreateUpdateCommands implements Controller {

    private final GetDeleteCommands<Skill, Long> generalSkillMethods = new GetDeleteCommands<>(Skill.class);
    private final GetDeleteCommands<Project, Long> generalProjectMethods = new GetDeleteCommands<>(Project.class);
    private final GetDeleteCommands<Developer, Long> generalDeveloperMethods = new GetDeleteCommands<>(Developer.class);
    private final GetDeleteCommands<Customer, Long> generalCustomerMethods = new GetDeleteCommands<>(Customer.class);
    private final GetDeleteCommands<Company, Long> generalCompanyMethods = new GetDeleteCommands<>(Company.class);
    private final SkillService SKILL_SERVICE = SkillServiceImpl.getInstance();
    private final ProjectService PROJECT_SERVICE = ProjectServiceImpl.getInstance();
    private final DeveloperService DEVELOPER_SERVICE = DeveloperServiceImpl.getInstance();
    private final CustomerService CUSTOMER_SERVICE = new CustomerServiceImpl();
    private final CompanyService COMPANY_SERVICE = CompanyServiceImpl.getInstance();
    private static CreateUpdateCommands createUpdateCommands;

    public static CreateUpdateCommands getInstance() {
        if (createUpdateCommands == null) {
            createUpdateCommands = new CreateUpdateCommands();
        }
        return createUpdateCommands;
    }

    @Override
    public void start() {

    }

    public void createSkill() {
        Skill skill = SKILL_SERVICE.createNewSkill(generalSkillMethods.enterActivity(),
                generalSkillMethods.enterLevel());
        System.out.println(" ✅ You created \uD83D\uDC49 " + "new Skill " + skill + "\n");
    }

    public void updateSkill() {
        final String id = generalSkillMethods.enterId();
        SKILL_SERVICE.updateSkill(Long.valueOf(id), generalSkillMethods.enterActivity(), generalSkillMethods.enterLevel());
        System.out.println(" ✅ You updated \uD83D\uDC49 " + SKILL_SERVICE.findById(Long.valueOf(id)).get() + "\n");
    }

    public void createProject() {
        Project project = PROJECT_SERVICE.createNewProject(generalProjectMethods.enterName(),
                Long.valueOf(generalProjectMethods.enterCost()),
                Long.valueOf(generalProjectMethods.enterCompanyId()),
                Long.valueOf(generalProjectMethods.enterCustomerId()));
        System.out.println(" ✅ You created \uD83D\uDC49 " + "new Project " + project + "\n");
    }

    public void updateProject() {
        final String id = generalProjectMethods.enterId();
        PROJECT_SERVICE.updateProject(Long.valueOf(id), generalProjectMethods.enterName(),
                Long.valueOf(generalProjectMethods.enterCost()),
                Long.valueOf(generalProjectMethods.enterCompanyId()),
                Long.valueOf(generalProjectMethods.enterCustomerId()));
        System.out.println(" ✅ You updated \uD83D\uDC49 " + PROJECT_SERVICE.findById(Long.valueOf(id)).get() + "\n");
    }

    public void createDeveloper() {
        DEVELOPER_SERVICE.createNewDeveloper(generalDeveloperMethods.enterName(), Long.valueOf(generalDeveloperMethods.enterAge()),
                generalDeveloperMethods.enterGender(), generalDeveloperMethods.enterEmail(),
                Long.valueOf(generalDeveloperMethods.enterSalary()), generalDeveloperMethods.addSetSkills(),
                Long.valueOf(generalDeveloperMethods.enterCompanyId()), Long.valueOf(generalDeveloperMethods.enterProjectId()));
        System.out.println(" ✅ You create \uD83D\uDC49   new Developer  \n");
    }

    public void updateDeveloper() {
        final String id = generalDeveloperMethods.enterId();
        DEVELOPER_SERVICE.updateDeveloper(Long.valueOf(id), generalDeveloperMethods.enterName(),
                Long.valueOf(generalDeveloperMethods.enterAge()),
                generalDeveloperMethods.enterGender(), generalDeveloperMethods.enterEmail(),
                Long.valueOf(generalDeveloperMethods.enterSalary()), generalDeveloperMethods.addSetSkills(),
                Long.valueOf(generalDeveloperMethods.enterCompanyId()),
                Long.valueOf(generalDeveloperMethods.enterProjectId()));
        System.out.println(" ✅ You updated \uD83D\uDC49 " + DEVELOPER_SERVICE.findById(Long.valueOf(id)).get() + "\n");
    }

    public void createCustomer() {
        Customer customer = CUSTOMER_SERVICE.createNewCustomer(generalCustomerMethods.enterName(), generalCustomerMethods.enterCity(),
                Long.valueOf(generalCustomerMethods.enterBudget()));
        System.out.println(" ✅ You created \uD83D\uDC49 " + "new Customer " + customer + "\n");
    }

    public void updateCustomer() {
        final String id = generalCustomerMethods.enterId();
        CUSTOMER_SERVICE.updateCustomer(Long.valueOf(id), generalCustomerMethods.enterName(),
                generalCustomerMethods.enterCity(), Long.valueOf(generalCustomerMethods.enterBudget()));
        System.out.println(" ✅ You updated \uD83D\uDC49 " + CUSTOMER_SERVICE.findById(Long.valueOf(id)).get() + "\n");
    }

    public void createCompany() {
        Company company = COMPANY_SERVICE.createNewCompany(generalCompanyMethods.enterName(), generalCompanyMethods.enterCity());
        System.out.println(" ✅ You created \uD83D\uDC49  " + "new Company " + company + " \n");
    }

    public void updateCompany() {
        final String id = generalCompanyMethods.enterId();
        COMPANY_SERVICE.updateCompany(Long.valueOf(id), generalCompanyMethods.enterName(), generalCompanyMethods.enterCity());
        System.out.println(" ✅ You updated \uD83D\uDC49 " + COMPANY_SERVICE.findById(Long.valueOf(id)).get() + "\n");
    }

    @Override
    public void close() {
        System.exit(0);
    }
}
