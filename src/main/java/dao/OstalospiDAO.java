package dao;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import data.Ostalospi;

public class OstalospiDAO {

    private static final OstalospiDAO instance = new OstalospiDAO();

    private OstalospiDAO() {
    }

    public static OstalospiDAO getInstance() {
        return instance;
    }

    public List<Ostalospi> findAll(EntityManager em) {
        Query query = em.createQuery("SELECT c FROM Ostalospi c");
        return query.getResultList();
    }

    public Ostalospi findOstalospiById(EntityManager em, int id) {
        Query query = em.createQuery("SELECT c FROM Ostalospi c WHERE c.iDOstalo=:iDOstalo");
        query.setParameter("iDOstalo", id);
        return (Ostalospi) query.getSingleResult();
    }

    public Ostalospi add(EntityManager em, Ostalospi ostalospi) {
        em.persist(ostalospi);
        em.flush();
        return ostalospi;
    }

    public Ostalospi edit(EntityManager em, Ostalospi ostalospi) {
        em.merge(ostalospi);
        em.flush();
        return ostalospi;
    }

    public void delete(EntityManager em, Ostalospi ostalospi) {
        Ostalospi managedOstalospi = em.merge(ostalospi);
        em.remove(managedOstalospi);
    }

}
