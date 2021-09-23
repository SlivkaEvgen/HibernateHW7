package org.homework.hibernatehw7.services;

import org.homework.hibernatehw7.model.Company;
import org.homework.hibernatehw7.model.Customer;
import org.homework.hibernatehw7.model.Project;
import org.homework.hibernatehw7.repository.CrudRepositoryHibernateImpl;
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
    public void update(Long id, String name, String city, Long budget, Long companyId, Long projectId) {
        Set<Project> projectSet = new HashSet<>();
        CrudRepositoryHibernateImpl<Project, Long> repositoryHibernate = new CrudRepositoryHibernateImpl<>(Project.class);
        Project project = repositoryHibernate.findById(1L).get();
        projectSet.add(project);

        CrudRepositoryHibernateImpl<Company, Long> repositoryHibernate2 = new CrudRepositoryHibernateImpl<>(Company.class);
        Company company = repositoryHibernate2.findById(companyId).get();

        Customer customer = CRUD_REPOSITORY_CUSTOMER.findById(id).get();
        customer.setBudget(budget);
        customer.setCity(city);
        customer.setName(name);
        customer.setCompany(company);
        customer.setProjects(projectSet);
        CRUD_REPOSITORY_CUSTOMER.update(id, customer);
    }

    @Override
    public void delete(Long id) {
        CRUD_REPOSITORY_CUSTOMER.delete(id);
    }

    @Override
    public Customer createNewCustomer(String name, String city, Long budget, Long projectId) {//Long projectId
        Set<Project> projectSet = new HashSet<>();
        CrudRepositoryHibernateImpl<Project, Long> repositoryHibernate = new CrudRepositoryHibernateImpl<>(Project.class);
        Project project = repositoryHibernate.findById(projectId).get();
        projectSet.add(project);
        Company company = project.getCompany();
        System.out.println(company);
        CrudRepositoryHibernateImpl<Company, Long> companyLongCrudRepositoryHibernate = new CrudRepositoryHibernateImpl<>(Company.class);
        Company company1 = companyLongCrudRepositoryHibernate.findById(1L).get();
        System.out.println(company1);
//        CrudRepositoryHibernateImpl<Project, Long> repositoryHibernate2 = new CrudRepositoryHibernateImpl<>(Project.class);
//        repositoryHibernate2.findById()

        Customer customer = Customer.builder()
                .city(city)
                .name(name)
                .budget(budget)
                .company(company)
                .projects(projectSet)
                .build();
        System.out.println(customer);
        return CRUD_REPOSITORY_CUSTOMER.create(customer);
    }
}
