package org.homework.hibernatehw7.services;

import org.homework.hibernatehw7.model.Customer;
import org.homework.hibernatehw7.model.Project;
import org.homework.hibernatehw7.services.interfaces.CustomerService;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

public class CustomerServiceImpl implements CustomerService {

    @Override
    public Customer createNewCustomer(String name, String city, Long budget, Long projectId) {
        Set<Project> projectSet = new HashSet<>();
        projectSet.add(ServiceFactory.of(Project.class).findById(projectId).get());

        return create(Customer.builder()
                .city(city)
                .name(name)
                .budget(budget)
                .projects(projectSet)
                .build());
    }

    @Override
    public void updateCustomer(Long id, String name, String city, Long budget, Long companyId, Long projectId) {
        Set<Project> projectSet = new HashSet<>();
        projectSet.add(ServiceFactory.of(Project.class).findById(projectId).get());

        Customer customer = findById(id).get();
        customer.setBudget(budget);
        customer.setCity(city);
        customer.setName(name);
        customer.setProjects(projectSet);
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
