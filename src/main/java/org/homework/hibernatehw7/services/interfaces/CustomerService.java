package org.homework.hibernatehw7.services.interfaces;

import org.homework.hibernatehw7.model.Customer;

public interface CustomerService extends Service<Customer, Long> {

    Customer createNewCustomer(String name, String city, Long budget, Long projectId);

    void updateCustomer(Long id, String name, String city, Long budget, Long companyId, Long projectId);

}
