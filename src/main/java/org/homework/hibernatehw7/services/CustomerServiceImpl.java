package org.homework.hibernatehw7.services;

import org.homework.hibernatehw7.model.Customer;
import org.homework.hibernatehw7.services.interfaces.CustomerService;
import org.homework.hibernatehw7.services.interfaces.Service;

import java.util.List;
import java.util.Optional;

public class CustomerServiceImpl implements CustomerService {

    private final Service<Customer, Long> CUSTOMER_SERVICE = ServiceFactory.of(Customer.class);
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
        return CUSTOMER_SERVICE.create(Customer.builder().city(city).name(name).budget(budget).build());
    }

    @Override
    public void updateCustomer(Long id, String name, String city, Long budget) {
        Customer customer = findById(id).get();
        customer.setBudget(budget);
        customer.setCity(city);
        customer.setName(name);
        CUSTOMER_SERVICE.update(id, customer);
    }

    @Override
    public Optional<Customer> findById(Long id) {
        return CUSTOMER_SERVICE.findById(id);
    }

    @Override
    public List<Customer> findAll() {
        return CUSTOMER_SERVICE.findAll();
    }

    @Override
    public Customer create(Customer customer) {
        return CUSTOMER_SERVICE.create(customer);
    }

    @Override
    public Customer update(Long id, Customer customer) {
        return CUSTOMER_SERVICE.update(id, customer);
    }

    @Override
    public void delete(Long id) {
        CUSTOMER_SERVICE.delete(id);
    }

    @Override
    public void close() {
        CUSTOMER_SERVICE.close();
    }
}
