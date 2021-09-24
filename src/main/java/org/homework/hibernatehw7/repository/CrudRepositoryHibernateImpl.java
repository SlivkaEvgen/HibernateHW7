package org.homework.hibernatehw7.repository;

import lombok.SneakyThrows;
import org.hibernate.Session;
import org.hibernate.query.criteria.JpaCriteriaQuery;
import org.homework.hibernatehw7.model.BaseModel;
import org.homework.hibernatehw7.model.Developer;
import org.homework.hibernatehw7.model.Skill;
import org.homework.hibernatehw7.repository.interfaces.CrudRepositoryJDBC;
import org.homework.hibernatehw7.utils.HibernateSessionFactory;

import java.io.Closeable;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

public class CrudRepositoryHibernateImpl<T extends BaseModel<ID>, ID> implements Closeable, CrudRepositoryJDBC<T, ID> {

    private final Class<T> modelClass; //(Class) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];

    public CrudRepositoryHibernateImpl(Class<T> modelClass) {
        this.modelClass = modelClass;
    }

    public Session createSession() {
        Session session = HibernateSessionFactory.getSessionFactory().openSession();
        session.beginTransaction();
        return session;
    }

    public void closeSession(Session session) {
        session.getTransaction().commit();
        session.close();
    }

    private Optional<T> getById(ID id, Session session) {
        return Optional.ofNullable(session.get(modelClass, id));
    }

    @SneakyThrows
    @Override
    public void close() {
        HibernateSessionFactory.close();
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

    private List<T> saveAll(Iterable<T> itbl) {
        return StreamSupport.stream(itbl.spliterator(), false)
                .map(entity -> create(entity)).collect(Collectors.toList());
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
//        getById(id, session).ifPresent(entity -> session.remove(entity));
        getById(id, session).ifPresent(session::remove);
        closeSession(session);
    }
//    public  <T> List<T> findAllWithJpql(Class<T>type,Session session,Long projectId){
//        return session.createQuery("SELECT id ,sum(developers.salary) AS SUM FROM Project WHERE id="+projectId,type).getResultList();
//    }
//
//    public List<T> sum(Long projectId){
//        Session session = createSession();
//        List<Developer> allWithJpql = findAllWithJpql(Developer.class, session, projectId);
//        return (List<T>) allWithJpql;
//    }

    public Set<T> createSet(){
        CrudRepositoryHibernateImpl<T, ID> repositoryHibernate = new CrudRepositoryHibernateImpl<>(modelClass);
        return new HashSet<>(repositoryHibernate.findAll());
    }
}
