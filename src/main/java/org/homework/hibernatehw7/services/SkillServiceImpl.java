package org.homework.hibernatehw7.services;

import org.homework.hibernatehw7.model.Skill;
import org.homework.hibernatehw7.services.interfaces.Service;
import org.homework.hibernatehw7.services.interfaces.SkillService;

import java.util.List;
import java.util.Optional;

public class SkillServiceImpl implements SkillService {

    private final Service<Skill,Long> SKILL_SERVICE = ServiceFactory.of(Skill.class);
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
        return SKILL_SERVICE.findById(id);
    }

    @Override
    public List<Skill> findAll() {
        return SKILL_SERVICE.findAll();
    }

    @Override
    public Skill create(Skill skill) {
        return SKILL_SERVICE.create(skill);
    }

    @Override
    public Skill update(Long id, Skill skill) {
        return SKILL_SERVICE.update(id, skill);
    }

    @Override
    public void delete(Long id) {
        SKILL_SERVICE.delete(id);
    }

    @Override
    public void close() {
        SKILL_SERVICE.close();
    }

    @Override
    public Skill createNewSkill(String activity, String level) {
        return SKILL_SERVICE.create(Skill.builder().activity(activity).level(level).build());
    }

    @Override
    public void updateSkill(Long id, String activity, String level) {
        Skill skill = findById(id).get();
        skill.setActivity(activity);
        skill.setLevel(level);
        SKILL_SERVICE.update(id, skill);
    }
}
