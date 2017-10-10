package service;

import model.Dependents;
import model.Employees;
import util.JPAUtil;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;

import java.util.List;

public class EmployeeServiceBean {

//    @PersistenceContext(unitName="devPostgreSQL")
    EntityManagerFactory emf = Persistence.createEntityManagerFactory("devPostgreSQL");
    EntityManager em = emf.createEntityManager();


//    Item B

//    JQPL
    public List JQPLGetAll(){
        return em.createQuery("from Employees",Employees.class).getResultList();
    }

//    Criteria Query
    public List CriteriaGetAll(){
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Employees> cq = cb.createQuery(Employees.class);
        cq.from(Employees.class);
        return em.createQuery(cq).getResultList();
    }

//    Named Query
    public List NamedQueryGetAll(){
        return em.createNamedQuery("Employees.findAll").getResultList();
    }

//    SQL Native
    public List<Dependents> SQLNativeGetAll(){
        JPAUtil.init("devPostgreSQL");
        final String SQL = "SELECT p FROM Employees p";
        Query query = JPAUtil.getEntityManager().createNativeQuery(SQL, Employees.class);
        List<Dependents> l = query.getResultList();
        return l;
    }

    public List findEmployesById(final Integer id){
        return  em.createQuery("SELECT e FROM Employees e WHERE e.id = ?1").setParameter(1,id).getResultList();
    }
}
