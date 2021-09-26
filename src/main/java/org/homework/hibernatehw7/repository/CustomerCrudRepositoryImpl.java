package org.homework.hibernatehw7.repository;

import org.homework.hibernatehw7.model.Customer;
import org.homework.hibernatehw7.repository.interfaces.CrudRepositoryJDBC;
import org.homework.hibernatehw7.repository.interfaces.CustomerCrudRepository;

import java.util.List;
import java.util.Optional;

public class CustomerCrudRepositoryImpl implements CustomerCrudRepository {

    private final CrudRepositoryJDBC<Customer, Long> CRUD_REPOSITORY_JDBC = RepositoryFactory.of(Customer.class);
    private static CustomerCrudRepositoryImpl customerCrudRepository;

    public static CustomerCrudRepositoryImpl getInstance() {
        if (customerCrudRepository == null) {
            synchronized (CustomerCrudRepositoryImpl.class) {
                if (customerCrudRepository == null) {
                    customerCrudRepository = new CustomerCrudRepositoryImpl();
                }
            }
        }
        return customerCrudRepository;
    }

    @Override
    public Optional<Customer> findById(Long id) {
        return CRUD_REPOSITORY_JDBC.findById(id);
    }

    @Override
    public List<Customer> findAll() {
        return CRUD_REPOSITORY_JDBC.findAll();
    }

    @Override
    public Customer create(Customer customer) {
        return CRUD_REPOSITORY_JDBC.create(customer);
    }

    @Override
    public Customer update(Long id, Customer customer) {
        return CRUD_REPOSITORY_JDBC.update(id, customer);
    }

    @Override
    public void delete(Long id) {
        CRUD_REPOSITORY_JDBC.delete(id);
    }

    @Override
    public void close() {
        CRUD_REPOSITORY_JDBC.close();
    }

    @Override
    public Customer createNewCustomer(String name, String city, Long budget) {
        return CRUD_REPOSITORY_JDBC.create(Customer.builder().city(city).name(name).budget(budget).build());
    }

    @Override
    public void updateCustomer(Long id, String name, String city, Long budget) {
        Customer customer = findById(id).get();
        customer.setBudget(budget);
        customer.setCity(city);
        customer.setName(name);
        CRUD_REPOSITORY_JDBC.update(id, customer);
    }
}
