package org.homework.hibernatehw7.controller.interfaces;

public interface Command extends Controller {

    void getCommand();

    void createCommand();

    void updateCommand();

    void deleteCommand();
}
