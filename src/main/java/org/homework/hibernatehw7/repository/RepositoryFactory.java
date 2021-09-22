package org.homework.hibernatehw7.repository;

import org.homework.hibernatehw7.model.BaseModel;
import org.homework.hibernatehw7.repository.interfaces.CrudRepositoryJDBC;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class RepositoryFactory {

    private final static Map<String, CrudRepositoryJDBC> REPOSITORIES = new ConcurrentHashMap<>();

    public synchronized static <E extends BaseModel<ID>, R extends CrudRepositoryJDBC<E, ID>, ID> CrudRepositoryJDBC<E, ID> of(Class<E> modelClass) {
        final String modelName = modelClass.getName();
        if (!REPOSITORIES.containsKey(modelName)) {
            REPOSITORIES.put(modelName, new CrudRepositoryHibernateImpl<>(modelClass));
        }
        return REPOSITORIES.get(modelName);
    }
}
