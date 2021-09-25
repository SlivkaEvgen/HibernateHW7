package org.homework.hibernatehw7.services;

import org.homework.hibernatehw7.model.Company;
import org.homework.hibernatehw7.model.Developer;
import org.homework.hibernatehw7.model.Project;
import org.homework.hibernatehw7.services.interfaces.CompanyService;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

public class CompanyServiceImpl implements CompanyService {

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
    public Optional<Company> findById(Long id) {
        return ServiceFactory.of(Company.class).findById(id);
    }

    @Override
    public List<Company> findAll() {
        return ServiceFactory.of(Company.class).findAll();
    }

    @Override
    public Company create(Company company) {
        return ServiceFactory.of(Company.class).create(company);
    }

    @Override
    public Company update(Long id, Company company) {
        return ServiceFactory.of(Company.class).update(id, company);
    }

    @Override
    public void delete(Long id) {
        ServiceFactory.of(Company.class).delete(id);
    }

    @Override
    public void close() {
        ServiceFactory.of(Company.class).close();
    }

    @Override
    public Company createNewCompany(String name, String city, Long projectId, Long developerId) {
        Set<Project> projectSet = new HashSet<>();
        Set<Developer> developerSet = new HashSet<>();
        projectSet.add(ProjectServiceImpl.getInstance().findById(projectId).get());
        developerSet.add(DeveloperServiceImpl.getInstance().findById(developerId).get());

        return create(Company.builder()
                .name(name)
                .city(city)
                .projects(projectSet)
                .developers(developerSet)
                .build());
    }

    @Override
    public void updateCompany(Long id, String name, String city, Long projectId, Long developerId) {
        Set<Project> projectSet = new HashSet<>();
        Set<Developer> developerSet = new HashSet<>();
        projectSet.add(ProjectServiceImpl.getInstance().findById(projectId).get());
        developerSet.add(DeveloperServiceImpl.getInstance().findById(developerId).get());

        Company company = findById(id).get();
        company.setCity(city);
        company.setName(name);
        company.setProjects(projectSet);
        company.setDevelopers(developerSet);
        update(id, company);
    }
}
