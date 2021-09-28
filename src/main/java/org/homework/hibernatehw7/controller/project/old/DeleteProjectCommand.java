//package org.homework.hibernatehw7.controller.project;
//
//import org.homework.hibernatehw7.controller.GetDeleteCommands;
//import org.homework.hibernatehw7.controller.interfaces.Controller;
//import org.homework.hibernatehw7.model.Project;
//
//public class DeleteProjectCommand implements Controller {
//
//    private final GetDeleteCommands<Project, Long> generalEnterMethods = new GetDeleteCommands<>(Project.class);
//    private static DeleteProjectCommand deleteProjectCommand;
//
//    public static DeleteProjectCommand getInstance() {
//        if (deleteProjectCommand == null) {
//            deleteProjectCommand = new DeleteProjectCommand();
//        }
//        return deleteProjectCommand;
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
