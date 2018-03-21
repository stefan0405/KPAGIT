package dao;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import data.Smer;

public class SmerDAO {

    private static final SmerDAO instance = new SmerDAO();

    private SmerDAO() {
    }

    public static SmerDAO getInstance() {
        return instance;
    }

    public List<Smer> findAll(EntityManager em) {
        Query query = em.createQuery("SELECT c FROM Smer c");
        return query.getResultList();
    }

    public Smer findSmerById(EntityManager em, int id) {
        Query query = em.createQuery("SELECT c FROM Smer c WHERE c.iDSmer=:iDSmer");
        query.setParameter("iDSmer", id);
        return (Smer) query.getSingleResult();
    }

    public Smer add(EntityManager em, Smer smer) {
        em.persist(smer);
        em.flush();
        return smer;
    }

    public Smer edit(EntityManager em, Smer smer) {
        em.merge(smer);
        em.flush();
        return smer;
    }

    public void delete(EntityManager em, Smer smer) {
        Smer managedSmer = em.merge(smer);
        em.remove(managedSmer);
    }

}
