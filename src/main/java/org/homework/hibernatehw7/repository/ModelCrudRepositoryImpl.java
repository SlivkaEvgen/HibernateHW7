package org.homework.hibernatehw7.repository;

import org.homework.hibernatehw7.model.BaseModel;
import org.homework.hibernatehw7.repository.interfaces.CrudRepositoryJDBC;
import java.io.Serializable;
import java.util.List;
import java.util.Optional;

public abstract class ModelCrudRepositoryImpl<T extends BaseModel<ID>, ID> implements CrudRepositoryJDBC<T, ID>, Serializable {

    private static final long serialVersionUID = 3333344651928374654L;
    private final CrudRepositoryJDBC<T, ID> CRUD_REPOSITORY_JDBC;

    public ModelCrudRepositoryImpl(Class<T> classModel) {
        CRUD_REPOSITORY_JDBC = RepositoryFactory.of(classModel);
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
