package dao;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import data.Ostaloprakticna;

public class OstaloprakticnaDAO {

    private static final OstaloprakticnaDAO instance = new OstaloprakticnaDAO();

    private OstaloprakticnaDAO() {
    }

    public static OstaloprakticnaDAO getInstance() {
        return instance;
    }

    public List<Ostaloprakticna> findAll(EntityManager em) {
        Query query = em.createQuery("SELECT c FROM Ostaloprakticna c");
        return query.getResultList();
    }

    public Ostaloprakticna findOstaloprakticnaById(EntityManager em, int id) {
        Query query = em.createQuery("SELECT c FROM Ostaloprakticna c WHERE c.iDPrakticnaNastava=:iDPrakticnaNastava");
        query.setParameter("iDPrakticnaNastava", id);
        return (Ostaloprakticna) query.getSingleResult();
    }

    public Ostaloprakticna add(EntityManager em, Ostaloprakticna ostaloprakticna) {
        em.persist(ostaloprakticna);
        em.flush();
        return ostaloprakticna;
    }

    public Ostaloprakticna edit(EntityManager em, Ostaloprakticna ostaloprakticna) {
        em.merge(ostaloprakticna);
        em.flush();
        return ostaloprakticna;
    }

    public void delete(EntityManager em, Ostaloprakticna ostaloprakticna) {
        Ostaloprakticna managedOstaloprakticna = em.merge(ostaloprakticna);
        em.remove(managedOstaloprakticna);
    }

}
