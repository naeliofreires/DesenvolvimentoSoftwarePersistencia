package dao;

import interfaces.iGeneric;
import util.JPAUtil;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.criteria.CriteriaQuery;

import java.util.List;

public class JPAGeneric<K> implements iGeneric<K> {

    private Class<K> persistentClass;
    private static EntityManager em;
    private static EntityTransaction et;

    public JPAGeneric(Class<K> persistentClass) {
        super();
        this.persistentClass = persistentClass;
        em = JPAUtil.getEntityManager();
        et = JPAUtil.getTransaction();
    }

    public void save(K entity) {
        getEm().merge(entity);
        System.out.println("\nSave!");
    }

    public void delete(K entity) {
        getEm().remove(getEm().merge(entity));
    }

    public K find(Object key) {
        return getEm().find(persistentClass, key);
    }

    public List<K> find() {
        CriteriaQuery<K> cq = getEm().getCriteriaBuilder().createQuery(persistentClass);
        cq.from(persistentClass);
        return getEm().createQuery(cq).getResultList();
    }

    public EntityManager getEm() {
        return JPAUtil.getEntityManager();
    }

    public void beginTransaction() {
        JPAUtil.getTransaction().begin();
    }

    public void commit() {
        JPAUtil.getTransaction().commit();
    }

    public void rollback() {
        JPAUtil.getTransaction().rollback();
    }

    public void close() {
        JPAUtil.closeEntityManager();
    }
}
