package org.homework.hibernatehw7.services;

import org.homework.hibernatehw7.model.Project;
import org.homework.hibernatehw7.repository.ProjectRepositoryImpl;
import org.homework.hibernatehw7.repository.interfaces.ProjectRepository;
import org.homework.hibernatehw7.services.interfaces.ProjectService;

import java.util.List;

public class ProjectServiceImpl extends ModelService<Project, Long> implements ProjectService {

    private static final long serialVersionUID = 3334544651928374654L;
    private final ProjectRepository CRUD_REPOSITORY = new ProjectRepositoryImpl(Project.class);

    public ProjectServiceImpl(Class<Project> classModel) {
        super(classModel);
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
}
