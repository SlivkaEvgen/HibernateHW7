//package org.homework.hibernatehw7.repository;
//
//import lombok.SneakyThrows;
//import org.homework.hibernatehw7.model.Developer;
//import org.homework.hibernatehw7.model.Project;
//import org.homework.hibernatehw7.model.Skill;
//import org.homework.hibernatehw7.repository.interfaces.DeveloperRepository;
//
//import java.util.ArrayList;
//import java.util.List;
//import java.util.Set;
//
//public class DeveloperCrudRepositoryImpl extends CrudRepositoryHibernateImpl<Developer, Long> implements DeveloperRepository {
//
//    CrudRepositoryHibernateImpl<Developer, Long> crudRepositoryHibernate = new CrudRepositoryHibernateImpl<>(Developer.class);
//    CrudRepositoryHibernateImpl<Project, Long> repositoryHibernatePro = new CrudRepositoryHibernateImpl<>(Project.class);
//
//
//    public DeveloperCrudRepositoryImpl(Class<Developer> modelClass) {
//        super(modelClass);
//    }
//
//
//    @SneakyThrows
//    public Long getSumSalariesDevelopersOfOneProject(Long projectId) {
//        Project project = repositoryHibernatePro.findById(projectId).get();
//        List<Developer> developerList = new ArrayList<>(project.getDevelopers());
//        System.out.println(developerList);
//        long totalSalaries = 0L;
//        for (Developer developer : developerList) {
//            Long salary = developer.getSalary();
//            totalSalaries = totalSalaries + salary;
//        }
////        repositoryHibernatePro.close();
//        return totalSalaries;
//    }
//
//    @Override
//    public List<Developer> getDevelopersFromOneProject(Long projectId) {
//        ArrayList<Developer> developers = new ArrayList<>(repositoryHibernatePro.findById(projectId).get().getDevelopers());
//        return developers;
//    }
//
//    @Override
//    public List<Developer> getDevelopersByActivity(String nameActivity) {
//        List<Developer> developersActivities = new ArrayList<>();
//        for (Developer developer : new ArrayList<>(crudRepositoryHibernate.findAll())) {
//            Set<Skill> skills = developer.getSkills();
//            ArrayList<Skill> arrayList = new ArrayList<>(skills);
//            for (Skill skill : arrayList) {
//                if (skill.getActivities().equalsIgnoreCase(nameActivity)) {
//                    developersActivities.add(developer);
//                }
//            }
//        }
////        crudRepositoryHibernate.close();
//        return developersActivities;
//    }
//
//    @Override
//    public List<Developer> getDevelopersByLevel(String level) {
//        List<Developer> developersLevel = new ArrayList<>();
//        for (Developer developer : new ArrayList<>(crudRepositoryHibernate.findAll())) {
//            for (Skill skill : new ArrayList<>(developer.getSkills())) {
//                if (skill.getLevel().equalsIgnoreCase(level)) {
//                    developersLevel.add(developer);
//                }
//            }
//        }
////        crudRepositoryHibernate.close();
//        return developersLevel;
//    }
//}
