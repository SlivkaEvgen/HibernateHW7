package org.homework.hibernatehw7.services.interfaces;

import org.homework.hibernatehw7.model.BaseModel;

import java.util.List;
import java.util.Optional;

public interface Service<T extends BaseModel<ID>, ID> {
    Optional<T> findById(ID id);

    List<T> findAll();

    T create(T t);

//    List<T> createAll(Iterable<T> itbl);

    T update(ID id, T t);

    void delete(ID id);

    void close();

    T createNewSkill(String activity, String level);

    void updateSkill(ID id, String activity, String level, ID developerId);

    T createNewProject(String name, Long cost, ID companyId, ID customerId, ID       developerId);

    void updateProject(ID id, String name, Long cost, ID companyId, ID customerId, ID developerId);

    List<String> getListProjectsWithDate();

    T createNewDeveloper(String name, Long age, String gender, String email, Long salary, ID skillId, ID companyId, ID projectId);

    void updateDeveloper(ID id, String name, Long age, String gender, String email, Long salary, ID skillId, ID companyId, ID projectId);

    List<T> getDevelopersFromOneProject(ID projectId);

    List<T> getDevelopersByActivity(String nameActivity);

    List<T> getDevelopersByLevel(String nameLevel);

    ID getSumSalariesDevelopersOfOneProject(ID projectId);

    T createNewCustomer(String name, String city, Long budget, ID projectId);

    void updateCustomer(ID id, String name, String city, Long budget, ID companyId, ID projectId);

    T createNewCompany(String name, String city, ID projectId, ID developerId);

    void updateCompany(ID id, String name, String city, ID projectId, ID developerId);

}
