package org.homework.hibernatehw7.services.interfaces;


import org.homework.hibernatehw7.model.Skill;

public interface SkillService extends IService<Skill, Long> {

    Skill createNewSkill(String activity, String level);

    void update(Long id, String activity, String level,Long developerId);
}
