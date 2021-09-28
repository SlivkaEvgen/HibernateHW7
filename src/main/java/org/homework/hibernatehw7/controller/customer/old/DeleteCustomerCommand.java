//package org.homework.hibernatehw7.controller.customer;
//
//import org.homework.hibernatehw7.controller.GetDeleteCommands;
//import org.homework.hibernatehw7.controller.interfaces.Controller;
//import org.homework.hibernatehw7.model.Customer;
//
//public class DeleteCustomerCommand implements Controller {
//
//    private final GetDeleteCommands<Customer, Long> generalEnterMethods = new GetDeleteCommands<>(Customer.class);
//    private static DeleteCustomerCommand deleteCustomerCommand;
//
//    public static DeleteCustomerCommand getInstance() {
//        if (deleteCustomerCommand == null) {
//            deleteCustomerCommand = new DeleteCustomerCommand();
//        }
//        return deleteCustomerCommand;
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
