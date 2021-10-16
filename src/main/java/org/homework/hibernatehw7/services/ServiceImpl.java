package org.homework.hibernatehw7.services;

import org.homework.hibernatehw7.model.BaseModel;
import org.homework.hibernatehw7.repository.RepositoryFactory;
import org.homework.hibernatehw7.repository.interfaces.CrudRepositoryJDBC;
import org.homework.hibernatehw7.services.interfaces.Service;

import java.io.Closeable;
import java.io.Serializable;
import java.util.List;
import java.util.Optional;

public class ServiceImpl<T extends BaseModel<ID>, ID> implements Closeable, Service<T, ID>, Serializable {

    private static final long serialVersionUID = 3334744651928374654L;
    private final CrudRepositoryJDBC<T, ID> CRUD_REPOSITORY_JDBC;

    public ServiceImpl(Class<T> modelClass) {
        this.CRUD_REPOSITORY_JDBC = RepositoryFactory.of(modelClass);
    }

    @Override
    public Optional<T> findById(ID id) {
        return CRUD_REPOSITORY_JDBC.findById(id);
    }

    @Override
    public List<T> findAll() {
        return CRUD_REPOSITORY_JDBC.findAll();
    }

    @Override
    public T create(T t) {
        return CRUD_REPOSITORY_JDBC.create(t);
    }

    @Override
    public T update(ID id, T t) {
        return CRUD_REPOSITORY_JDBC.update(id, t);
    }

    @Override
    public void delete(ID id) {
        CRUD_REPOSITORY_JDBC.delete(id);
    }

    @Override
    public void close() {
        CRUD_REPOSITORY_JDBC.close();
    }
}
