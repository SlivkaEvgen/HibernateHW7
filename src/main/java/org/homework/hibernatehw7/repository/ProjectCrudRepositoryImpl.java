package org.homework.hibernatehw7.repository;

import org.homework.hibernatehw7.model.Project;
import org.homework.hibernatehw7.repository.interfaces.CrudRepositoryJDBC;
import org.homework.hibernatehw7.repository.interfaces.ProjectCrudRepository;
import org.homework.hibernatehw7.services.CompanyServiceImpl;
import org.homework.hibernatehw7.services.CustomerServiceImpl;
import org.homework.hibernatehw7.services.DeveloperServiceImpl;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ProjectCrudRepositoryImpl implements ProjectCrudRepository {

    private final CrudRepositoryJDBC<Project, Long> CRUD_REPOSITORY_JDBC = RepositoryFactory.of(Project.class);
    private static ProjectCrudRepositoryImpl projectCrudRepository;

    public static ProjectCrudRepositoryImpl getInstance() {
        if (projectCrudRepository == null) {
            synchronized (ProjectCrudRepositoryImpl.class) {
                if (projectCrudRepository == null) {
                    projectCrudRepository = new ProjectCrudRepositoryImpl();
                }
            }
        }
        return projectCrudRepository;
    }

    @Override
    public Optional<Project> findById(Long id) {
        return CRUD_REPOSITORY_JDBC.findById(id);
    }

    @Override
    public List<Project> findAll() {
        return CRUD_REPOSITORY_JDBC.findAll();
    }

    @Override
    public Project create(Project project) {
        return CRUD_REPOSITORY_JDBC.create(project);
    }

    @Override
    public Project update(Long id, Project project) {
        return CRUD_REPOSITORY_JDBC.update(id, project);
    }

    @Override
    public void delete(Long id) {
        CRUD_REPOSITORY_JDBC.delete(id);
    }

    @Override
    public void close() {
        CRUD_REPOSITORY_JDBC.close();
    }

    @Override
    public List<String> getListProjectsWithDate() {
        List<String> projectWithDate = new ArrayList<>();
        for (Project project : findAll()) {
            projectWithDate.add("Project " + project.getName() + " has " +
                                DeveloperServiceImpl.getInstance().getDevelopersFromOneProject(project.getId()).size() + " developers, sight date: " +
                                project.getFirstDate());
        }
        return projectWithDate;
    }

    @Override
    public void updateProject(Long id, String name, Long cost, Long companyId, Long customerId) {
        Project project = findById(id).get();
        project.setName(name);
        project.setCost(cost);
        project.setCompany(CompanyServiceImpl.getInstance().findById(companyId).get());
        project.setCustomer(CustomerServiceImpl.getInstance().findById(customerId).get());
        update(id, project);
    }

    @Override
    public Project createNewProject(String name, Long cost, Long companyId, Long customerId) {
        return create(Project.builder().name(name).cost(cost)
                .company(CompanyServiceImpl.getInstance().findById(companyId).get())
                .customer(CustomerServiceImpl.getInstance().findById(customerId).get())
                .build());
    }
}
