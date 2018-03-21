package service;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import exceptions.KPAException;
import dao.UlogaDAO;
import dao.EntityManagerWrapper;
import data.Uloga;

public class UlogaService {

    private static final UlogaService instance = new UlogaService();

    private UlogaService() {
    }

    public static UlogaService getInstance() {
        return instance;
    }

    public List<Uloga> getAll() {
        return UlogaDAO.getInstance().findAll(EntityManagerWrapper.getEntityManager());
    }

    public Uloga getUlogaById(int id) {
        return UlogaDAO.getInstance().findUlogaById(EntityManagerWrapper.getEntityManager(), id);
    }

    public Uloga add(Uloga uloga) throws KPAException {
        //validirati ulazne parametre, parametre koje je korisnik uneo u formi
        validate(uloga);

        EntityManager em = EntityManagerWrapper.getEntityManager();
        try {
            em.getTransaction().begin();
            final Uloga newUloga = UlogaDAO.getInstance().add(em, uloga);
            em.getTransaction().commit();

            return newUloga;
        } finally {
            em.close();
        }
    }

    public Uloga edit(Uloga uloga) throws KPAException {
        //validirati ulazne parametre, parametre koje je korisnik uneo u formi
        validate(uloga);

        EntityManager em = EntityManagerWrapper.getEntityManager();
        try {
            em.getTransaction().begin();
            final Uloga updatedUloga = UlogaDAO.getInstance().edit(em, uloga);
            em.getTransaction().commit();

            return updatedUloga;
        } finally {
            em.close();
        }
    }

    public void delete(Uloga uloga) throws KPAException {
        //validirati ulazne parametre, parametre koje je korisnik uneo u formi
        if (uloga.getIDUloga() == null) {
            throw new KPAException("Nepoznat ID korisnika.");
        }

        EntityManager em = EntityManagerWrapper.getEntityManager();
        try {
            em.getTransaction().begin();
            UlogaDAO.getInstance().delete(em, uloga);
            em.getTransaction().commit();
        } finally {
            em.close();
        }
    }

    private void validate(Uloga uloga) throws KPAException {
        List<String> errors = new ArrayList<>();
        if (uloga.getNaziv() == null || uloga.getNaziv().trim().isEmpty()) {
            errors.add("Parametar naziv je obavezan.");
        }
        if (!errors.isEmpty()) {
            throw new KPAException(errors);
        }
    }

}
