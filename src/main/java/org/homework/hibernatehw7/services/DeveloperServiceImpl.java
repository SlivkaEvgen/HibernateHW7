package org.homework.hibernatehw7.services;

import org.homework.hibernatehw7.model.Developer;
import org.homework.hibernatehw7.model.Project;
import org.homework.hibernatehw7.model.Skill;
import org.homework.hibernatehw7.repository.RepositoryFactory;
import org.homework.hibernatehw7.repository.interfaces.CrudRepositoryJDBC;
import org.homework.hibernatehw7.services.interfaces.DeveloperService;

import java.util.*;

public class DeveloperServiceImpl implements DeveloperService {

    private final CrudRepositoryJDBC<Developer, Long> CRUD_REPOSITORY_DEVELOPER = RepositoryFactory.of(Developer.class);
    private static DeveloperServiceImpl developerService;

    public static DeveloperServiceImpl getInstance() {
        if (developerService == null) {
            synchronized (DeveloperServiceImpl.class) {
                if (developerService == null) {
                    developerService = new DeveloperServiceImpl();
                }
            }
        }
        return developerService;
    }

    @Override
    public Optional<Developer> getById(Long id) {
        return developerService.CRUD_REPOSITORY_DEVELOPER.findById(id);
    }

    @Override
    public List<Developer> getAll() {
        return developerService.CRUD_REPOSITORY_DEVELOPER.findAll();
    }

    @Override
    public Developer createNewDeveloper(String name, Long age, String gender, String email, Long salary, Long skillId, Long companyId, Long projectId) { // Long companyId, Long projectId,
        Set<Skill> skillSet1 = new HashSet<>();
        Set<Project> projectSet = new HashSet<>();
        skillSet1.add(SkillServiceImpl.getInstance().getById(skillId).get());
        projectSet.add(ProjectServiceImpl.getInstance().getById(projectId).get());

        return developerService.CRUD_REPOSITORY_DEVELOPER.create(Developer.builder()
                .name(name)
                .age(age)
                .gender(gender)
                .email(email)
                .salary(salary)
                .skills(skillSet1)
                .company(CompanyServiceImpl.getInstance().getById(companyId).get())
                .projects(projectSet)
                .build());
    }

    @Override
    public void update(Long id, String name, Long age, String gender, String email, Long salary, Long skillId, Long companyId, Long projectId) {
        Set<Skill> skillSet = new HashSet<>();
        Set<Project> projectSet = new HashSet<>();
        skillSet.add(SkillServiceImpl.getInstance().getById(skillId).get());
        projectSet.add(ProjectServiceImpl.getInstance().getById(projectId).get());

        Developer developer = developerService.CRUD_REPOSITORY_DEVELOPER.findById(id).get();
        developer.setName(name);
        developer.setAge(age);
        developer.setGender(gender);
        developer.setEmail(email);
        developer.setSalary(salary);
        developer.setCompany(CompanyServiceImpl.getInstance().getById(companyId).get());
        developer.setSkills(skillSet);
        developer.setProjects(projectSet);
        developerService.CRUD_REPOSITORY_DEVELOPER.update(id, developer);
    }

    @Override
    public Long getSumSalariesDevelopersOfOneProject(Long projectId) {
        Long sumSalaries = 0L;
        for (Developer developer : getDevelopersFromOneProject(projectId)) {
            Long salary = developer.getSalary();
            sumSalaries += salary;
        }
        return sumSalaries;
    }

    @Override
    public List<Developer> getDevelopersFromOneProject(Long projectId) {
        List<Developer> developersOfOneProject = new ArrayList<>();
        for (Developer developer : getAll()) {
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
        List<Developer> developersActivity = new ArrayList<>();
        for (Developer developer : getAll()) {
            for (Skill skill : new ArrayList<>(developer.getSkills())) {
                if (skill.getActivity().equalsIgnoreCase(nameActivity)) {
                    if (developersActivity.contains(developer)) {
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
        List<Developer> developersActivity = new ArrayList<>();
        for (Developer developer : getAll()) {
            for (Skill skill : new ArrayList<>(developer.getSkills())) {
                if (skill.getLevel().equalsIgnoreCase(nameLevel)) {
                    if (developersActivity.contains(developer)) {
                        continue;
                    }
                    developersActivity.add(developer);
                }
            }
        }
        return developersActivity;
    }

    @Override
    public void delete(Long id) {
        developerService.CRUD_REPOSITORY_DEVELOPER.delete(id);
    }
}
