package org.homework.hibernatehw7.repository.interfaces;

import org.homework.hibernatehw7.model.Company;

public interface CompanyRepository extends CrudRepositoryJDBC<Company, Long> {

    Company createNewCompany(String name, String city);

    void updateCompany(Long id, String name, String city);
}
