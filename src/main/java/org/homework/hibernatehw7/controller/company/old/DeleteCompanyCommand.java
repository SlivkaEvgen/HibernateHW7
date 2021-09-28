//package org.homework.hibernatehw7.controller.company;
//
//import org.homework.hibernatehw7.controller.GetDeleteCommands;
//import org.homework.hibernatehw7.controller.interfaces.Controller;
//import org.homework.hibernatehw7.model.Company;
//
//public class DeleteCompanyCommand implements Controller {
//
//    private final GetDeleteCommands<Company, Long> generalEnterMethods = new GetDeleteCommands<>(Company.class);
//    private static DeleteCompanyCommand deleteCompanyCommand;
//
//    public static DeleteCompanyCommand getInstance() {
//        if (deleteCompanyCommand == null) {
//            deleteCompanyCommand = new DeleteCompanyCommand();
//        }
//        return deleteCompanyCommand;
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
