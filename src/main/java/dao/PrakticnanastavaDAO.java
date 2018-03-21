package dao;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import data.Prakticnanastava;

public class PrakticnanastavaDAO {

    private static final PrakticnanastavaDAO instance = new PrakticnanastavaDAO();

    private PrakticnanastavaDAO() {
    }

    public static PrakticnanastavaDAO getInstance() {
        return instance;
    }

    public List<Prakticnanastava> findAll(EntityManager em) {
        Query query = em.createQuery("SELECT c FROM Prakticnanastava c");
        return query.getResultList();
    }

    public Prakticnanastava findPrakticnanastavaById(EntityManager em, int id) {
        Query query = em.createQuery("SELECT c FROM Prakticnanastava c WHERE c.iDPrakticnaNastava=:iDPrakticnaNastava");
        query.setParameter("iDPrakticnaNastava", id);
        return (Prakticnanastava) query.getSingleResult();
    }

    public Prakticnanastava add(EntityManager em, Prakticnanastava prakticnanastava) {
        em.persist(prakticnanastava);
        em.flush();
        return prakticnanastava;
    }

    public Prakticnanastava edit(EntityManager em, Prakticnanastava prakticnanastava) {
        em.merge(prakticnanastava);
        return prakticnanastava;
    }

    public void delete(EntityManager em, Prakticnanastava prakticnanastava) {
        Prakticnanastava managedPrakticnanastava = em.merge(prakticnanastava);
        em.remove(managedPrakticnanastava);
    }

    public List<Prakticnanastava> getSaktByIndeks(EntityManager em, String brindeksa) {
        Query query = em.createQuery("SELECT c FROM Prakticnanastava c WHERE c.fKBrIndeks.brindeksa=:fKBrIndeks");
        query.setParameter("fKBrIndeks", brindeksa);
        return query.getResultList();
    }

}
