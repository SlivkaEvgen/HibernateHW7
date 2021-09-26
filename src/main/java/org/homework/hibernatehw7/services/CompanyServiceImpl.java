package org.homework.hibernatehw7.services;

import org.homework.hibernatehw7.model.Company;
import org.homework.hibernatehw7.repository.CompanyCrudRepositoryImpl;
import org.homework.hibernatehw7.services.interfaces.CompanyService;

import java.util.List;
import java.util.Optional;

public class CompanyServiceImpl implements CompanyService {

    private final CompanyCrudRepositoryImpl COMPANY_SERVICE = CompanyCrudRepositoryImpl.getInstance();
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
        return COMPANY_SERVICE.createNewCompany(name, city);
    }

    @Override
    public void updateCompany(Long id, String name, String city) {
        COMPANY_SERVICE.updateCompany(id, name, city);
    }
}
