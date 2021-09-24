package org.homework.hibernatehw7.services;

import org.homework.hibernatehw7.model.Developer;
import org.homework.hibernatehw7.model.Project;
import org.homework.hibernatehw7.repository.RepositoryFactory;
import org.homework.hibernatehw7.repository.interfaces.CrudRepositoryJDBC;
import org.homework.hibernatehw7.services.interfaces.ProjectService;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class ProjectServiceImpl implements ProjectService {

    private final CrudRepositoryJDBC<Project, Long> CRUD_REPOSITORY_PROJECT = RepositoryFactory.of(Project.class);
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
    public Optional<Project> getById(Long id) {
        return projectService.CRUD_REPOSITORY_PROJECT.findById(id);
    }

    @Override
    public List<Project> getAll() {
        return projectService.CRUD_REPOSITORY_PROJECT.findAll();
    }

    @Override
    public Project createNewProject(String name, Long cost, Long companyId, Long customerId, Long developerId) {
        Set<Developer> developers = new HashSet<>();
        developers.add(DeveloperServiceImpl.getInstance().getById(developerId).get());

        return projectService.CRUD_REPOSITORY_PROJECT.create(Project.builder()
                .name(name)
                .cost(cost)
                .company(CompanyServiceImpl.getInstance().getById(companyId).get())
                .customer(CustomerServiceImpl.getInstance().getById(customerId).get())
                .developers(developers)
                .build());
    }

    @Override
    public void update(Long id, String name, Long cost, Long companyId, Long customerId, Long developerId) {
        Set<Developer> developerSet = new HashSet<>();
        developerSet.add(DeveloperServiceImpl.getInstance().getById(developerId).get());

        Project project = projectService.CRUD_REPOSITORY_PROJECT.findById(id).get();
        project.setName(name);
        project.setCost(cost);
        project.setCompany(CompanyServiceImpl.getInstance().getById(companyId).get());
        project.setCustomer(CustomerServiceImpl.getInstance().getById(customerId).get());
        project.setDevelopers(developerSet);
        projectService.CRUD_REPOSITORY_PROJECT.update(id, project);
    }

    @Override
    public void delete(Long id) {
        projectService.CRUD_REPOSITORY_PROJECT.delete(id);
    }

    @Override
    public List<String> getListProjectsWithDate() {
        return IntStream.range(0, getAll().size())
                .mapToObj(i -> "In project " + getAll().get(i).getName() + " - " + countDevelopers(i + 1) + " developers, signs - " + getAll().get(i).getFirstDate())
                .collect(Collectors.toList());
    }

    private int countDevelopers(int projectId) {
        return DeveloperServiceImpl.getInstance().getDevelopersFromOneProject((long) projectId).size();
    }
}
