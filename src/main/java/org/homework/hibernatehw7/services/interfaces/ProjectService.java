package org.homework.hibernatehw7.services.interfaces;

import org.homework.hibernatehw7.model.Project;

import java.util.List;

public interface ProjectService extends Service<Project, Long> {

    Project createNewProject(String name, Long cost, Long companyId, Long customerId);

    void updateProject(Long id, String name, Long cost, Long companyId, Long customerId);

    List<String> getListProjectsWithDate();
}
