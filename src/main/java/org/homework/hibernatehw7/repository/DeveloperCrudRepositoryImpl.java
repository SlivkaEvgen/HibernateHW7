package org.homework.hibernatehw7.repository;

import org.homework.hibernatehw7.model.Developer;
import org.homework.hibernatehw7.model.Project;
import org.homework.hibernatehw7.model.Skill;
import org.homework.hibernatehw7.repository.interfaces.CrudRepositoryJDBC;
import org.homework.hibernatehw7.repository.interfaces.DeveloperCrudRepository;
import org.homework.hibernatehw7.services.CompanyServiceImpl;
import org.homework.hibernatehw7.services.ProjectServiceImpl;

import java.util.*;

public class DeveloperCrudRepositoryImpl implements DeveloperCrudRepository {

    private final CrudRepositoryJDBC<Developer, Long> CRUD_REPOSITORY_JDBC = RepositoryFactory.of(Developer.class);
    private static DeveloperCrudRepositoryImpl developerCrudRepository;

    public static DeveloperCrudRepositoryImpl getInstance() {
        if (developerCrudRepository == null) {
            synchronized (DeveloperCrudRepositoryImpl.class) {
                if (developerCrudRepository == null) {
                    developerCrudRepository = new DeveloperCrudRepositoryImpl();
                }
            }
        }
        return developerCrudRepository;
    }

    @Override
    public Developer createNewDeveloper(String name, Long age, String gender, String email, Long salary, Set<Skill> skillSet, Long companyId, Long projectId) {
        Set<Project> projectSet = new HashSet<>();
        projectSet.add(ProjectServiceImpl.getInstance().findById(projectId).get());
        return create(Developer.builder()
                .name(name)
                .age(age)
                .gender(gender)
                .email(email)
                .salary(salary)
                .skills(skillSet)
                .company(CompanyServiceImpl.getInstance().findById(companyId).get())
                .projects(projectSet)
                .build());
    }

    @Override
    public void updateDeveloper(Long id, String name, Long age, String gender, String email, Long salary, Set<Skill> skillSet, Long companyId, Long projectId) {
        Set<Project> projectSet = new HashSet<>();
        projectSet.add(ProjectServiceImpl.getInstance().findById(projectId).get());
        Developer developer = findById(id).get();
        developer.setName(name);
        developer.setAge(age);
        developer.setGender(gender);
        developer.setEmail(email);
        developer.setSalary(salary);
        developer.setCompany(CompanyServiceImpl.getInstance().findById(companyId).get());
        developer.setSkills(skillSet);
        developer.setProjects(projectSet);
        update(id, developer);
    }

    @Override
    public Long getSumSalariesDevelopersOfOneProject(Long projectId) {
        Long sumSalaries = 0L;
        for (Developer developer : getDevelopersFromOneProject(projectId)) {
            sumSalaries += developer.getSalary();
        }
        return sumSalaries;
    }

    @Override
    public List<Developer> getDevelopersFromOneProject(Long projectId) {
        List<Developer> developersOfOneProject = new ArrayList<>();
        for (Developer developer : findAll()) {
            for (Project project : new ArrayList<>(developer.getProjects())) {
                if (project.getId().equals(projectId)) {
                    developersOfOneProject.add(developer);
                }
            }
        }
        return developersOfOneProject;
    }

    @Override
    public List<Developer> getDevelopersByActivity(String nameActivity) {
        return getBySkill(nameActivity);
    }

    private List<Developer> getBySkill(String skillParameter) {
        List<Developer> skills = new ArrayList<>();
        for (Developer developer : findAll()) {
            for (Skill skill : new ArrayList<>(developer.getSkills())) {
                String activity = skill.getActivity();
                String skillLevel = skill.getLevel();
                if (activity.equalsIgnoreCase(skillParameter)) {
                    skills.add(developer);
                }
                if (skillLevel.equalsIgnoreCase(skillParameter)) {
                    skills.add(developer);
                }
            }
        }
        return skills;
    }

    @Override
    public List<Developer> getDevelopersByLevel(String nameLevel) {
        return getBySkill(nameLevel);
    }

    @Override
    public Optional<Developer> findById(Long id) {
        return CRUD_REPOSITORY_JDBC.findById(id);
    }

    @Override
    public List<Developer> findAll() {
        return CRUD_REPOSITORY_JDBC.findAll();
    }

    @Override
    public Developer create(Developer developer) {
        return CRUD_REPOSITORY_JDBC.create(developer);
    }

    @Override
    public Developer update(Long id, Developer developer) {
        return CRUD_REPOSITORY_JDBC.update(id, developer);
    }

    @Override
    public void delete(Long id) {
        CRUD_REPOSITORY_JDBC.delete(id);
    }

    @Override
    public void close() {
        CRUD_REPOSITORY_JDBC.close();
    }
}
