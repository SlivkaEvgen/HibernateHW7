package org.homework.hibernatehw7.services.interfaces;

import org.homework.hibernatehw7.model.Skill;

public interface SkillService extends Service<Skill, Long> {

    Skill createNewSkill(String activity, String level);

    void updateSkill(Long aLong, String activity, String level, Long developerId);
}
