package org.homework.hibernatehw7.services.interfaces;


import org.homework.hibernatehw7.model.Company;

public interface CompanyService extends IService<Company, Long> {

    Company createNewCompany(String name, String city,Long projectId);

    void update(Long id, String name, String city,Long projectId);
}
