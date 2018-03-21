package dao;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import data.Sakt;

public class SaktDAO {

    private static final SaktDAO instance = new SaktDAO();

    private SaktDAO() {
    }

    public static SaktDAO getInstance() {
        return instance;
    }

    public List<Sakt> findAll(EntityManager em) {
        Query query = em.createQuery("SELECT c FROM Sakt c");
        return query.getResultList();
    }

    public Sakt findSaktById(EntityManager em, int id) {
        Query query = em.createQuery("SELECT c FROM Sakt c WHERE c.iDSkat=:iDSkat");
        query.setParameter("iDSkat", id);
        return (Sakt) query.getSingleResult();
    }

    public Sakt add(EntityManager em, Sakt sakt) {
        em.persist(sakt);
        em.flush();
        return sakt;
    }

    public Sakt edit(EntityManager em, Sakt sakt) {
        em.merge(sakt);
        em.flush();
        return sakt;
    }

    public void delete(EntityManager em, Sakt sakt) {
        Sakt managedSakt = em.merge(sakt);
        em.remove(managedSakt);
    }

    public List<Sakt> findSaktByIndeks(EntityManager em, String brindeksa) {
        Query query = em.createQuery("SELECT c FROM Sakt c WHERE c.fKBrindeksa.brindeksa=:fKBrindeksa");
        query.setParameter("fKBrindeksa", brindeksa);
        return query.getResultList();
    }

}
