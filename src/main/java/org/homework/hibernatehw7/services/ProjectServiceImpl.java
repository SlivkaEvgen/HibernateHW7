package org.homework.hibernatehw7.services;

import org.homework.hibernatehw7.model.Project;
import org.homework.hibernatehw7.repository.ProjectCrudRepositoryImpl;
import org.homework.hibernatehw7.services.interfaces.ProjectService;

import java.util.List;
import java.util.Optional;

public class ProjectServiceImpl implements ProjectService {

    private final ProjectCrudRepositoryImpl projectCrudRepository = new ProjectCrudRepositoryImpl();
    private static ProjectServiceImpl projectService;

    public static ProjectServiceImpl getInstance() {
        if (projectService == null) {
            synchronized (ProjectServiceImpl.class) {
                if (projectService == null) {
                    projectService = new ProjectServiceImpl();
                }
            }
        }
        return projectService;
    }

    @Override
    public Project createNewProject(String name, Long cost, Long companyId, Long customerId) {
        return create(Project.builder().name(name).cost(cost)
                .company(CompanyServiceImpl.getInstance().findById(companyId).get())
                .customer(CustomerServiceImpl.getInstance().findById(customerId).get())
                .build());
    }

    @Override
    public void updateProject(Long id, String name, Long cost, Long companyId, Long customerId) {
//        Set<Developer> developerSet = new HashSet<>();
//        developerSet.add(DeveloperServiceImpl.getInstance().findById(developerId).get());
        Project project = findById(id).get();
        project.setName(name);
        project.setCost(cost);
        project.setCompany(CompanyServiceImpl.getInstance().findById(companyId).get());
        project.setCustomer(CustomerServiceImpl.getInstance().findById(customerId).get());
//        project.setDevelopers(developerSet);
        update(id, project);
    }

    @Override
    public List<String> getListProjectsWithDate() {
        return projectCrudRepository.getListProjectsWithDate();
    }

    @Override
    public Optional<Project> findById(Long id) {
        return projectCrudRepository.findById(id);
    }

    @Override
    public List<Project> findAll() {
        return projectCrudRepository.findAll();
    }

    @Override
    public Project create(Project project) {
        return projectCrudRepository.create(project);
    }

    @Override
    public Project update(Long id, Project project) {
        return projectCrudRepository.update(id, project);
    }

    @Override
    public void delete(Long id) {
        projectCrudRepository.delete(id);
    }

    @Override
    public void close() {
        projectCrudRepository.close();
    }
}
