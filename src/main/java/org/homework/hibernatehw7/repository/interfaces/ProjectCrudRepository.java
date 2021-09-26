package org.homework.hibernatehw7.repository.interfaces;

import org.homework.hibernatehw7.model.Project;

import java.util.List;

public interface ProjectCrudRepository extends CrudRepositoryJDBC<Project, Long> {

    List<String> getListProjectsWithDate();

    Project createNewProject(String name, Long cost, Long companyId, Long customerId);

    void updateProject(Long id, String name, Long cost, Long companyId, Long customerId);
}
