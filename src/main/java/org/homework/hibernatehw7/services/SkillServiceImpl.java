package org.homework.hibernatehw7.services;

import org.homework.hibernatehw7.model.Skill;
import org.homework.hibernatehw7.repository.SkillRepositoryImpl;
import org.homework.hibernatehw7.repository.interfaces.SkillRepository;
import org.homework.hibernatehw7.services.interfaces.SkillService;

public class SkillServiceImpl extends ModelService<Skill, Long> implements SkillService {

    private static final long serialVersionUID = 3334844651928374654L;
    private final SkillRepository CRUD_REPOSITORY = new SkillRepositoryImpl(Skill.class);

    public SkillServiceImpl(Class<Skill> classModel) {
        super(classModel);
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
