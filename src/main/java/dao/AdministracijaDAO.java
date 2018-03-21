package dao;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import data.Administracija;

public class AdministracijaDAO {

    private static final AdministracijaDAO instance = new AdministracijaDAO();

    private AdministracijaDAO() {
    }

    public static AdministracijaDAO getInstance() {
        return instance;
    }

    public List<Administracija> findAll(EntityManager em) {
        Query query = em.createQuery("SELECT c FROM Administracija c");
        return query.getResultList();
    }

    public Administracija findAdministracijaById(EntityManager em, int id) {
        Query query = em.createQuery("SELECT a FROM Administracija a WHERE a.iDAdmin=:iDAdmin");
        query.setParameter("iDAdmin", id);
        return (Administracija) query.getSingleResult();
    }

    public Administracija add(EntityManager em, Administracija administracija) {
        em.persist(administracija);
        em.flush();
        return administracija;
    }

    public Administracija edit(EntityManager em, Administracija administracija) {
        em.merge(administracija);
        em.flush();
        return administracija;
    }

    public void delete(EntityManager em, Administracija administracija) {
        Administracija managedAdministracija = em.merge(administracija);
        em.remove(managedAdministracija);
    }

}
