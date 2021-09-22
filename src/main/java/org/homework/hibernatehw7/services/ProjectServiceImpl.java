package org.homework.hibernatehw7.services;

import org.homework.hibernatehw7.model.Project;
import org.homework.hibernatehw7.repository.CrudRepositoryHibernateImpl;
import org.homework.hibernatehw7.services.interfaces.ProjectService;

import java.util.List;
import java.util.Optional;

public class ProjectServiceImpl implements ProjectService {

    private final CrudRepositoryHibernateImpl<Project, Long> CRUD_REPOSITORY_PROJECT = new CrudRepositoryHibernateImpl<>(Project.class);

    @Override
    public Optional<Project> getById(Long id) {
        return CRUD_REPOSITORY_PROJECT.findById(id);
    }

    @Override
    public List<Project> getAll() {
        return CRUD_REPOSITORY_PROJECT.findAll();
    }

    @Override
    public Project createNewProject(String name, Long cost, Long companyId, Long customerId) {
        Project project = Project.builder()
                .name(name)
                .cost(cost)
                .build();
//        project.setCompanyId(companyId);
//        project.setCustomerId(customerId);
        return CRUD_REPOSITORY_PROJECT.create(project);
    }

    @Override
    public void update(Long id, String name, Long cost) {
        Project project = CRUD_REPOSITORY_PROJECT.findById(id).get();
        project.setName(name);
        project.setCost(cost);
//        project.setCompany();
//        project.setCustomer();
//        project.setDevelopers();

        CRUD_REPOSITORY_PROJECT.update(id,project);
    }

    @Override
    public void delete(Long id) {
        CRUD_REPOSITORY_PROJECT.delete(id);
    }

    @Override
    public List<String> getListProjectsWithDate() {
//        List<String> listProjectsWithDate = CRUD_REPOSITORY_PROJECT.getListProjectsWithDate();
////        CRUD_REPOSITORY_PROJECT.close();
//        return listProjectsWithDate;
        return null;
    }
}
