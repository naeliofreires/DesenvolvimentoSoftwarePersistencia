package service;

import model.Employees;
import org.hibernate.SQLQuery;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
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
    public void SQLNativeGetAll(){
//        String sql = "SELECT p FROM Employees p";
//        SQLQuery query = (SQLQuery) em.createNativeQuery(sql)
//        query.addEntity(Employees.class);
//        return query.list();
    }

    public List findEmployesById(final Integer id){
        return  em.createQuery("SELECT e FROM Employees e WHERE e.id = ?1").setParameter(1,id).getResultList();
    }
}
