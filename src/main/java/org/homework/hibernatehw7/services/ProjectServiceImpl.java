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
//    private final DeveloperServiceImpl developerService = DeveloperServiceImpl.getInstance();
//    private final CompanyServiceImpl companyService = CompanyServiceImpl.getInstance();
//    private final CustomerServiceImpl customerService = CustomerServiceImpl.getInstance();
    private static ProjectServiceImpl projectService;

    public static ProjectServiceImpl getInstance() {
        if (projectService == null) {
            synchronized (ProjectServiceImpl.class) {
                if (projectService == null) {
                    projectService = new ProjectServiceImpl();
                }
            }
        }
        return projectService;
    }
    @Override
    public Optional<Project> getById(Long id) {
        return projectService.CRUD_REPOSITORY_PROJECT.findById(id);
    }

    @Override
    public List<Project> getAll() {
        return projectService.CRUD_REPOSITORY_PROJECT.findAll();
    }

    @Override
    public Project createNewProject(String name, Long cost, Long companyId, Long customerId, Long developerId) {
//        CrudRepositoryHibernateImpl<Developer, Long> repositoryHibernate = new CrudRepositoryHibernateImpl<>(Developer.class);
//        Developer developer = repositoryHibernate.findById(developerId).get();
//        CrudRepositoryHibernateImpl<Company, Long> repositoryHibernate2 = new CrudRepositoryHibernateImpl<>(Company.class);
//        Company company = repositoryHibernate2.findById(companyId).get();
//        CrudRepositoryHibernateImpl<Customer, Long> repositoryHibernate3 = new CrudRepositoryHibernateImpl<>(Customer.class);
//        Customer customer = repositoryHibernate3.findById(customerId).get();
//        Developer developer = DeveloperServiceImpl.getInstance().getById(developerId).get();
        Set<Developer> developers = new HashSet<>();
        developers.add(DeveloperServiceImpl.getInstance().getById(developerId).get());
        Customer customer = CustomerServiceImpl.getInstance().getById(customerId).get();
        Company company = CompanyServiceImpl.getInstance().getById(companyId).get();

        Project project = new Project();
        project.setName(name);
        project.setCost(cost);
        project.setCompany(company);
        project.setCustomer(customer);
        project.setDevelopers(developers);
        return projectService.CRUD_REPOSITORY_PROJECT.create(project);
    }

    @Override
    public void update(Long id, String name, Long cost, Long companyId, Long customerId, Long developerId) {
//        CrudRepositoryHibernateImpl<Developer, Long> repositoryHibernate = new CrudRepositoryHibernateImpl<>(Developer.class);
//        Developer developer = repositoryHibernate.findById(developerId).get();
        Set<Developer> developerSet = new HashSet<>();
//        Developer developer = DeveloperServiceImpl.getInstance().getById(developerId).get();
        developerSet.add(DeveloperServiceImpl.getInstance().getById(developerId).get());

        Customer customer = CustomerServiceImpl.getInstance().getById(customerId).get();
        Company company = CompanyServiceImpl.getInstance().getById(companyId).get();

        Project project = projectService.CRUD_REPOSITORY_PROJECT.findById(id).get();
        project.setName(name);
        project.setCost(cost);
        project.setCompany(company);
        project.setCustomer(customer);
        project.setDevelopers(developerSet);
        projectService.CRUD_REPOSITORY_PROJECT.update(id, project);
    }

    @Override
    public void delete(Long id) {
        projectService.CRUD_REPOSITORY_PROJECT.delete(id);
    }

    @Override
    public List<String> getListProjectsWithDate() {
        List<Project> projects = projectService.CRUD_REPOSITORY_PROJECT.findAll();
        List<String> listProjects = new ArrayList<>();
        for (int i = 0; i < projects.size(); i++) {
            Project project = projects.get(i);
            int count = countDevelopers(i + 1);
            String name = project.getName();
            String firstDate = project.getFirstDate();
            String listWithDate = "In project " + name + " - " + count + " developers, signs - " + firstDate;
            listProjects.add(listWithDate);
        }
        return listProjects;
    }

    private int countDevelopers(int projectId) {
        return DeveloperServiceImpl.getInstance().getDevelopersFromOneProject((long) projectId).size();
    }
}
