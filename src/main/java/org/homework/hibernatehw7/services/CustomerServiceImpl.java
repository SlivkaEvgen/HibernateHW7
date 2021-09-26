package org.homework.hibernatehw7.services;

import org.homework.hibernatehw7.model.Customer;
import org.homework.hibernatehw7.repository.CustomerCrudRepositoryImpl;
import org.homework.hibernatehw7.services.interfaces.CustomerService;

import java.util.List;
import java.util.Optional;

public class CustomerServiceImpl implements CustomerService {

    private final CustomerCrudRepositoryImpl CRUD_REPOSITORY = CustomerCrudRepositoryImpl.getInstance();
    private static CustomerServiceImpl customerService;

    public static CustomerServiceImpl getInstance() {
        if (customerService == null) {
            synchronized (CustomerServiceImpl.class) {
                if (customerService == null) {
                    customerService = new CustomerServiceImpl();
                }
            }
        }
        return customerService;
    }

    @Override
    public Customer createNewCustomer(String name, String city, Long budget) {
        return CRUD_REPOSITORY.createNewCustomer(name, city, budget);
    }

    @Override
    public void updateCustomer(Long id, String name, String city, Long budget) {
        CRUD_REPOSITORY.updateCustomer(id, name, city, budget);
    }

    @Override
    public Optional<Customer> findById(Long id) {
        return CRUD_REPOSITORY.findById(id);
    }

    @Override
    public List<Customer> findAll() {
        return CRUD_REPOSITORY.findAll();
    }

    @Override
    public Customer create(Customer customer) {
        return CRUD_REPOSITORY.create(customer);
    }

    @Override
    public Customer update(Long id, Customer customer) {
        return CRUD_REPOSITORY.update(id, customer);
    }

    @Override
    public void delete(Long id) {
        CRUD_REPOSITORY.delete(id);
    }

    @Override
    public void close() {
        CRUD_REPOSITORY.close();
    }
}
