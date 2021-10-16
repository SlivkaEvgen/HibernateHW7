package org.homework.hibernatehw7.repository;

import org.homework.hibernatehw7.model.Company;
import org.homework.hibernatehw7.model.Customer;
import org.homework.hibernatehw7.model.Developer;
import org.homework.hibernatehw7.model.Project;
import org.homework.hibernatehw7.repository.interfaces.ProjectRepository;
import org.homework.hibernatehw7.services.CompanyServiceImpl;
import org.homework.hibernatehw7.services.CustomerServiceImpl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ProjectRepositoryImpl extends ModelCrudRepositoryImpl<Project, Long> implements ProjectRepository {

    private static final long serialVersionUID = 3333844651928374654L;

    public ProjectRepositoryImpl(Class<Project> classModel) {
        super(classModel);
    }

    @Override
    public List<String> getListProjectsWithDate() {
        List<String> projectWithDate = new ArrayList<>();
        for (Project project : findAll()) {
            projectWithDate.add("Project " + project.getName() + " has " +
                    new DeveloperRepositoryImpl(Developer.class)
                            .getDevelopersFromOneProject(project.getId()).size()
                    + " developers, sight date: " +
                    project.getFirstDate());
        }
        return projectWithDate;
    }

    @Override
    public void updateProject(Long id, String name, Long cost, Long companyId, Long customerId) {
        Project project = findById(id).get();
        project.setName(name);
        project.setCost(cost);
        project.setFirstDate(new Date().toString());
        project.setCompany(new CompanyServiceImpl(Company.class).findById(companyId).get());
        project.setCustomer(new CustomerServiceImpl(Customer.class).findById(customerId).get());
        update(id, project);
    }

    @Override
    public Project createNewProject(String name, Long cost, Long companyId, Long customerId) {
        return create(Project.builder().name(name).cost(cost)
                .firstDate(new Date().toString())
                .company(new CompanyServiceImpl(Company.class).findById(companyId).get())
                .customer(new CustomerServiceImpl(Customer.class).findById(customerId).get())
                .build());
    }
}
