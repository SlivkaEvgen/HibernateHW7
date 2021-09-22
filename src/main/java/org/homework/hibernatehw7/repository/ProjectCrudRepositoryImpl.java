//package org.homework.hibernatehw7.repository;
//
//import org.homework.hibernatehw7.model.Project;
//import org.homework.hibernatehw7.repository.interfaces.ProjectRepository;
//
//import java.util.ArrayList;
//import java.util.List;
//
//public class ProjectCrudRepositoryImpl extends CrudRepositoryHibernateImpl<Project,Long> implements ProjectRepository {
//
//    CrudRepositoryHibernateImpl<Project,Long> crudRepositoryHibernate = new CrudRepositoryHibernateImpl<>(Project.class);
//    public ProjectCrudRepositoryImpl(Class<Project> modelClass) {
//        super(modelClass);
//    }
//
//    @Override
//    public List<String> getListProjectsWithDate() {
//
//        int size=0;
//        String projectName="";
//        String firstDate="";
//        List<Project> projects = crudRepositoryHibernate.findAll();
//        List<String> projectsWithDate = new ArrayList<>();
//        for (Project project : projects) {
//            size = project.getDevelopers().size();
//            projectName = project.getName();
//            firstDate = project.getFirstDate();
//            projectsWithDate.add(projectName + " - " + size + " developers; " + firstDate);
//        }
//        return projectsWithDate;
//    }
//}
