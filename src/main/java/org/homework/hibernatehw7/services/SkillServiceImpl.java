package org.homework.hibernatehw7.services;

import org.homework.hibernatehw7.model.Skill;
import org.homework.hibernatehw7.repository.CrudRepositoryHibernateImpl;
import org.homework.hibernatehw7.services.interfaces.SkillService;

import java.util.List;
import java.util.Optional;

public class SkillServiceImpl implements SkillService {

    private final CrudRepositoryHibernateImpl<Skill, Long> CRUD_REPOSITORY_SKILL = new CrudRepositoryHibernateImpl<>(Skill.class);

    @Override
    public Optional<Skill> getById(Long id) {
        return CRUD_REPOSITORY_SKILL.findById(id);
    }

    @Override
    public List<Skill> getAll() {
        return CRUD_REPOSITORY_SKILL.findAll();
    }

    @Override
    public Skill createNewSkill(String activity, String level) {
        Skill skill = Skill.builder()
                .activities(activity)
                .level(level)
                .build();
        return CRUD_REPOSITORY_SKILL.create(skill);
    }

    @Override
    public void update(Long id, String activity, String level) {
        Skill skill = CRUD_REPOSITORY_SKILL.findById(id).get();
        skill.setActivities(activity);
        skill.setLevel(level);
        CRUD_REPOSITORY_SKILL.update(id, skill);
    }

    @Override
    public void delete(Long id) {
        CRUD_REPOSITORY_SKILL.delete(id);
    }
}
