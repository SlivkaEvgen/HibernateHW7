package org.homework.hibernatehw7.repository;

import org.homework.hibernatehw7.model.Company;
import org.homework.hibernatehw7.repository.interfaces.CompanyRepository;

public class CompanyRepositoryImpl extends ModelCrudRepositoryImpl<Company, Long> implements CompanyRepository {

    private static final long serialVersionUID = 3333444651928374654L;

    public CompanyRepositoryImpl(Class<Company> classModel) {
        super(classModel);
    }

    @Override
    public Company createNewCompany(String name, String city) {
        return create(Company.builder().name(name).city(city).build());
    }

    @Override
    public void updateCompany(Long id, String name, String city) {
        Company company = findById(id).get();
        company.setCity(city);
        company.setName(name);
        update(id, company);
    }
}
