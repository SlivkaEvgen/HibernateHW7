package org.homework.hibernatehw7.client;

import org.homework.hibernatehw7.model.Developer;
import org.homework.hibernatehw7.repository.CrudRepositoryHibernateImpl;

public class Main {

    public static void main(String[] args) {

        CrudRepositoryHibernateImpl<Developer,Long> crudRepositoryHibernate = new CrudRepositoryHibernateImpl<>(Developer.class);
        System.out.println(crudRepositoryHibernate.findJava());
//        new CommandImpl().start();
    }
}
