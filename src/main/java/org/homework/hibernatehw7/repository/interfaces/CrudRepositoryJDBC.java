package org.homework.hibernatehw7.repository.interfaces;

import org.homework.hibernatehw7.model.BaseModel;

import java.util.List;
import java.util.Optional;

public interface CrudRepositoryJDBC<T extends BaseModel<ID>, ID> {

    Optional<T> findById(ID id);

    List<T> findAll();

    T create(T t);

    T update(ID id, T t);

    void delete(ID id);

    void close();

    //    List<T> createAll(Iterable<T> itbl);
}
