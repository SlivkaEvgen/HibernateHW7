package org.homework.hibernatehw7.services;

import org.homework.hibernatehw7.model.BaseModel;
import org.homework.hibernatehw7.repository.RepositoryFactory;
import org.homework.hibernatehw7.services.interfaces.Service;

import java.io.Closeable;
import java.util.List;
import java.util.Optional;

public class ServiceImpl<T extends BaseModel<ID>, ID> implements Closeable, Service<T, ID> {

    private final Class<T> modelClass;
    private static ServiceImpl service;

    public static ServiceImpl getInstance(Class modelClass) {
        if (service == null) {
            synchronized (ServiceImpl.class) {
                if (service == null) {
                    service = new ServiceImpl(modelClass);
                }
            }
        }
        return service;
    }

    public ServiceImpl(Class<T> modelClass) {
        this.modelClass = modelClass;
    }

    @Override
    public Optional<T> findById(ID id) {
        return RepositoryFactory.of(modelClass).findById(id);
    }

    @Override
    public List<T> findAll() {
        return RepositoryFactory.of(modelClass).findAll();
    }

    @Override
    public T create(T t) {
        return RepositoryFactory.of(modelClass).create(t);
    }

    @Override
    public T update(ID id, T t) {
        return RepositoryFactory.of(modelClass).update(id, t);
    }

    @Override
    public void delete(ID id) {
        RepositoryFactory.of(modelClass).delete(id);
    }

    @Override
    public void close() {
        RepositoryFactory.of(modelClass).close();
    }
}
