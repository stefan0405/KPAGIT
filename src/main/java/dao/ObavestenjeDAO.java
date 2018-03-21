package dao;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import data.Obavestenje;

public class ObavestenjeDAO {

    private static final ObavestenjeDAO instance = new ObavestenjeDAO();

    private ObavestenjeDAO() {
    }

    public static ObavestenjeDAO getInstance() {
        return instance;
    }

    public List<Obavestenje> findAll(EntityManager em) {
        Query query = em.createQuery("SELECT c FROM Obavestenje c");
        return query.getResultList();
    }

    public Obavestenje findObavestenjeById(EntityManager em, int id) {
        Query query = em.createQuery("SELECT c FROM Obavestenje c WHERE c.iDObavestenje=:iDObavestenje");
        query.setParameter("iDObavestenje", id);
        return (Obavestenje) query.getSingleResult();
    }

    public Obavestenje add(EntityManager em, Obavestenje obavestenje) {
        em.persist(obavestenje);
        em.flush();
        return obavestenje;
    }

    public Obavestenje edit(EntityManager em, Obavestenje obavestenje) {
        em.merge(obavestenje);
        em.flush();
        return obavestenje;
    }

    public void delete(EntityManager em, Obavestenje obavestenje) {
        Obavestenje managedObavestenje = em.merge(obavestenje);
        em.remove(managedObavestenje);
    }

}
