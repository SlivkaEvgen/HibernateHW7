package org.homework.hibernatehw7.repository;

import org.homework.hibernatehw7.model.Project;
import org.homework.hibernatehw7.repository.interfaces.ProjectCrudRepository;
import org.homework.hibernatehw7.services.DeveloperServiceImpl;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ProjectCrudRepositoryImpl implements ProjectCrudRepository {

    @Override
    public Optional<Project> findById(Long id) {
        return RepositoryFactory.of(Project.class).findById(id);
    }

    @Override
    public List<Project> findAll() {
        return RepositoryFactory.of(Project.class).findAll();
    }

    @Override
    public Project create(Project project) {
        return RepositoryFactory.of(Project.class).create(project);
    }

    @Override
    public Project update(Long id, Project project) {
        return RepositoryFactory.of(Project.class).update(id, project);
    }

    @Override
    public void delete(Long id) {
        RepositoryFactory.of(Project.class).delete(id);
    }

    @Override
    public void close() {
        RepositoryFactory.of(Project.class).close();
    }

    @Override
    public List<String> getListProjectsWithDate() {
        List<String> proWithDate = new ArrayList<>();
        for (Project project : RepositoryFactory.of(Project.class).findAll()) {
            proWithDate.add("Project " + project.getName() + " has " +
                            new DeveloperServiceImpl().getDevelopersFromOneProject(project.getId()).size() + " developers, sight date: " +
                            project.getFirstDate());
        }
        return proWithDate;
    }
}
