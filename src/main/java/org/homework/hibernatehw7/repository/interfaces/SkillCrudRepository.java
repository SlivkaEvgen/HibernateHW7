package org.homework.hibernatehw7.repository.interfaces;

import org.homework.hibernatehw7.model.Skill;

public interface SkillCrudRepository extends CrudRepositoryJDBC<Skill, Long> {

    Skill createNewSkill(String activity, String level);

    void updateSkill(Long aLong, String activity, String level);
}
