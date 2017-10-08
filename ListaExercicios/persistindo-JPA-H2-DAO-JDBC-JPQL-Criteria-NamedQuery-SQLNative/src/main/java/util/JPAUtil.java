package util;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public class JPAUtil {

    private static EntityManagerFactory factory;
    private static ThreadLocal<EntityManager> managerEM;
    private static ThreadLocal<EntityTransaction> managerET;


    private JPAUtil() { }

    public static void init(String nameBD) {
        factory = Persistence.createEntityManagerFactory(nameBD);
        System.out.println("Abriu conexão!");
        managerEM = new ThreadLocal<EntityManager>();
        managerET = new ThreadLocal<EntityTransaction>();
    }

    public static EntityManager getEntityManager() {
        if (managerEM.get() == null) {
            managerEM.set(factory.createEntityManager());
        }
        return managerEM.get();
    }

    public static EntityTransaction getTransaction() {
        if (managerET.get() == null) {
            managerET.set(getEntityManager().getTransaction());
        }
        return managerET.get();
    }

    public static void closeEntityManager(){
        EntityManager em = JPAUtil.managerEM.get(); // ThreadLocal<EntityManager> managerEM;
        if(em != null){
            EntityTransaction tx = em.getTransaction();
            if(tx.isActive())
                tx.commit();
            em.close();
            JPAUtil.managerEM.set(null);
        }
    }

    public static void closeEntityManagerFactory(){
        closeEntityManager();
        JPAUtil.factory.close();
    }

}
