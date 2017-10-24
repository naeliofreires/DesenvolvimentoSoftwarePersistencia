package service;

import model.Dependents;
import util.JPAUtil;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.List;

public class DependentsServiceBean {

//    Item A
//    select * TABLE campo LIKE 'A%' ...
//    SELECT D.nome, E.nme FROM dependentes A, employees E WHERE name LIKE 'd%' AND d.empl_id = e.id;

//    JPQL
    public List JPQLNameLike(){
        JPAUtil.init("devPostgreSQL");
        List l = (List) JPAUtil.getEntityManager().createQuery
                ("FROM Dependents WHERE  name LIKE 'd%'", Dependents.class).getResultList();
        return l;
    }

//    Criteria Query
    public List Criteria(){
        JPAUtil.init("devPostgreSQL");
        EntityManager em = JPAUtil.getEntityManager();
        CriteriaBuilder cb  = em.getCriteriaBuilder();
        CriteriaQuery<Dependents> cf = cb.createQuery(Dependents.class);
        Root<Dependents> r = cf.from(Dependents.class);
        Predicate predicate =  cb.and();
        predicate = cb.and(predicate,cb.like(r.<String>get("name"), "d%"));
        TypedQuery<Dependents> tp = em.createQuery(cf.select(r).where(predicate));
        List<Dependents> dep = tp.getResultList();
        JPAUtil.closeEntityManager();
        return dep;
    }

//    Named Query
    public List Query(){
        JPAUtil.init("devPostgreSQL");
        return JPAUtil.getEntityManager().createNamedQuery("Dependents.LikeName").getResultList();
    }

//    SQL Native
    public List SQLNative(){
        JPAUtil.init("devPostgreSQL");
        final String SQL = "select * from dependents where name like 'd%'";
        Query query = JPAUtil.getEntityManager().createNativeQuery(SQL, Dependents.class);
        List<Dependents> l = query.getResultList();
        return l;
    }

}
