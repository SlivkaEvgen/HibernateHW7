package org.homework.hibernatehw7.services.interfaces;

import org.homework.hibernatehw7.model.Developer;

import java.util.List;

public interface DeveloperService extends IService<Developer, Long> {

    Developer createNewDeveloper(String name, Long age, String gender, String email, Long salary, Long skillId);

    void update(Long id, String name, Long age, String gender, String email, Long salary,Long skillId, Long companyId, Long projectId);

    Long getSumSalariesDevelopersOfOneProject(Long projectId);

    List<Developer> getDevelopersFromOneProject(Long projectId);

    List<Developer> getDevelopersByActivity(String nameActivity);

    List<Developer> getDevelopersByLevel(String nameLevel);
}
