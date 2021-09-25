package org.homework.hibernatehw7.services;

import org.homework.hibernatehw7.model.Company;
import org.homework.hibernatehw7.model.Customer;
import org.homework.hibernatehw7.model.Developer;
import org.homework.hibernatehw7.model.Project;
import org.homework.hibernatehw7.services.interfaces.ProjectService;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class ProjectServiceImpl implements ProjectService {

    @Override
    public Project createNewProject(String name, Long cost, Long companyId, Long customerId, Long developerId) {
        Set<Developer> developers = new HashSet<>();
        developers.add(ServiceFactory.of(Developer.class).findById(developerId).get());

        return create(Project.builder()
                .name(name)
                .cost(cost)
                .company(ServiceFactory.of(Company.class).findById(companyId).get())
                .customer(ServiceFactory.of(Customer.class).findById(customerId).get())
                .developers(developers)
                .build());
    }

    @Override
    public void updateProject(Long id, String name, Long cost, Long companyId, Long customerId, Long developerId) {
        Set<Developer> developerSet = new HashSet<>();
        developerSet.add(ServiceFactory.of(Developer.class).findById(developerId).get());

        Project project = findById(id).get();
        project.setName(name);
        project.setCost(cost);
        project.setCompany(ServiceFactory.of(Company.class).findById(companyId).get());
        project.setCustomer(ServiceFactory.of(Customer.class).findById(customerId).get());
        project.setDevelopers(developerSet);
        update(id, project);
    }

    @Override
    public List<String> getListProjectsWithDate() {
        return IntStream.range(0, findAll().size())
                .mapToObj(i -> "In project " +
                               ServiceFactory.of(Developer.class).findAll().get(i).getName() + " - " +
                               countDevelopers(i + 1) + " developers, signs - " +
                               ServiceFactory.of(Project.class).findAll().get(i).getFirstDate())
                .collect(Collectors.toList());
    }

    @Override
    public Optional<Project> findById(Long id) {
        return ServiceFactory.of(Project.class).findById(id);
    }

    @Override
    public List<Project> findAll() {
        return ServiceFactory.of(Project.class).findAll();
    }

    @Override
    public Project create(Project project) {
        return ServiceFactory.of(Project.class).create(project);
    }

    @Override
    public Project update(Long id, Project project) {
        return ServiceFactory.of(Project.class).update(id, project);
    }

    @Override
    public void delete(Long id) {
        ServiceFactory.of(Project.class).delete(id);
    }

    @Override
    public void close() {
        ServiceFactory.of(Project.class).close();
    }

    private int countDevelopers(int projectId) {
        return new DeveloperServiceImpl().getDevelopersFromOneProject((long) projectId).size();
    }

}
