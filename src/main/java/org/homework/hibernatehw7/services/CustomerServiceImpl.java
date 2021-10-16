package org.homework.hibernatehw7.services;

import org.homework.hibernatehw7.model.Customer;
import org.homework.hibernatehw7.repository.CustomerRepositoryImpl;
import org.homework.hibernatehw7.repository.interfaces.CustomerRepository;
import org.homework.hibernatehw7.services.interfaces.CustomerService;

public class CustomerServiceImpl extends ModelService<Customer, Long> implements CustomerService {

    private static final long serialVersionUID = 3334344651928374654L;
    private final CustomerRepository CRUD_REPOSITORY = new CustomerRepositoryImpl(Customer.class);

    public CustomerServiceImpl(Class<Customer> classModel) {
        super(classModel);
    }

    @Override
    public Customer createNewCustomer(String name, String city, Long budget) {
        return CRUD_REPOSITORY.createNewCustomer(name, city, budget);
    }

    @Override
    public void updateCustomer(Long id, String name, String city, Long budget) {
        CRUD_REPOSITORY.updateCustomer(id, name, city, budget);
    }
}
