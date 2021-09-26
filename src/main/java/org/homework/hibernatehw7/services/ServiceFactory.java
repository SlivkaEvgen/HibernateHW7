package org.homework.hibernatehw7.services;

import org.homework.hibernatehw7.model.BaseModel;
import org.homework.hibernatehw7.services.interfaces.Service;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class ServiceFactory {

    private final static Map<String, Service> REPOSITORIES = new ConcurrentHashMap<>();

    public synchronized static <E extends BaseModel<ID>, R extends Service<E, ID>, ID> Service<E, ID> of(Class<E> modelClass) {
        final String modelName = modelClass.getName();
        if (!REPOSITORIES.containsKey(modelName)) {
            REPOSITORIES.put(modelName, new ServiceImpl<>(modelClass));
        }
        return REPOSITORIES.get(modelName);
    }
}
