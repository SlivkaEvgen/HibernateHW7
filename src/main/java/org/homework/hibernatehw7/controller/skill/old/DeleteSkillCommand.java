//package org.homework.hibernatehw7.controller.skill;
//
//import org.homework.hibernatehw7.controller.GetDeleteCommands;
//import org.homework.hibernatehw7.controller.interfaces.Controller;
//import org.homework.hibernatehw7.model.Skill;
//
//public class DeleteSkillCommand implements Controller {
//
//    private final GetDeleteCommands<Skill, Long> generalEnterMethods = new GetDeleteCommands<>(Skill.class);
//    private static DeleteSkillCommand deleteSkillCommand;
//
//    public static DeleteSkillCommand getInstance() {
//        if (deleteSkillCommand == null) {
//            deleteSkillCommand = new DeleteSkillCommand();
//        }
//        return deleteSkillCommand;
//    }
//
//    @Override
//    public void start() {
//        generalEnterMethods.delete();
//    }
//
//    @Override
//    public void close() {
//        System.exit(0);
//    }
//}
