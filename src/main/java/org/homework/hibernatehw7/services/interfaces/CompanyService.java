package org.homework.hibernatehw7.services.interfaces;

import org.homework.hibernatehw7.model.Company;

public interface CompanyService extends Service<Company, Long> {

    Company createNewCompany(String name, String city, Long projectId, Long developerId);

    void updateCompany(Long id, String name, String city, Long projectId, Long developerId);
}
