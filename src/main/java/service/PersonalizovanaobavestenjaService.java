package service;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import exceptions.KPAException;
import dao.PersonalizovanaobavestenjaDAO;
import dao.EntityManagerWrapper;
import data.Personalizovanaobavestenja;

public class PersonalizovanaobavestenjaService {

    private static final PersonalizovanaobavestenjaService instance = new PersonalizovanaobavestenjaService();

    private PersonalizovanaobavestenjaService() {
    }

    public static PersonalizovanaobavestenjaService getInstance() {
        return instance;
    }

    public List<Personalizovanaobavestenja> getAll() {
        return PersonalizovanaobavestenjaDAO.getInstance().findAll(EntityManagerWrapper.getEntityManager());
    }

    public Personalizovanaobavestenja getPersonalizovanaobavestenjaById(int id) {
        return PersonalizovanaobavestenjaDAO.getInstance().findPersonalizovanaobavestenjaById(EntityManagerWrapper.getEntityManager(), id);
    }

    public Personalizovanaobavestenja add(Personalizovanaobavestenja personalizovanaobavestenja) throws KPAException {
        //validirati ulazne parametre, parametre koje je korisnik uneo u formi
        validate(personalizovanaobavestenja);

        EntityManager em = EntityManagerWrapper.getEntityManager();
        try {
            em.getTransaction().begin();
            final Personalizovanaobavestenja newPersonalizovanaobavestenja = PersonalizovanaobavestenjaDAO.getInstance().add(em, personalizovanaobavestenja);
            em.getTransaction().commit();

            return newPersonalizovanaobavestenja;
        } finally {
            em.close();
        }
    }

    public Personalizovanaobavestenja edit(Personalizovanaobavestenja personalizovanaobavestenja) throws KPAException {
        //validirati ulazne parametre, parametre koje je korisnik uneo u formi
        validate(personalizovanaobavestenja);

        EntityManager em = EntityManagerWrapper.getEntityManager();
        try {
            em.getTransaction().begin();
            final Personalizovanaobavestenja updatedPersonalizovanaobavestenja = PersonalizovanaobavestenjaDAO.getInstance().edit(em, personalizovanaobavestenja);
            em.getTransaction().commit();

            return updatedPersonalizovanaobavestenja;
        } finally {
            em.close();
        }
    }

    public void delete(Personalizovanaobavestenja personalizovanaobavestenja) throws KPAException {
        //validirati ulazne parametre, parametre koje je korisnik uneo u formi
        if (personalizovanaobavestenja.getIdPObavestenja() == null) {
            throw new KPAException("Nepoznat ID korisnika.");
        }

        EntityManager em = EntityManagerWrapper.getEntityManager();
        try {
            em.getTransaction().begin();
            PersonalizovanaobavestenjaDAO.getInstance().delete(em, personalizovanaobavestenja);
            em.getTransaction().commit();
        } finally {
            em.close();
        }
    }

    private void validate(Personalizovanaobavestenja personalizovanaobavestenja) throws KPAException {
        List<String> errors = new ArrayList<>();
        if (!errors.isEmpty()) {
            throw new KPAException(errors);
        }
    }

}
