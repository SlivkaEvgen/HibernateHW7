package org.homework.hibernatehw7.services;

import org.homework.hibernatehw7.model.BaseModel;
import org.homework.hibernatehw7.repository.RepositoryFactory;
import org.homework.hibernatehw7.repository.interfaces.CrudRepositoryJDBC;
import org.homework.hibernatehw7.services.interfaces.Service;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;

public abstract class ModelService<T extends BaseModel<ID>, ID> implements Service<T, ID>, Serializable {

    private static final long serialVersionUID = 3334144651928374654L;
    private final CrudRepositoryJDBC<T, ID> REPOSITORY_JDBC;

    public ModelService(Class<T> classModel) {
        REPOSITORY_JDBC = RepositoryFactory.of(classModel);
    }

    @Override
    public Optional<T> findById(ID id) {
        return REPOSITORY_JDBC.findById(id);
    }

    @Override
    public List<T> findAll() {
        return REPOSITORY_JDBC.findAll();
    }

    @Override
    public T create(T t) {
        return REPOSITORY_JDBC.create(t);
    }

    @Override
    public T update(ID id, T t) {
        return REPOSITORY_JDBC.update(id, t);
    }

    @Override
    public void delete(ID id) {
        REPOSITORY_JDBC.delete(id);
    }

    @Override
    public void close() {
        REPOSITORY_JDBC.close();
    }
}
