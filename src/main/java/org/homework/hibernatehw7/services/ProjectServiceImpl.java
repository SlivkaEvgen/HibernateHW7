package org.homework.hibernatehw7.services;

import org.homework.hibernatehw7.model.Project;
import org.homework.hibernatehw7.repository.ProjectCrudRepositoryImpl;
import org.homework.hibernatehw7.services.interfaces.ProjectService;

import java.util.List;
import java.util.Optional;

public class ProjectServiceImpl implements ProjectService {

    private final ProjectCrudRepositoryImpl CRUD_REPOSITORY =  ProjectCrudRepositoryImpl.getInstance();
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
        return CRUD_REPOSITORY.createNewProject(name, cost, companyId, customerId);
    }

    @Override
    public void updateProject(Long id, String name, Long cost, Long companyId, Long customerId) {
        CRUD_REPOSITORY.updateProject(id, name, cost, companyId, customerId);
    }

    @Override
    public List<String> getListProjectsWithDate() {
        return CRUD_REPOSITORY.getListProjectsWithDate();
    }

    @Override
    public Optional<Project> findById(Long id) {
        return CRUD_REPOSITORY.findById(id);
    }

    @Override
    public List<Project> findAll() {
        return CRUD_REPOSITORY.findAll();
    }

    @Override
    public Project create(Project project) {
        return CRUD_REPOSITORY.create(project);
    }

    @Override
    public Project update(Long id, Project project) {
        return CRUD_REPOSITORY.update(id, project);
    }

    @Override
    public void delete(Long id) {
        CRUD_REPOSITORY.delete(id);
    }

    @Override
    public void close() {
        CRUD_REPOSITORY.close();
    }
}
