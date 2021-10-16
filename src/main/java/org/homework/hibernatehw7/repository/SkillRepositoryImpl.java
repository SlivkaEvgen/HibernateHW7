package org.homework.hibernatehw7.repository;

import org.homework.hibernatehw7.model.Skill;
import org.homework.hibernatehw7.repository.interfaces.SkillRepository;

public class SkillRepositoryImpl extends ModelCrudRepositoryImpl<Skill, Long> implements SkillRepository {

    private static final long serialVersionUID = 3334044651928374654L;

    public SkillRepositoryImpl(Class<Skill> classModel) {
        super(classModel);
    }

    @Override
    public Skill createNewSkill(String activity, String level) {
        // ( ! )if only the declared skills ->
        
        //        for (Skill skill:findAll()) {
        //            if (skill.getLevel().equalsIgnoreCase(level)&&skill.getActivity().equalsIgnoreCase(activity)){
        //                System.out.println(" Already have such a skill "+ skill);
        //                return skill;
        //            }
        //        }
        return create(Skill.builder().activity(activity).level(level).build());
    }

    @Override
    public void updateSkill(Long id, String activity, String level) {
        Skill skill = findById(id).get();
        skill.setActivity(activity);
        skill.setLevel(level);
        update(id, skill);
    }
}
