package org.homework.hibernatehw7.services;

import org.homework.hibernatehw7.model.Developer;
import org.homework.hibernatehw7.model.Skill;
import org.homework.hibernatehw7.repository.DeveloperRepositoryImpl;
import org.homework.hibernatehw7.repository.interfaces.DeveloperRepository;
import org.homework.hibernatehw7.services.interfaces.DeveloperService;

import java.util.List;
import java.util.Set;

public class DeveloperServiceImpl extends ModelService<Developer, Long> implements DeveloperService {

    private static final long serialVersionUID = 3334444651928374654L;
    private final DeveloperRepository CRUD_REPOSITORY = new DeveloperRepositoryImpl(Developer.class);

    public DeveloperServiceImpl(Class<Developer> classModel) {
        super(classModel);
    }

    @Override
    public Developer createNewDeveloper(String name, Long age, String gender, String email, Long salary, Set<Skill> skillSet, Long companyId, Long projectId) {
        return CRUD_REPOSITORY.createNewDeveloper(name, age, gender, email, salary, skillSet, companyId, projectId);
    }

    @Override
    public void updateDeveloper(Long id, String name, Long age, String gender, String email, Long salary, Set<Skill> skillSet, Long companyId, Long projectId) {
        CRUD_REPOSITORY.updateDeveloper(id, name, age, gender, email, salary, skillSet, companyId, projectId);
    }

    @Override
    public Long getSumSalariesDevelopersOfOneProject(Long projectId) {
        return CRUD_REPOSITORY.getSumSalariesDevelopersOfOneProject(projectId);
    }

    @Override
    public List<Developer> getDevelopersFromOneProject(Long projectId) {
        return CRUD_REPOSITORY.getDevelopersFromOneProject(projectId);
    }

    @Override
    public List<Developer> getDevelopersByActivity(String nameActivity) {
        return CRUD_REPOSITORY.getDevelopersByActivity(nameActivity);
    }

    @Override
    public List<Developer> getDevelopersByLevel(String nameLevel) {
        return CRUD_REPOSITORY.getDevelopersByLevel(nameLevel);
    }
}
