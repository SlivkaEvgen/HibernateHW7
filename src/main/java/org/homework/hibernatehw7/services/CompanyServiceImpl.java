package org.homework.hibernatehw7.services;

import org.homework.hibernatehw7.model.Company;
import org.homework.hibernatehw7.model.Developer;
import org.homework.hibernatehw7.model.Project;
import org.homework.hibernatehw7.repository.CrudRepositoryHibernateImpl;
import org.homework.hibernatehw7.services.interfaces.CompanyService;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

public class CompanyServiceImpl implements CompanyService {

    private final CrudRepositoryHibernateImpl<Company, Long> CRUD_REPOSITORY_COMPANY = new CrudRepositoryHibernateImpl<>(Company.class);
    private static CompanyServiceImpl companyService;

    public static CompanyServiceImpl getInstance() {
        if (companyService == null) {
            synchronized (CompanyServiceImpl.class) {
                if (companyService == null) {
                    companyService = new CompanyServiceImpl();
                }
            }
        }
        return companyService;
    }

    @Override
    public Optional<Company> getById(Long id) {
       return companyService.CRUD_REPOSITORY_COMPANY.findById(id);
    }

    @Override
    public List<Company> getAll() {
        return companyService.CRUD_REPOSITORY_COMPANY.findAll();
    }

    @Override
    public Company createNewCompany(String name, String city, Long projectId, Long developerId) {//Long projectId
        Set<Project> projectSet = new HashSet<>();
//        Project project = ProjectServiceImpl.getInstance().getById(projectId).get();
        projectSet.add(ProjectServiceImpl.getInstance().getById(projectId).get());

        Set<Developer> developerSet = new HashSet<>();
//        Developer developer = DeveloperServiceImpl.getInstance().getById(developerId).get();
        developerSet.add(DeveloperServiceImpl.getInstance().getById(developerId).get());

        Company company = Company.builder()
                .name(name)
                .city(city)
                .projects(projectSet)
                .developers(developerSet)
                .build();
        return companyService.CRUD_REPOSITORY_COMPANY.create(company);
    }

    @Override
    public void update(Long id, String name, String city, Long projectId, Long developerId) {
        Set<Project> projectSet = new HashSet<>();
//        Project project = ProjectServiceImpl.getInstance().getById(projectId).get();
        projectSet.add(ProjectServiceImpl.getInstance().getById(projectId).get());

        Set<Developer> developerSet = new HashSet<>();
//        Developer developer = DeveloperServiceImpl.getInstance().getById(developerId).get();
        developerSet.add(DeveloperServiceImpl.getInstance().getById(developerId).get());

        Company company = companyService.CRUD_REPOSITORY_COMPANY.findById(id).get();
        company.setCity(city);
        company.setName(name);
        company.setProjects(projectSet);
        company.setDevelopers(developerSet);
        companyService.CRUD_REPOSITORY_COMPANY.update(id, company);
    }

    @Override
    public void delete(Long id) {
        companyService.CRUD_REPOSITORY_COMPANY.delete(id);
    }
}
