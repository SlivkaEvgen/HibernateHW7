package org.homework.hibernatehw7.services;

import org.homework.hibernatehw7.model.Customer;
import org.homework.hibernatehw7.services.interfaces.CustomerService;

import java.util.List;
import java.util.Optional;

public class CustomerServiceImpl implements CustomerService {

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
        return create(Customer.builder().city(city).name(name).budget(budget).build());
    }

    @Override
    public void updateCustomer(Long id, String name, String city, Long budget) {
//        Set<Project> projectSet = new HashSet<>();
//        projectSet.add(ProjectServiceImpl.getInstance().findById(projectId).get());
        Customer customer = findById(id).get();
        customer.setBudget(budget);
        customer.setCity(city);
        customer.setName(name);
//        customer.setProjects(projectSet);
        update(id, customer);
    }

    @Override
    public Optional<Customer> findById(Long id) {
        return ServiceFactory.of(Customer.class).findById(id);
    }

    @Override
    public List<Customer> findAll() {
        return ServiceFactory.of(Customer.class).findAll();
    }

    @Override
    public Customer create(Customer customer) {
        return ServiceFactory.of(Customer.class).create(customer);
    }

    @Override
    public Customer update(Long id, Customer customer) {
        return ServiceFactory.of(Customer.class).update(id, customer);
    }

    @Override
    public void delete(Long id) {
        ServiceFactory.of(Customer.class).delete(id);
    }

    @Override
    public void close() {
        ServiceFactory.of(Customer.class).close();
    }
}
