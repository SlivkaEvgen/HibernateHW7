package org.homework.hibernatehw7.services;

import org.homework.hibernatehw7.model.Company;
import org.homework.hibernatehw7.repository.CompanyRepositoryImpl;
import org.homework.hibernatehw7.repository.interfaces.CompanyRepository;
import org.homework.hibernatehw7.services.interfaces.CompanyService;

public class CompanyServiceImpl extends ModelService<Company, Long> implements CompanyService {

    private static final long serialVersionUID = 3334244651928374654L;
    private final CompanyRepository COMPANY_SERVICE = new CompanyRepositoryImpl(Company.class);

    public CompanyServiceImpl(Class<Company> classModel) {
        super(classModel);
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
