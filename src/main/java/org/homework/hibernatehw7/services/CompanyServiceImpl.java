package org.homework.hibernatehw7.services;

import org.homework.hibernatehw7.model.Company;
import org.homework.hibernatehw7.repository.CrudRepositoryHibernateImpl;
import org.homework.hibernatehw7.services.interfaces.CompanyService;

import java.util.List;
import java.util.Optional;

public class CompanyServiceImpl implements CompanyService {

    private final CrudRepositoryHibernateImpl<Company, Long> CRUD_REPOSITORY_COMPANY = new CrudRepositoryHibernateImpl<>(Company.class);

    @Override
    public Optional<Company> getById(Long id) {
        return CRUD_REPOSITORY_COMPANY.findById(id);
    }

    @Override
    public List<Company> getAll() {
        return CRUD_REPOSITORY_COMPANY.findAll();
    }

    @Override
    public Company createNewCompany(String name, String city,Long projectId) {
//        Set<Project> projectSet = new HashSet<>();
//        CrudRepositoryJDBC<Project, Long> repositoryJDBC = RepositoryFactory.of(Project.class);
//        Project project = repositoryJDBC.findById(projectId).get();
//        projectSet.add(project);
//        Set<Developer> developerSet = new HashSet<>();
//        CrudRepositoryJDBC<Developer, Long> repositoryJDBC1 = RepositoryFactory.of(Developer.class);
//        Developer developer = repositoryJDBC1.findById(projectId).get();
//        developerSet.add(developer);
        Company company = Company.builder()
                .name(name)
                .city(city)
//                .projects(projectSet)
//                .developers(developerSet)
                .build();
        return CRUD_REPOSITORY_COMPANY.create(company);
    }

    @Override
    public void update(Long id, String name, String city,Long projectId) {
//        Set<Project> projectSet = new HashSet<>();
//        CrudRepositoryJDBC<Project, Long> repositoryJDBC = RepositoryFactory.of(Project.class);
//        Project project = repositoryJDBC.findById(projectId).get();
//        projectSet.add(project);
//        Set<Developer> developerSet = new HashSet<>();
//        CrudRepositoryJDBC<Developer, Long> repositoryJDBC1 = RepositoryFactory.of(Developer.class);
//        Developer developer = repositoryJDBC1.findById(projectId).get();
//        developerSet.add(developer);
        Company company = CRUD_REPOSITORY_COMPANY.findById(id).get();
        company.setCity(city);
        company.setName(name);
//        company.setProjects(projectSet);
//        company.setDevelopers(developerSet);
        CRUD_REPOSITORY_COMPANY.update(id,company);
    }

    @Override
    public void delete(Long id) {
        CRUD_REPOSITORY_COMPANY.delete(id);
    }
}
