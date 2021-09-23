package org.homework.hibernatehw7.services;

import org.homework.hibernatehw7.model.Company;
import org.homework.hibernatehw7.model.Customer;
import org.homework.hibernatehw7.model.Developer;
import org.homework.hibernatehw7.model.Project;
import org.homework.hibernatehw7.repository.CrudRepositoryHibernateImpl;
import org.homework.hibernatehw7.services.interfaces.ProjectService;

import java.util.*;

public class ProjectServiceImpl implements ProjectService {

    private final CrudRepositoryHibernateImpl<Project, Long> CRUD_REPOSITORY_PROJECT = new CrudRepositoryHibernateImpl<>(Project.class);

    @Override
    public Optional<Project> getById(Long id) {
        return CRUD_REPOSITORY_PROJECT.findById(id);
    }

    @Override
    public List<Project> getAll() {
        return CRUD_REPOSITORY_PROJECT.findAll();
    }

    @Override
    public Project createNewProject(String name, Long cost,Long companyId) {
//        Set<Developer>developerSet = new HashSet<>();
//        CrudRepositoryHibernateImpl<Developer, Long> repositoryHibernate = new CrudRepositoryHibernateImpl<>(Developer.class);
//        Developer developer = repositoryHibernate.findById(developerId).get();
//        developerSet.add(developer);

        Company company = new CompanyServiceImpl().getById(companyId).get();
//        Set<Customer> customers = company.getCustomers();
        Set<Developer> developers = company.getDevelopers();
//        Set<Customer> customers = company.getCustomers();
//        Customer customer = new CustomerServiceImpl().getById(customerId).get();

        Project project =new Project();
        project.setName(name);
        project.setCost(cost);
        project.setCompany(company);
//        project.setCustomer(customer);
        project.setDevelopers(developers);
        return CRUD_REPOSITORY_PROJECT.create(project);
    }

    @Override
    public void update(Long id, String name, Long cost,Long companyId,Long customerId,Long developerId) {
        Set<Developer>developerSet = new HashSet<>();
        CrudRepositoryHibernateImpl<Developer, Long> repositoryHibernate = new CrudRepositoryHibernateImpl<>(Developer.class);
        Developer developer = repositoryHibernate.findById(developerId).get();
        developerSet.add(developer);

        Company company = new CompanyServiceImpl().getById(companyId).get();
        Customer customer = new CustomerServiceImpl().getById(customerId).get();

        Project project = CRUD_REPOSITORY_PROJECT.findById(id).get();
        project.setName(name);
        project.setCost(cost);
        project.setCompany(company);
        project.setCustomer(customer);
        project.setDevelopers(developerSet);
        CRUD_REPOSITORY_PROJECT.update(id, project);
    }

    @Override
    public void delete(Long id) {
        CRUD_REPOSITORY_PROJECT.delete(id);
    }

    @Override
    public List<String> getListProjectsWithDate() {
        List<Project> projects = CRUD_REPOSITORY_PROJECT.findAll();
        List<String> listProjects = new ArrayList<>();
        for (int i = 0; i< projects.size(); i++) {
            Project project = projects.get(i);
            int count = countDevelopers(i);
            String name = project.getName();
            String firstDate = project.getFirstDate();
            String listWithDate = "In project " + name + " - " + count + " developers, signs - " + firstDate;
            listProjects.add(listWithDate);
        }
        return listProjects;
    }

    private int countDevelopers(int projectId) {
        DeveloperServiceImpl developerService = new DeveloperServiceImpl();
        return developerService.getDevelopersFromOneProject((long) projectId).size();
    }
}
