package org.homework.hibernatehw7.services.interfaces;


import org.homework.hibernatehw7.model.Customer;

public interface CustomerService extends IService<Customer, Long> {

    Customer createNewCustomer(String name,String city, Long budget,Long projectId);

    void update(Long id, String name,String city, Long budget,Long projectId);
}
