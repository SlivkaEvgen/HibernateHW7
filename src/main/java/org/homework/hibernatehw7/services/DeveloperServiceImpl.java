package org.homework.hibernatehw7.services;

import org.homework.hibernatehw7.model.Company;
import org.homework.hibernatehw7.model.Developer;
import org.homework.hibernatehw7.model.Project;
import org.homework.hibernatehw7.model.Skill;
import org.homework.hibernatehw7.repository.CrudRepositoryHibernateImpl;
import org.homework.hibernatehw7.services.interfaces.DeveloperService;

import java.util.*;

public class DeveloperServiceImpl implements DeveloperService {

    private final CrudRepositoryHibernateImpl<Developer, Long> CRUD_REPOSITORY_DEVELOPER = new CrudRepositoryHibernateImpl<>(Developer.class);

    @Override
    public Optional<Developer> getById(Long id) {
        return CRUD_REPOSITORY_DEVELOPER.findById(id);
    }

    @Override
    public List<Developer> getAll() {
        return CRUD_REPOSITORY_DEVELOPER.findAll();
    }

    @Override
    public Developer createNewDeveloper(String name, Long age, String gender, String email, Long salary, Long skillId) { // Long companyId, Long projectId,
        Set<Skill> skillSet1 = new HashSet<>();
        CrudRepositoryHibernateImpl<Skill, Long> repositoryHibernate = new CrudRepositoryHibernateImpl<>(Skill.class);
        Skill skill = repositoryHibernate.findById(skillId).get();
        skillSet1.add(skill);

//        Set<Project> projectSet = new HashSet<>();
//        CrudRepositoryHibernateImpl<Project, Long> repositoryHibernate1 = new CrudRepositoryHibernateImpl<>(Project.class);
//        Project project = repositoryHibernate1.findById(projectId).get();
//        projectSet.add(project);
//
//        CrudRepositoryHibernateImpl<Company, Long> repositoryHibernate2 = new CrudRepositoryHibernateImpl<>(Company.class);
//        Company company = repositoryHibernate2.findById(companyId).get();

        Developer developer = Developer.builder()
                .name(name)
                .age(age)
                .gender(gender)
                .email(email)
                .salary(salary)
                .skills(skillSet1)
//                .company(company)
//                .projects(projectSet)
                .build();
        return CRUD_REPOSITORY_DEVELOPER.create(developer);
    }

    @Override
    public void update(Long id, String name, Long age, String gender, String email, Long salary,Long skillId, Long companyId, Long projectId) {
        Set<Skill> skillSet = new HashSet<>();
        CrudRepositoryHibernateImpl<Skill, Long> repositoryHibernate = new CrudRepositoryHibernateImpl<>(Skill.class);
        Skill skill = repositoryHibernate.findById(skillId).get();
        skillSet.add(skill);

        CrudRepositoryHibernateImpl<Company, Long> repositoryHibernate1 = new CrudRepositoryHibernateImpl<>(Company.class);
        Company company = repositoryHibernate1.findById(companyId).get();

        Set<Project> projectSet = new HashSet<>();
        CrudRepositoryHibernateImpl<Project, Long> repositoryHibernate2 = new CrudRepositoryHibernateImpl<>(Project.class);
        Project project = repositoryHibernate2.findById(projectId).get();
        projectSet.add(project);

        Developer developer = CRUD_REPOSITORY_DEVELOPER.findById(id).get();
        developer.setName(name);
        developer.setAge(age);
        developer.setGender(gender);
        developer.setEmail(email);
        developer.setSalary(salary);
        developer.setCompany(company);
        developer.setSkills(skillSet);
        developer.setProjects(projectSet);
        CRUD_REPOSITORY_DEVELOPER.update(id, developer);
    }

    @Override
    public void delete(Long id) {
        CRUD_REPOSITORY_DEVELOPER.delete(id);
    }

    @Override
    public Long getSumSalariesDevelopersOfOneProject(Long projectId) {
        List<Developer> fromOneProject = getDevelopersFromOneProject(projectId);
        Long sumSalaries = 0L;
        for (Developer developer : fromOneProject) {
            Long salary = developer.getSalary();
            sumSalaries += salary;
        }
        return sumSalaries;
    }

    @Override
    public List<Developer> getDevelopersFromOneProject(Long projectId) {
        List<Developer> developers = getAll();
        List<Developer> developersOfOneProject = new ArrayList<>();
        for (Developer developer : developers) {
            Set<Project> projects = developer.getProjects();
            List<Project> projectList = new ArrayList<>(projects);
            for (Project project : projectList) {
                Long projectID = project.getId();
                if (projectID.equals(projectId)) {
                    developersOfOneProject.add(developer);
                }
            }
        }
        return developersOfOneProject;
    }

    @Override
    public List<Developer> getDevelopersByActivity(String nameActivity) {
        List<Developer> developers = getAll();
        List<Developer> developersActivity = new ArrayList<>();
        for (Developer developer : developers) {
            List<Skill> skills = new ArrayList<>(developer.getSkills());
            for (Skill skill : skills) {
                String activities = skill.getActivity();
                if (activities.equalsIgnoreCase(nameActivity)) {
                    if (developersActivity.contains(developer)){
                        continue;
                    }
                    developersActivity.add(developer);
                }
            }
        }
        return developersActivity;
    }

    @Override
    public List<Developer> getDevelopersByLevel(String nameLevel) {
        List<Developer> developers = CRUD_REPOSITORY_DEVELOPER.findAll();
        List<Developer> developersActivity = new ArrayList<>();
        for (Developer developer : developers) {
            Set<Skill> skillSet = developer.getSkills();
            List<Skill> skills = new ArrayList<>(skillSet);
            for (Skill skill : skills) {
                String level = skill.getLevel();
                if (level.equalsIgnoreCase(nameLevel)) {
                    if (developersActivity.contains(developer)){
                        continue;
                    }
                    developersActivity.add(developer);
                }
            }
        }
        return developersActivity;
    }
}
