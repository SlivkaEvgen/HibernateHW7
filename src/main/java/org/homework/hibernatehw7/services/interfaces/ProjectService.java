package org.homework.hibernatehw7.services.interfaces;


import org.homework.hibernatehw7.model.Project;

import java.util.List;

public interface ProjectService extends IService<Project, Long> {

    Project createNewProject(String name, Long cost,Long companyId);

    void update(Long id, String name, Long cost,Long companyId,Long customerId,Long developerId);

    List<String> getListProjectsWithDate();
}
