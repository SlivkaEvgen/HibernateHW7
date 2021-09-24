package org.homework.hibernatehw7.services;

import org.homework.hibernatehw7.model.Customer;
import org.homework.hibernatehw7.model.Project;
import org.homework.hibernatehw7.repository.RepositoryFactory;
import org.homework.hibernatehw7.repository.interfaces.CrudRepositoryJDBC;
import org.homework.hibernatehw7.services.interfaces.CustomerService;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

public class CustomerServiceImpl implements CustomerService {

    private final CrudRepositoryJDBC<Customer, Long> CRUD_REPOSITORY_CUSTOMER = RepositoryFactory.of(Customer.class);
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
    public Optional<Customer> getById(Long id) {
        return customerService.CRUD_REPOSITORY_CUSTOMER.findById(id);
    }

    @Override
    public List<Customer> getAll() {
        return customerService.CRUD_REPOSITORY_CUSTOMER.findAll();
    }

    @Override
    public Customer createNewCustomer(String name, String city, Long budget, Long projectId) {
        Set<Project> projectSet = new HashSet<>();
        projectSet.add(ProjectServiceImpl.getInstance().getById(projectId).get());

        return customerService.CRUD_REPOSITORY_CUSTOMER.create(Customer.builder()
                .city(city)
                .name(name)
                .budget(budget)
                .projects(projectSet)
                .build());
    }

    @Override
    public void update(Long id, String name, String city, Long budget, Long companyId, Long projectId) {
        Set<Project> projectSet = new HashSet<>();
        projectSet.add(ProjectServiceImpl.getInstance().getById(projectId).get());

        Customer customer = customerService.CRUD_REPOSITORY_CUSTOMER.findById(id).get();
        customer.setBudget(budget);
        customer.setCity(city);
        customer.setName(name);
        customer.setProjects(projectSet);
        customerService.CRUD_REPOSITORY_CUSTOMER.update(id, customer);
    }

    @Override
    public void delete(Long id) {
        customerService.CRUD_REPOSITORY_CUSTOMER.delete(id);
    }
}
