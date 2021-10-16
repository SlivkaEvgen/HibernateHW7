package org.homework.hibernatehw7.repository;

import org.hibernate.Session;
import org.homework.hibernatehw7.model.Company;
import org.homework.hibernatehw7.model.Developer;
import org.homework.hibernatehw7.model.Project;
import org.homework.hibernatehw7.model.Skill;
import org.homework.hibernatehw7.repository.interfaces.DeveloperRepository;
import org.homework.hibernatehw7.services.CompanyServiceImpl;
import org.homework.hibernatehw7.services.ProjectServiceImpl;
import org.homework.hibernatehw7.utils.HibernateSessionFactory;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class DeveloperRepositoryImpl extends ModelCrudRepositoryImpl<Developer, Long> implements DeveloperRepository {

    private static final long serialVersionUID = 3333744651928374654L;

    public DeveloperRepositoryImpl(Class<Developer> classModel) {
        super(classModel);
    }

    @Override
    public Developer createNewDeveloper(String name, Long age, String gender, String email, Long salary, Set<Skill> skillSet, Long companyId, Long projectId) {
        Set<Project> projectSet = new HashSet<>();
        projectSet.add(new ProjectServiceImpl(Project.class).findById(projectId).get());
        return create(Developer.builder()
                .name(name)
                .age(age)
                .gender(gender)
                .email(email)
                .salary(salary)
                .skills(skillSet)
                .company(new CompanyServiceImpl(Company.class).findById(companyId).get())
                .projects(projectSet)
                .build());
    }

    @Override
    public void updateDeveloper(Long id, String name, Long age, String gender, String email, Long salary, Set<Skill> skillSet, Long companyId, Long projectId) {
        Set<Project> projectSet = new HashSet<>();
        projectSet.add(new ProjectServiceImpl(Project.class).findById(projectId).get());
        Developer developer = findById(id).get();
        developer.setName(name);
        developer.setAge(age);
        developer.setGender(gender);
        developer.setEmail(email);
        developer.setSalary(salary);
        developer.setCompany(new CompanyServiceImpl(Company.class).findById(companyId).get());
        developer.setSkills(skillSet);
        developer.setProjects(projectSet);
        update(id, developer);
    }

    @Override
    public Long getSumSalariesDevelopersOfOneProject(Long projectId) {
        Session session = HibernateSessionFactory.getSessionFactory().openSession();
        String criteria = "select d.salary from Developer d join d.projects p where p.id =: id group by d.id";
        List<Long> longs = session.createQuery(criteria, Long.class).setParameter("id", projectId).list();
        return longs.stream().mapToLong(aLong -> aLong).sum();
    }

    @Override
    public List<Developer> getDevelopersFromOneProject(Long projectId) {
        Session session = HibernateSessionFactory.getSessionFactory().openSession();
        String criteria = "select d from Developer d join d.projects p where p.id =: id group by d.id";
        return session.createQuery(criteria, Developer.class).setParameter("id", projectId).list();
    }

    @Override
    public List<Developer> getDevelopersByActivity(String nameActivity) {
        Session session = HibernateSessionFactory.getSessionFactory().openSession();
        String criteria = "select d from Developer d join d.skills s where s.activity =: activity group by d.id";
        return session.createQuery(criteria, Developer.class).setParameter("activity", nameActivity).list();
    }

    @Override
    public List<Developer> getDevelopersByLevel(String nameLevel) {
        Session session = HibernateSessionFactory.getSessionFactory().openSession();
        String criteria = "select d from Developer d join d.skills s where s.level =: level group by d.id";
        return session.createQuery(criteria, Developer.class).setParameter("level", nameLevel).list();
    }
}

/// Other variants

//    @Override
//    public Long getSumSalariesDevelopersOfOneProject(Long projectId) {
//        Long sumSalaries = 0L;
//        for (Developer developer : getDevelopersFromOneProject(projectId)) {
//            sumSalaries += developer.getSalary();
//        }
//        return sumSalaries;
//    }

//////

//    @Override
//    public List<Developer> getDevelopersFromOneProject(Long projectId) {
//        List<Developer> developersOfOneProject = new ArrayList<>();
//        findAll().forEach(developer -> new ArrayList<>(developer.getProjects())
//                .stream()
//                .filter(project -> project.getId().equals(projectId))
//                .map(project -> developer)
//                .forEach(developersOfOneProject::add));
//        return developersOfOneProject;
//    }

///////

//    private List<Developer> getBySkillParameter(String skillParameter) {
//        List<Developer> skills = new ArrayList<>();
//        findAll().forEach(developer -> new ArrayList<>(developer.getSkills()).forEach(skill -> {
//            String activity = skill.getActivity();
//            String skillLevel = skill.getLevel();
//            if (activity.equalsIgnoreCase(skillParameter) | skillLevel.equalsIgnoreCase(skillParameter)) {
//                skills.add(developer);
//            }
//        }));
//        return skills;
//    }