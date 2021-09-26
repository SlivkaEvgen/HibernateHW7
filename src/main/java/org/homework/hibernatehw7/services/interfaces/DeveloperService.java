package org.homework.hibernatehw7.services.interfaces;

import org.homework.hibernatehw7.model.Developer;
import org.homework.hibernatehw7.model.Skill;

import java.util.List;
import java.util.Set;

public interface DeveloperService extends Service<Developer, Long> {

    Developer createNewDeveloper(String name, Long age, String gender, String email, Long salary, Set<Skill> skillSet, Long companyId, Long projectId);

    void updateDeveloper(Long id, String name, Long age, String gender, String email, Long salary, Set<Skill> skillSet, Long companyId, Long projectId);

    List<Developer> getDevelopersFromOneProject(Long projectId);

    List<Developer> getDevelopersByActivity(String nameActivity);

    List<Developer> getDevelopersByLevel(String nameLevel);

    Long getSumSalariesDevelopersOfOneProject(Long projectId);
}
