package org.homework.hibernatehw7.services;

import org.homework.hibernatehw7.model.Skill;
import org.homework.hibernatehw7.repository.SkillCrudRepositoryImpl;
import org.homework.hibernatehw7.services.interfaces.SkillService;

import java.util.List;
import java.util.Optional;

public class SkillServiceImpl implements SkillService {

    private final SkillCrudRepositoryImpl CRUD_REPOSITORY = SkillCrudRepositoryImpl.getInstance();
    private static SkillServiceImpl skillService;

    public static SkillServiceImpl getInstance() {
        if (skillService == null) {
            synchronized (SkillServiceImpl.class) {
                if (skillService == null) {
                    skillService = new SkillServiceImpl();
                }
            }
        }
        return skillService;
    }

    @Override
    public Optional<Skill> findById(Long id) {
        return CRUD_REPOSITORY.findById(id);
    }

    @Override
    public List<Skill> findAll() {
        return CRUD_REPOSITORY.findAll();
    }

    @Override
    public Skill create(Skill skill) {
        return CRUD_REPOSITORY.create(skill);
    }

    @Override
    public Skill update(Long id, Skill skill) {
        return CRUD_REPOSITORY.update(id, skill);
    }

    @Override
    public void delete(Long id) {
        CRUD_REPOSITORY.delete(id);
    }

    @Override
    public void close() {
        CRUD_REPOSITORY.close();
    }

    @Override
    public Skill createNewSkill(String activity, String level) {
        return CRUD_REPOSITORY.createNewSkill(activity, level);
    }

    @Override
    public void updateSkill(Long id, String activity, String level) {
        CRUD_REPOSITORY.updateSkill(id, activity, level);
    }
}
