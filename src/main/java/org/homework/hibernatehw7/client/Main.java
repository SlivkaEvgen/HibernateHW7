package org.homework.hibernatehw7.client;

import org.homework.hibernatehw7.controller.ControllerConsole;
import org.homework.hibernatehw7.model.Company;
import org.homework.hibernatehw7.services.CompanyServiceImpl;
import org.homework.hibernatehw7.services.ServiceFactory;
import org.homework.hibernatehw7.services.interfaces.CompanyService;
import org.homework.hibernatehw7.services.interfaces.Service;

public class Main {

    public static void main(String[] args) {

         new ControllerConsole().start();

        CompanyService serviceFactory = new CompanyServiceImpl(Company.class);
        Service<Company, Long> service = ServiceFactory.of(Company.class);
        CompanyService  longService = (CompanyService) ServiceFactory.of(Company.class);

    }
}
