package org.homework.hibernatehw7.services;

import org.homework.hibernatehw7.model.Developer;
import org.homework.hibernatehw7.model.Project;
import org.homework.hibernatehw7.model.Skill;
import org.homework.hibernatehw7.repository.DeveloperCrudRepositoryImpl;
import org.homework.hibernatehw7.services.interfaces.DeveloperService;
import java.util.*;

public class DeveloperServiceImpl implements DeveloperService {

    private final DeveloperCrudRepositoryImpl developerCrudRepository = new DeveloperCrudRepositoryImpl();
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
    public Developer createNewDeveloper(String name, Long age, String gender, String email, Long salary, Set<Skill> skillSet, Long companyId, Long projectId) { // Long companyId, Long projectId,
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
//        Set<Skill> skillSet = new HashSet<>();
        Set<Project> projectSet = new HashSet<>();
//        skillSet.add(SkillServiceImpl.getInstance().findById(skillId).get());
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
            Long salary = developer.getSalary();
            sumSalaries += salary;
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
        List<Developer> developersActivity = new ArrayList<>();
        for (Developer developer : findAll()) {
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
        for (Developer developer : findAll()) {
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
    public Optional<Developer> findById(Long id) {
        return developerCrudRepository.findById(id);
    }

    @Override
    public List<Developer> findAll() {
        return developerCrudRepository.findAll();
    }

    @Override
    public Developer create(Developer developer) {
        return developerCrudRepository.create(developer);
    }

    @Override
    public Developer update(Long id, Developer developer) {
        return developerCrudRepository.update(id, developer);
    }

    @Override
    public void delete(Long id) {
        developerCrudRepository.delete(id);
    }

    @Override
    public void close() {
        developerCrudRepository.close();
    }
}
