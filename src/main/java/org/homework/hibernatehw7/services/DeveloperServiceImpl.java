package org.homework.hibernatehw7.services;

import org.homework.hibernatehw7.model.Developer;
import org.homework.hibernatehw7.model.Skill;
import org.homework.hibernatehw7.repository.DeveloperCrudRepositoryImpl;
import org.homework.hibernatehw7.services.interfaces.DeveloperService;

import java.util.List;
import java.util.Optional;
import java.util.Set;

public class DeveloperServiceImpl implements DeveloperService {

    private final DeveloperCrudRepositoryImpl CRUD_REPOSITORY = DeveloperCrudRepositoryImpl.getInstance();
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

    @Override
    public Optional<Developer> findById(Long id) {
        return CRUD_REPOSITORY.findById(id);
    }

    @Override
    public List<Developer> findAll() {
        return CRUD_REPOSITORY.findAll();
    }

    @Override
    public Developer create(Developer developer) {
        return CRUD_REPOSITORY.create(developer);
    }

    @Override
    public Developer update(Long id, Developer developer) {
        return CRUD_REPOSITORY.update(id, developer);
    }

    @Override
    public void delete(Long id) {
        CRUD_REPOSITORY.delete(id);
    }

    @Override
    public void close() {
        CRUD_REPOSITORY.close();
    }
}
