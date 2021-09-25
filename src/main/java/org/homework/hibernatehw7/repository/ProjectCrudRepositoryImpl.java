package org.homework.hibernatehw7.repository;

import org.homework.hibernatehw7.model.Project;
import org.homework.hibernatehw7.repository.interfaces.ProjectCrudRepository;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

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
        return RepositoryFactory.of(Project.class).update(id,project);
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
        return IntStream.range(0, findAll().size())
                .mapToObj(i -> "In project " +
                               RepositoryFactory.of(Project.class).findAll().get(i).getName() + " - " +
                               countDevelopers(i + 1) + " developers, signs - " +
                               RepositoryFactory.of(Project.class).findAll().get(i).getFirstDate())
                .collect(Collectors.toList());
    }

    private int countDevelopers(int projectId) {
        return new DeveloperCrudRepositoryImpl().getDevelopersFromOneProject((long) projectId).size();
    }
}
