package org.homework.hibernatehw7.services;

import org.homework.hibernatehw7.model.BaseModel;
import org.homework.hibernatehw7.services.interfaces.Service;

import java.io.Serializable;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class ServiceFactory<T extends BaseModel<ID>,ID> extends ModelService<T,ID> implements Serializable {

    private static final long serialVersionUID = 3334644651928374654L;
    private final static Map<String, Service> REPOSITORIES = new ConcurrentHashMap<>();

    public ServiceFactory(Class<T> classModel) {
        super(classModel);
    }

    public synchronized static <T extends BaseModel<ID>, R extends Service<T, ID>, ID> Service<T, ID> of(Class<T> modelClass) {
        final String modelName = modelClass.getName();
        if (!REPOSITORIES.containsKey(modelName)) {
            REPOSITORIES.put(modelName, new ServiceImpl<>(modelClass));
        }
        return REPOSITORIES.get(modelName);
    }
}
