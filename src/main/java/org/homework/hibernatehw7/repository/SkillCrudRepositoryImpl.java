package org.homework.hibernatehw7.repository;

import org.homework.hibernatehw7.model.Skill;
import org.homework.hibernatehw7.repository.interfaces.CrudRepositoryJDBC;
import org.homework.hibernatehw7.repository.interfaces.SkillCrudRepository;

import java.util.List;
import java.util.Optional;

public class SkillCrudRepositoryImpl implements SkillCrudRepository {

    private final CrudRepositoryJDBC<Skill, Long> CRUD_REPOSITORY_JDBC = RepositoryFactory.of(Skill.class);
    private static SkillCrudRepositoryImpl skillCrudRepository;

    public static SkillCrudRepositoryImpl getInstance() {
        if (skillCrudRepository == null) {
            synchronized (SkillCrudRepositoryImpl.class) {
                if (skillCrudRepository == null) {
                    skillCrudRepository = new SkillCrudRepositoryImpl();
                }
            }
        }
        return skillCrudRepository;
    }

    @Override
    public Optional<Skill> findById(Long id) {
        return CRUD_REPOSITORY_JDBC.findById(id);
    }

    @Override
    public List<Skill> findAll() {
        return CRUD_REPOSITORY_JDBC.findAll();
    }

    @Override
    public Skill create(Skill skill) {
        return CRUD_REPOSITORY_JDBC.create(skill);
    }

    @Override
    public Skill update(Long id, Skill skill) {
        return CRUD_REPOSITORY_JDBC.update(id, skill);
    }

    @Override
    public void delete(Long id) {
        CRUD_REPOSITORY_JDBC.delete(id);
    }

    @Override
    public void close() {
        CRUD_REPOSITORY_JDBC.close();
    }

    @Override
    public Skill createNewSkill(String activity, String level) {
        return CRUD_REPOSITORY_JDBC.create(Skill.builder().activity(activity).level(level).build());
    }

    @Override
    public void updateSkill(Long id, String activity, String level) {
        Skill skill = findById(id).get();
        skill.setActivity(activity);
        skill.setLevel(level);
        CRUD_REPOSITORY_JDBC.update(id, skill);
    }
}
