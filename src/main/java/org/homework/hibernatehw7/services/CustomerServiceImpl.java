package org.homework.hibernatehw7.services;

import org.homework.hibernatehw7.model.Customer;
import org.homework.hibernatehw7.model.Project;
import org.homework.hibernatehw7.repository.CrudRepositoryHibernateImpl;
import org.homework.hibernatehw7.repository.RepositoryFactory;
import org.homework.hibernatehw7.repository.interfaces.CrudRepositoryJDBC;
import org.homework.hibernatehw7.services.interfaces.CustomerService;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

public class CustomerServiceImpl implements CustomerService {

    private final CrudRepositoryHibernateImpl<Customer, Long> CRUD_REPOSITORY_CUSTOMER = new CrudRepositoryHibernateImpl<>(Customer.class);

    @Override
    public Optional<Customer> getById(Long id) {
        return CRUD_REPOSITORY_CUSTOMER.findById(id);
    }

    @Override
    public List<Customer> getAll() {
        return CRUD_REPOSITORY_CUSTOMER.findAll();
    }

    @Override
    public void update(Long id, String name, String city, Long budget, Long projectId) {
        Set<Project> projectSet = new HashSet<>();
        ProjectServiceImpl projectService = new ProjectServiceImpl();
        Optional<Project> optionalProject = projectService.getById(projectId);
        Project project = optionalProject.get();
        System.out.println(project);
        projectSet.add(project);
//        CrudRepositoryHibernateImpl crudRepositoryHibernate = new CrudRepositoryHibernateImpl(Project.class);
//        CrudRepositoryJDBC<Project, Long> repositoryJDBC = RepositoryFactory.of(Project.class);
//        Project project = repositoryJDBC.findById(projectId).get();
//        projectSet.add(project);
        Customer customer = CRUD_REPOSITORY_CUSTOMER.findById(id).get();
        customer.setBudget(budget);
        customer.setCity(city);
        customer.setName(name);
        customer.setProjects(projectSet);
        System.out.println(customer);
        CRUD_REPOSITORY_CUSTOMER.update(id, customer);
    }

    @Override
    public void delete(Long id) {
        CRUD_REPOSITORY_CUSTOMER.delete(id);
    }

    @Override
    public Customer createNewCustomer(String name, String city, Long budget,Long projectId) {
        Set<Project> projectSet = new HashSet<>();
        ProjectServiceImpl projectService = new ProjectServiceImpl();
        Optional<Project> optionalProject = projectService.getById(projectId);
        projectSet.add(optionalProject.get());
        Customer customer = Customer.builder()
                .city(city)
                .name(name)
                .budget(budget)
                .projects(projectSet)
                .build();
        return CRUD_REPOSITORY_CUSTOMER.create(customer);
    }
}
