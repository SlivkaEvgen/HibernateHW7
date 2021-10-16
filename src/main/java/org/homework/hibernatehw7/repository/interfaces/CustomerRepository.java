package org.homework.hibernatehw7.repository.interfaces;

import org.homework.hibernatehw7.model.Customer;

public interface CustomerRepository extends CrudRepositoryJDBC<Customer, Long> {

    Customer createNewCustomer(String name, String city, Long budget);

    void updateCustomer(Long id, String name, String city, Long budget);
}
