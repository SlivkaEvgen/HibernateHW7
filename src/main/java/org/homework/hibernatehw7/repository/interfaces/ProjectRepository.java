package org.homework.hibernatehw7.repository.interfaces;

import org.homework.hibernatehw7.model.Project;

import java.util.List;

public interface ProjectRepository extends CrudRepositoryJDBC<Project, Long> {

    List<String> getListProjectsWithDate();
}
