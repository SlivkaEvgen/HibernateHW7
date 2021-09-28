//package org.homework.hibernatehw7.controller.developer;
//
//import org.homework.hibernatehw7.controller.GetDeleteCommands;
//import org.homework.hibernatehw7.controller.interfaces.Controller;
//import org.homework.hibernatehw7.model.Developer;
//
//public class DeleteDeveloperCommand implements Controller {
//
//    private final GetDeleteCommands<Developer, Long> generalEnterMethods = new GetDeleteCommands<>(Developer.class);
//    private static DeleteDeveloperCommand deleteDeveloperCommand;
//
//    public static DeleteDeveloperCommand getInstance() {
//        if (deleteDeveloperCommand == null) {
//            deleteDeveloperCommand = new DeleteDeveloperCommand();
//        }
//        return deleteDeveloperCommand;
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