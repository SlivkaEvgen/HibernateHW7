package org.homework.hibernatehw7.services;

import org.homework.hibernatehw7.model.Company;
import org.homework.hibernatehw7.services.interfaces.CompanyService;

import java.util.List;
import java.util.Optional;

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
    public Company createNewCompany(String name, String city) {
        return create(Company.builder().name(name).city(city).build());
    }

    @Override
    public void updateCompany(Long id, String name, String city) {
//        Set<Project> projectSet = new HashSet<>();
//        Set<Developer> developerSet = new HashSet<>();
//        projectSet.add(ProjectServiceImpl.getInstance().findById(projectId).get());
//        developerSet.add(DeveloperServiceImpl.getInstance().findById(developerId).get());

        Company company = findById(id).get();
        company.setCity(city);
        company.setName(name);
//        company.setProjects(projectSet);
//        company.setDevelopers(developerSet);
        update(id, company);
    }
}
