package org.homework.hibernatehw7.repository;

import org.hibernate.Session;
import org.hibernate.query.criteria.JpaCriteriaQuery;
import org.homework.hibernatehw7.model.BaseModel;
import org.homework.hibernatehw7.repository.interfaces.CrudRepositoryJDBC;
import org.homework.hibernatehw7.utils.HibernateSessionFactory;
import java.io.Closeable;
import java.io.Serializable;
import java.util.List;
import java.util.Optional;

public class CrudRepositoryHibernateImpl<T extends BaseModel<ID>, ID> implements Closeable, CrudRepositoryJDBC<T, ID>, Serializable {

    private static final long serialVersionUID = 3333354651928374654L;
    private final Class<T> modelClass;

    public CrudRepositoryHibernateImpl(Class<T> modelClass) {
        this.modelClass = modelClass;
    }

    @Override
    public Optional<T> findById(ID id) {
        Session session = createSession();
        Optional<T> result = getById(id, session);
        closeSession(session);
        return result;
    }

    @Override
    public List<T> findAll() {
        Session session = createSession();
        JpaCriteriaQuery<T> jpaCriteriaQuery = session.getCriteriaBuilder().createQuery(modelClass);
        List<T> resultList = session.createQuery(jpaCriteriaQuery.select(jpaCriteriaQuery.from(modelClass))).getResultList();
        closeSession(session);
        return resultList;
    }

    @Override
    public T create(T t) {
        Session session = createSession();
        ID id = (ID) session.save(t);
        Optional<T> result = getById(id, session);
        closeSession(session);
        return result.get();
    }

    @Override
    public T update(ID id, T t) {
        Session session = createSession();
        session.saveOrUpdate(t);
        ID id1 = t.getId();
        session.getTransaction().commit();
        return findById(id1).get();
    }

    @Override
    public void delete(ID id) {
        Session session = createSession();
        getById(id, session).ifPresent(session::remove);
        closeSession(session);
    }

    @Override
    public void close() {
        HibernateSessionFactory.close();
    }

    private Optional<T> getById(ID id, Session session) {
        return Optional.ofNullable(session.get(modelClass, id));
    }

    private Session createSession() {
        Session session = HibernateSessionFactory.getSessionFactory().openSession();
        session.beginTransaction();
        return session;
    }

    private void closeSession(Session session) {
        session.getTransaction().commit();
        session.close();
    }
}
