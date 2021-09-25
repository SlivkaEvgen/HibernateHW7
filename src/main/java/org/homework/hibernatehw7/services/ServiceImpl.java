package org.homework.hibernatehw7.services;

import org.homework.hibernatehw7.model.*;
import org.homework.hibernatehw7.repository.RepositoryFactory;
import org.homework.hibernatehw7.services.interfaces.Service;

import java.io.Closeable;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class ServiceImpl<T extends BaseModel<ID>, ID> implements Closeable, Service<T, ID> {

    private final Class<T> modelClass;

    private static ServiceImpl service;

    public static ServiceImpl getInstance(Class modelClass){
        if (service == null) {
            synchronized (ServiceImpl.class) {
                if (service == null) {
                    service = new ServiceImpl(modelClass);
                }
            }
        }
        return service;
    }

    public ServiceImpl(Class<T> modelClass) {
        this.modelClass = modelClass;
    }

    @Override
    public Optional<T> findById(ID id) {
        return RepositoryFactory.of(modelClass).findById(id);
    }

    @Override
    public List<T> findAll() {
        return RepositoryFactory.of(modelClass).findAll();
    }

    @Override
    public T create(T t) {
        return RepositoryFactory.of(modelClass).create(t);
    }

    @Override
    public T update(ID id, T t) {
        return RepositoryFactory.of(modelClass).update(id, t);
    }

    @Override
    public void delete(ID id) {
        RepositoryFactory.of(modelClass).delete(id);
    }

    @Override
    public void close() {
        close();
    }

    @Override
    public T createNewSkill(String activity, String level) {
        return create((T) Skill.builder().activity(activity).level(level).build());
    }

    public T createNewProject(String name, Long cost, ID companyId, ID customerId, ID developerId) {
        Set<Developer> developers = new HashSet<>();
        developers.add(ServiceFactory.of(Developer.class).findById((Long) developerId).get());

        return create((T) Project.builder()
                .name(name)
                .cost(cost)
                .company(ServiceFactory.of(Company.class).findById((Long) companyId).get())
                .customer(ServiceFactory.of(Customer.class).findById((Long) customerId).get())
                .developers(developers)
                .build());
    }

    @Override
    public void updateSkill(ID id, String activity, String level, ID developerId) {
        Set<Developer> developerSet = new HashSet<>();
        developerSet.add(ServiceFactory.of(Developer.class).findById((Long) developerId).get());

        Skill skill = (Skill) findById(id).get();
        skill.setActivity(activity);
        skill.setLevel(level);
        skill.setDevelopers(developerSet);
        update(id, (T) skill);
    }

    @Override
    public void updateProject(ID id, String name, Long cost, ID companyId, ID customerId, ID developerId) {
        Set<Developer> developerSet = new HashSet<>();
        developerSet.add(ServiceFactory.of(Developer.class).findById((Long) developerId).get());

        Project project = (Project) findById(id).get();
        project.setName(name);
        project.setCost(cost);
        project.setCompany(ServiceFactory.of(Company.class).findById((Long) companyId).get());
        project.setCustomer(ServiceFactory.of(Customer.class).findById((Long) customerId).get());
        project.setDevelopers(developerSet);
        update(id, (T) project);
    }

    @Override
    public List<String> getListProjectsWithDate() {
        return IntStream.range(0, findAll().size())
                .mapToObj(i -> "In project " +
                               ServiceFactory.of(Developer.class).findAll().get(i).getName() + " - " +
                               countDevelopers(i + 1) + " developers, signs - " +
                               ServiceFactory.of(Project.class).findAll().get(i).getFirstDate())
                .collect(Collectors.toList());
    }

    private int countDevelopers(int projectId) {
        return ServiceFactory.of(Developer.class).getDevelopersFromOneProject((long) projectId).size();
    }

    @Override
    public T createNewDeveloper(String name, Long age, String gender, String email, Long salary, ID skillId, ID companyId, ID projectId) { // Long companyId, Long projectId,
        Set<Skill> skillSet1 = new HashSet<>();
        Set<Project> projectSet = new HashSet<>();
        skillSet1.add(ServiceFactory.of(Skill.class).findById((Long) skillId).get());
        projectSet.add(ServiceFactory.of(Project.class).findById((Long) projectId).get());

        return create((T) Developer.builder()
                .name(name)
                .age(age)
                .gender(gender)
                .email(email)
                .salary(salary)
                .skills(skillSet1)
                .company(ServiceFactory.of(Company.class).findById((Long) companyId).get())
                .projects(projectSet)
                .build());
    }

    @Override
    public void updateDeveloper(ID id, String name, Long age, String gender, String email, Long salary, ID skillId, ID companyId, ID projectId) {
        Set<Skill> skillSet = new HashSet<>();
        Set<Project> projectSet = new HashSet<>();
        skillSet.add(ServiceFactory.of(Skill.class).findById((Long) skillId).get());
        projectSet.add(ServiceFactory.of(Project.class).findById((Long) projectId).get());

        Developer developer = (Developer) findById(id).get();
        developer.setName(name);
        developer.setAge(age);
        developer.setGender(gender);
        developer.setEmail(email);
        developer.setSalary(salary);
        developer.setCompany(ServiceFactory.of(Company.class).findById((Long) companyId).get());
        developer.setSkills(skillSet);
        developer.setProjects(projectSet);
        update(id, (T) developer);
    }

    @Override
    public ID getSumSalariesDevelopersOfOneProject(ID projectId) {
        Long sumSalaries = 0L;
        for (Developer developer : (List<Developer>) getDevelopersFromOneProject(projectId)) {
            Long salary = developer.getSalary();
            sumSalaries += salary;
        }
        return (ID) sumSalaries;
    }

    @Override
    public List<T> getDevelopersFromOneProject(ID projectId) {
        List<Developer> developersOfOneProject = new ArrayList<>();
        for (Developer developer : (List<Developer>) findAll()) {
            for (Project project : new ArrayList<>(developer.getProjects())) {
                if (project.getId().equals(projectId)) {
                    developersOfOneProject.add(developer);
                }
            }
        }
        return (List<T>) developersOfOneProject;
    }

    @Override
    public List<T> getDevelopersByActivity(String nameActivity) {
        List<Developer> developersActivity = new ArrayList<>();
        for (Developer developer : (List<Developer>) findAll()) {
            for (Skill skill : new ArrayList<>(developer.getSkills())) {
                if (skill.getActivity().equalsIgnoreCase(nameActivity)) {
                    if (developersActivity.contains(developer)) {
                        continue;
                    }
                    developersActivity.add(developer);
                }
            }
        }
        return (List<T>) developersActivity;
    }

    @Override
    public List<T> getDevelopersByLevel(String nameLevel) {
        List<Developer> developersActivity = new ArrayList<>();
        for (Developer developer : (List<Developer>) findAll()) {
            for (Skill skill : new ArrayList<>(developer.getSkills())) {
                if (skill.getLevel().equalsIgnoreCase(nameLevel)) {
                    if (developersActivity.contains(developer)) {
                        continue;
                    }
                    developersActivity.add(developer);
                }
            }
        }
        return (List<T>) developersActivity;
    }

    @Override
    public T createNewCustomer(String name, String city, Long budget, ID projectId) {
        Set<Project> projectSet = new HashSet<>();
        projectSet.add(ServiceFactory.of(Project.class).findById((Long) projectId).get());

        return create((T) Customer.builder()
                .city(city)
                .name(name)
                .budget(budget)
                .projects(projectSet)
                .build());
    }

    @Override
    public void updateCustomer(ID id, String name, String city, Long budget, ID companyId, ID projectId) {
        Set<Project> projectSet = new HashSet<>();
        projectSet.add(ServiceFactory.of(Project.class).findById((Long) projectId).get());

        Customer customer = (Customer) findById(id).get();
        customer.setBudget(budget);
        customer.setCity(city);
        customer.setName(name);
        customer.setProjects(projectSet);
        update(id, (T) customer);
    }

    @Override
    public T createNewCompany(String name, String city, ID projectId, ID developerId) {
        Set<Project> projectSet = new HashSet<>();
        Set<Developer> developerSet = new HashSet<>();
        projectSet.add(ServiceFactory.of(Project.class).findById((Long) projectId).get());
        developerSet.add(ServiceFactory.of(Developer.class).findById((Long) developerId).get());

        return create((T) Company.builder()
                .name(name)
                .city(city)
                .projects(projectSet)
                .developers(developerSet)
                .build());
    }

    @Override
    public void updateCompany(ID id, String name, String city, ID projectId, ID developerId) {
        Set<Project> projectSet = new HashSet<>();
        Set<Developer> developerSet = new HashSet<>();
        projectSet.add(ServiceFactory.of(Project.class).findById((Long) projectId).get());
        developerSet.add(ServiceFactory.of(Developer.class).findById((Long) developerId).get());

        Company company = (Company) findById(id).get();
        company.setCity(city);
        company.setName(name);
        company.setProjects(projectSet);
        company.setDevelopers(developerSet);
        update(id, (T) company);
    }
}
