package dao;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import data.Personalizovanaobavestenja;

public class PersonalizovanaobavestenjaDAO {

    private static final PersonalizovanaobavestenjaDAO instance = new PersonalizovanaobavestenjaDAO();

    private PersonalizovanaobavestenjaDAO() {
    }

    public static PersonalizovanaobavestenjaDAO getInstance() {
        return instance;
    }

    public List<Personalizovanaobavestenja> findAll(EntityManager em) {
        Query query = em.createQuery("SELECT c FROM Personalizovanaobavestenja c");
        return query.getResultList();
    }

    public Personalizovanaobavestenja findPersonalizovanaobavestenjaById(EntityManager em, int id) {
        Query query = em.createQuery("SELECT c FROM Personalizovanaobavestenja c WHERE c.idPObavestenja=:id");
        query.setParameter("id", id);
        return (Personalizovanaobavestenja) query.getSingleResult();
    }

    public Personalizovanaobavestenja add(EntityManager em, Personalizovanaobavestenja personalizovanaobavestenja) {
        em.persist(personalizovanaobavestenja);
        em.flush();
        return personalizovanaobavestenja;
    }

    public Personalizovanaobavestenja edit(EntityManager em, Personalizovanaobavestenja personalizovanaobavestenja) {
        em.merge(personalizovanaobavestenja);
        em.flush();
        return personalizovanaobavestenja;
    }

    public void delete(EntityManager em, Personalizovanaobavestenja personalizovanaobavestenja) {
        Personalizovanaobavestenja managedPersonalizovanaobavestenja = em.merge(personalizovanaobavestenja);
        em.remove(managedPersonalizovanaobavestenja);
    }

}
