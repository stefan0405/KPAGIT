package dao;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import data.Uloga;

public class UlogaDAO {

    private static final UlogaDAO instance = new UlogaDAO();

    private UlogaDAO() {
    }

    public static UlogaDAO getInstance() {
        return instance;
    }

    public List<Uloga> findAll(EntityManager em) {
        Query query = em.createQuery("SELECT c FROM Uloga c");
        return query.getResultList();
    }

    public Uloga findUlogaById(EntityManager em, int id) {
        Query query = em.createQuery("SELECT c FROM Uloga c WHERE c.iDUloga=:iDUloga");
        query.setParameter("id", id);
        return (Uloga) query.getSingleResult();
    }

    public Uloga add(EntityManager em, Uloga uloga) {
        em.persist(uloga);
        em.flush();
        return uloga;
    }

    public Uloga edit(EntityManager em, Uloga uloga) {
        em.merge(uloga);
        em.flush();
        return uloga;
    }

    public void delete(EntityManager em, Uloga uloga) {
        Uloga managedUloga = em.merge(uloga);
        em.remove(managedUloga);
    }

}
