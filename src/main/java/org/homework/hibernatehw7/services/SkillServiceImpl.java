package org.homework.hibernatehw7.services;

import org.homework.hibernatehw7.model.Developer;
import org.homework.hibernatehw7.model.Skill;
import org.homework.hibernatehw7.services.interfaces.SkillService;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

public class SkillServiceImpl implements SkillService {

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
        return ServiceFactory.of(Skill.class).findById(id);
    }

    @Override
    public List<Skill> findAll() {
        return ServiceFactory.of(Skill.class).findAll();
    }

    @Override
    public Skill create(Skill skill) {
        return ServiceFactory.of(Skill.class).create(skill);
    }

    @Override
    public Skill update(Long id, Skill skill) {
        return ServiceFactory.of(Skill.class).update(id, skill);
    }

    @Override
    public void delete(Long id) {
        ServiceFactory.of(Skill.class).delete(id);
    }

    @Override
    public void close() {
        ServiceFactory.of(Skill.class).close();
    }

    @Override
    public Skill createNewSkill(String activity, String level) {
        return create(Skill.builder().activity(activity).level(level).build());
    }

    @Override
    public void updateSkill(Long id, String activity, String level, Long developerId) {
        Set<Developer> developerSet = new HashSet<>();
        developerSet.add(DeveloperServiceImpl.getInstance().findById(developerId).get());

        Skill skill = findById(id).get();
        skill.setActivity(activity);
        skill.setLevel(level);
        skill.setDevelopers(developerSet);
        update(id, skill);
    }
}
