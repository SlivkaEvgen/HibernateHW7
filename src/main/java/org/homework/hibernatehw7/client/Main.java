package org.homework.hibernatehw7.client;

import com.sun.xml.internal.xsom.impl.scd.Iterators;
import org.homework.hibernatehw7.model.Company;
import org.homework.hibernatehw7.model.Customer;
import org.homework.hibernatehw7.model.Developer;
import org.homework.hibernatehw7.model.Project;
import org.homework.hibernatehw7.repository.CrudRepositoryHibernateImpl;
import org.homework.hibernatehw7.services.DeveloperServiceImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class Main {

    public static void main(String[] args) {
//        new CommandImpl().start();
//        CrudRepositoryHibernateImpl<DevelopersProjects, Long> crudRepositoryHibernate = new CrudRepositoryHibernateImpl<>(DevelopersProjects.class);
//        Optional<DevelopersProjects> developersProjects = crudRepositoryHibernate.findById(1L);
//        System.out.println(developersProjects.get());
//        System.out.println(sum);
//        List<Company> companies = crudRepositoryHibernate.findAll();
//        System.out.println(companies);

//        CrudRepositoryHibernateImpl<Customer, Long> crudRepositoryHibernate1 = new CrudRepositoryHibernateImpl<>(Customer.class);
//        List<Customer> customers = crudRepositoryHibernate1.findAll();
//        System.out.println(customers);
//
//        CrudRepositoryHibernateImpl<Project, Long> crudRepositoryHibernate2 = new CrudRepositoryHibernateImpl<>(Project.class);
//        List<Project> projects = crudRepositoryHibernate2.findAll();
//        System.out.println(projects);
        DeveloperServiceImpl developerService = new DeveloperServiceImpl();
        List<Developer> middle = developerService.getDevelopersByLevel("middle");
        List<Developer> java = developerService.getDevelopersByActivity("java");
        System.out.println(middle);
        System.out.println(java);

//        Long sumSalariesDevelopersOfOneProject = developerService.getSumSalariesDevelopersOfOneProject(5L);
//        System.out.println(sumSalariesDevelopersOfOneProject);
//        List<Developer> developersFromOneProject = developerService.getDevelopersFromOneProject(1L);
//        System.out.println(developersFromOneProject);
//        CrudRepositoryHibernateImpl<Developer, Long> crudRepositoryHibernate3 = new CrudRepositoryHibernateImpl<>(Developer.class);
//        List<Developer> developers = crudRepositoryHibernate3.findAll();
//        System.out.println(developers);
//        System.out.println(developers.get(1).getProjects());
//        Set<Project> projectSet = developers.get(1).getProjects();
//        List<Project> arrayList = new ArrayList<>(projectSet);
//        Long id = arrayList.get(0).getId();
//        System.out.println(id);
//        CrudRepositoryHibernateImpl<Company, Long> crudRepositoryHibernate4 = new CrudRepositoryHibernateImpl<>(Company.class);
//        List<Company> companies = crudRepositoryHibernate4.findAll();
//        System.out.println(companies);
//
//        Optional<Skill> skill = crudRepositoryHibernate4.findById(5L);
//        Optional<Developer> developer = crudRepositoryHibernate3.findById(2L);
//        Optional<Project> project = crudRepositoryHibernate2.findById(2L);
//        Optional<Customer> customer = crudRepositoryHibernate1.findById(2L);
//        Optional<Company> company = crudRepositoryHibernate.findById(2L);
//
//        System.out.println(company.get());
//        System.out.println(project.get());
//        System.out.println(developer.get());
//        System.out.println(skill.get());
//        System.out.println(customer.get());
//        SkillServiceImpl skillService = new SkillServiceImpl();
//        List<Skill> skills = skillService.getAll();
//        System.out.println(skills);
//        Optional<Skill> skillOptional = skillService.getById(1L);
//        System.out.println(skillOptional);
//        crudRepositoryHibernate.delete(1L);
//        crudRepositoryHibernate1.delete(1L);
//        crudRepositoryHibernate2.delete(1L);
//        crudRepositoryHibernate3.delete(1L);
//        crudRepositoryHibernate4.delete(1L);
//        Company company1 = crudRepositoryHibernate.findById(3L).get();
//        company1.setName("TEST");
//        crudRepositoryHibernate.update(3L, company1);
//
//        Customer customer1 = crudRepositoryHibernate1.findById(1L).get();
//        customer1.setName("TEST");
//        crudRepositoryHibernate1.update(1L, customer1);
//
//        Project project1 = crudRepositoryHibernate2.findById(1L).get();
//        project1.setName("TEST");
//        crudRepositoryHibernate2.update(1L, project1);
//
//        Developer developer1 = crudRepositoryHibernate3.findById(1L).get();
//        developer1.setName("TEST");
//        crudRepositoryHibernate3.update(1L, developer1);
//
//        Skill skill1 = crudRepositoryHibernate4.findById(1L).get();
//        skill1.setActivities("C++");
//        skill1.setLevel("MIDDLE");
//        crudRepositoryHibernate4.update(1L, skill1);
//        Company company1 = Company.builder().name("TestBUILDER").city("TestBUILDER").build();
//        crudRepositoryHibernate.create(company1);
//        crudRepositoryHibernate1.create(Customer.builder().name("TestBUILDER").city("TestBUILDER").budget(000000L).build());
//        crudRepositoryHibernate2.create(Project.builder().name("TestBUILDER").cost(000000L).build());
//        crudRepositoryHibernate3.create(Developer.builder().name("TestBUILDER").salary(000000L).email("ggg@").gender("Male").age(00L).build());
//        crudRepositoryHibernate4.create(Skill.builder().activities("Java").level("SENIOR").build());

//        crudRepositoryHibernate1.update(3L, Customer.builder().name("TEST").city("TEST").build());
//        crudRepositoryHibernate2.update(3L, Project.builder().name("TEST").cost(0000000L).build());
//        crudRepositoryHibernate3.update(3L, Developer.builder().name("TEST").salary(0000000L).build());
//        crudRepositoryHibernate4.update(3L, Skill.builder().activities("TEST").level("TEST").build());


    }
}
