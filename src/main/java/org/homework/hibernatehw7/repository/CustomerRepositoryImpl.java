package org.homework.hibernatehw7.repository;

import org.homework.hibernatehw7.model.Customer;
import org.homework.hibernatehw7.repository.interfaces.CustomerRepository;

public class CustomerRepositoryImpl extends ModelCrudRepositoryImpl<Customer, Long> implements CustomerRepository {

    private static final long serialVersionUID = 3333644651928374654L;

    public CustomerRepositoryImpl(Class<Customer> classModel) {
        super(classModel);
    }

    @Override
    public Customer createNewCustomer(String name, String city, Long budget) {
        return create(Customer.builder().city(city).name(name).budget(budget).build());
    }

    @Override
    public void updateCustomer(Long id, String name, String city, Long budget) {
        Customer customer = findById(id).get();
        customer.setBudget(budget);
        customer.setCity(city);
        customer.setName(name);
        update(id, customer);
    }
}
