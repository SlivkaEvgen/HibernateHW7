package org.homework.hibernatehw7.services;

import org.homework.hibernatehw7.model.Company;
import org.homework.hibernatehw7.model.Customer;
import org.homework.hibernatehw7.model.Developer;
import org.homework.hibernatehw7.model.Project;
import org.homework.hibernatehw7.repository.CrudRepositoryHibernateImpl;
import org.homework.hibernatehw7.services.interfaces.CompanyService;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

public class CompanyServiceImpl implements CompanyService {

    private final CrudRepositoryHibernateImpl<Company, Long> CRUD_REPOSITORY_COMPANY = new CrudRepositoryHibernateImpl<>(Company.class);

    @Override
    public Optional<Company> getById(Long id) {
        return CRUD_REPOSITORY_COMPANY.findById(id);
    }

    @Override
    public List<Company> getAll() {
        return CRUD_REPOSITORY_COMPANY.findAll();
    }

    @Override
    public Company createNewCompany(String name, String city,Long developerId) {//Long projectId
//        Set<Project> projectSet = new HashSet<>();
//        CrudRepositoryHibernateImpl<Project, Long> repositoryHibernate = new CrudRepositoryHibernateImpl<>(Project.class);
//        Project project = repositoryHibernate.findById(1L).get();
//        projectSet.add(project);
//
        Set<Developer> developerSet = new HashSet<>();
        CrudRepositoryHibernateImpl<Developer, Long> crudRepositoryHibernate = new CrudRepositoryHibernateImpl<>(Developer.class);
        Developer developer = crudRepositoryHibernate.findById(1L).get();
        developerSet.add(developer);

//        Set<Customer> customerSet = new HashSet<>();
//        CrudRepositoryHibernateImpl<Customer, Long> crudRepositoryHibernate = new CrudRepositoryHibernateImpl<>(Customer.class);
//        Customer customer = crudRepositoryHibernate.findById(customerId).get();
//        customerSet.add(customer);

        Company company = Company.builder()
                .name(name)
                .city(city)
//                .customers(customerSet)
//                .projects(projectSet)
                .developers(developerSet)
                .build();
        return CRUD_REPOSITORY_COMPANY.create(company);
    }

    @Override
    public void update(Long id, String name, String city,Long customerId,Long projectId,Long developerId) {
        Set<Project> projectSet = new HashSet<>();
        CrudRepositoryHibernateImpl<Project, Long> repositoryHibernate = new CrudRepositoryHibernateImpl<>(Project.class);
        Project project = repositoryHibernate.findById(projectId).get();
        projectSet.add(project);

        Set<Developer> developerSet = new HashSet<>();
        CrudRepositoryHibernateImpl<Developer, Long> crudRepositoryHibernate = new CrudRepositoryHibernateImpl<>(Developer.class);
        Developer developer = crudRepositoryHibernate.findById(developerId).get();
        developerSet.add(developer);

        Set<Customer> customerSet = new HashSet<>();
        CrudRepositoryHibernateImpl<Customer, Long> crudRepositoryHibernate2 = new CrudRepositoryHibernateImpl<>(Customer.class);
        Customer customer = crudRepositoryHibernate2.findById(customerId).get();
        customerSet.add(customer);

        Company company = CRUD_REPOSITORY_COMPANY.findById(id).get();
        company.setCity(city);
        company.setName(name);
        company.setProjects(projectSet);
        company.setDevelopers(developerSet);
        company.setCustomers(customerSet);
        CRUD_REPOSITORY_COMPANY.update(id,company);
    }

    @Override
    public void delete(Long id) {
        CRUD_REPOSITORY_COMPANY.delete(id);
    }
}
