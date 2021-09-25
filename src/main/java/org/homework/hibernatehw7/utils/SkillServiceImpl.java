//package org.homework.hibernatehw7.services;
//
//import org.homework.hibernatehw7.model.Developer;
//import org.homework.hibernatehw7.model.Skill;
//
//import java.util.HashSet;
//import java.util.Set;
//
//public class SkillServiceImpl extends ServiceImpl<Skill, Long> {
//
//    private static SkillServiceImpl skillService;
//
//    private SkillServiceImpl(Class<Skill> modelClass) {
//        super(modelClass);
//    }
//
//    public static SkillServiceImpl getInstance() {
//        if (skillService == null) {
//            synchronized (SkillServiceImpl.class) {
//                if (skillService == null) {
//                    skillService = new SkillServiceImpl(Skill.class);
//                }
//            }
//        }
//        return skillService;
//    }
//
//    public Skill createNewSkill(String activity, String level) {
//        return create(Skill.builder()
//                .activity(activity)
//                .level(level)
//                .build());
//    }
//
//    public void updateSkill(Long id, String activity, String level, Long developerId) {
//        Set<Developer> developerSet = new HashSet<>();
//        developerSet.add(DeveloperServiceImpl.getInstance().findById(developerId).get());
//
//        Skill skill = findById(id).get();
//        skill.setActivity(activity);
//        skill.setLevel(level);
//        skill.setDevelopers(developerSet);
//        update(id, skill);
//    }
//}
