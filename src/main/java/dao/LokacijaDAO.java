package dao;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import data.Lokacija;

public class LokacijaDAO {

    private static final LokacijaDAO instance = new LokacijaDAO();

    private LokacijaDAO() {
    }

    public static LokacijaDAO getInstance() {
        return instance;
    }

    public List<Lokacija> findAll(EntityManager em) {
        Query query = em.createQuery("SELECT c FROM Lokacija c");
        return query.getResultList();
    }

    public Lokacija findLokacijaById(EntityManager em, int id) {
        Query query = em.createQuery("SELECT c FROM Lokacija c WHERE c.iDLokacije=:id");
        query.setParameter("id", id);
        return (Lokacija) query.getSingleResult();
    }
    
    public Lokacija findLokacijaByParm(EntityManager em, String pUprava, String pStanica) {
        Query query = em.createQuery("SELECT c FROM Lokacija c WHERE c.policijskaUprava=:pUprava AND c.policijskaStanica=:pStanica");
        query.setParameter("pUprava", pUprava);
        query.setParameter("pStanica", pStanica);
        return (Lokacija) query.getSingleResult();
    }

    public Lokacija add(EntityManager em, Lokacija lokacija) {
        em.persist(lokacija);
        em.flush();
        return lokacija;
    }

    public Lokacija edit(EntityManager em, Lokacija lokacija) {
        em.merge(lokacija);
        em.flush();
        return lokacija;
    }

    public void delete(EntityManager em, Lokacija lokacija) {
        Lokacija managedLokacija = em.merge(lokacija);
        em.remove(managedLokacija);
    }

}
