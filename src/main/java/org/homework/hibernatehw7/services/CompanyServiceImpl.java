package org.homework.hibernatehw7.services;

import org.homework.hibernatehw7.model.Company;
import org.homework.hibernatehw7.services.interfaces.CompanyService;
import org.homework.hibernatehw7.services.interfaces.Service;

import java.util.List;
import java.util.Optional;

public class CompanyServiceImpl implements CompanyService {

    private final Service<Company, Long> COMPANY_SERVICE = ServiceFactory.of(Company.class);
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
        return COMPANY_SERVICE.findById(id);
    }

    @Override
    public List<Company> findAll() {
        return COMPANY_SERVICE.findAll();
    }

    @Override
    public Company create(Company company) {
        return COMPANY_SERVICE.create(company);
    }

    @Override
    public Company update(Long id, Company company) {
        return COMPANY_SERVICE.update(id, company);
    }

    @Override
    public void delete(Long id) {
        COMPANY_SERVICE.delete(id);
    }

    @Override
    public void close() {
        COMPANY_SERVICE.close();
    }

    @Override
    public Company createNewCompany(String name, String city) {
        return COMPANY_SERVICE.create(Company.builder().name(name).city(city).build());
    }

    @Override
    public void updateCompany(Long id, String name, String city) {
        Company company = findById(id).get();
        company.setCity(city);
        company.setName(name);
        COMPANY_SERVICE.update(id, company);
    }
}
