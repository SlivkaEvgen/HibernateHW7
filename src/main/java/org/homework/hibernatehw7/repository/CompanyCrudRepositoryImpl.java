package org.homework.hibernatehw7.repository;

import org.homework.hibernatehw7.model.Company;
import org.homework.hibernatehw7.repository.interfaces.CompanyCrudRepository;
import org.homework.hibernatehw7.repository.interfaces.CrudRepositoryJDBC;

import java.util.List;
import java.util.Optional;

public class CompanyCrudRepositoryImpl implements CompanyCrudRepository {

    private final CrudRepositoryJDBC<Company, Long> CRUD_REPOSITORY_JDBC = RepositoryFactory.of(Company.class);
    private static CompanyCrudRepositoryImpl companyCrudRepository;

    public static CompanyCrudRepositoryImpl getInstance() {
        if (companyCrudRepository == null) {
            synchronized (CompanyCrudRepositoryImpl.class) {
                if (companyCrudRepository == null) {
                    companyCrudRepository = new CompanyCrudRepositoryImpl();
                }
            }
        }
        return companyCrudRepository;
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

    @Override
    public Optional<Company> findById(Long id) {
        return CRUD_REPOSITORY_JDBC.findById(id);
    }

    @Override
    public List<Company> findAll() {
        return CRUD_REPOSITORY_JDBC.findAll();
    }

    @Override
    public Company create(Company company) {
        return CRUD_REPOSITORY_JDBC.create(company);
    }

    @Override
    public Company update(Long id, Company company) {
        return CRUD_REPOSITORY_JDBC.update(id, company);
    }

    @Override
    public void delete(Long id) {
        CRUD_REPOSITORY_JDBC.delete(id);
    }

    @Override
    public void close() {
        CRUD_REPOSITORY_JDBC.close();
    }
}
