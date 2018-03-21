package service;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import exceptions.KPAException;
import dao.OstalospiDAO;
import dao.EntityManagerWrapper;
import data.Ostalospi;

public class OstalospiService {

    private static final OstalospiService instance = new OstalospiService();

    private OstalospiService() {
    }

    public static OstalospiService getInstance() {
        return instance;
    }

    public List<Ostalospi> getAll() {
        return OstalospiDAO.getInstance().findAll(EntityManagerWrapper.getEntityManager());
    }

    public Ostalospi getOstalospiById(int id) {
        return OstalospiDAO.getInstance().findOstalospiById(EntityManagerWrapper.getEntityManager(), id);
    }

    public Ostalospi add(Ostalospi ostalospi) throws KPAException {
        //validirati ulazne parametre, parametre koje je korisnik uneo u formi
        validate(ostalospi);

        EntityManager em = EntityManagerWrapper.getEntityManager();
        try {
            em.getTransaction().begin();
            final Ostalospi newOstalospi = OstalospiDAO.getInstance().add(em, ostalospi);
            em.getTransaction().commit();

            return newOstalospi;
        } finally {
            em.close();
        }
    }

    public Ostalospi edit(Ostalospi ostalospi) throws KPAException {
        //validirati ulazne parametre, parametre koje je korisnik uneo u formi
        validate(ostalospi);

        EntityManager em = EntityManagerWrapper.getEntityManager();
        try {
            em.getTransaction().begin();
            final Ostalospi updatedOstalospi = OstalospiDAO.getInstance().edit(em, ostalospi);
            em.getTransaction().commit();

            return updatedOstalospi;
        } finally {
            em.close();
        }
    }

    public void delete(Ostalospi ostalospi) throws KPAException {
        //validirati ulazne parametre, parametre koje je korisnik uneo u formi
        if (ostalospi.getIDOstalo() == null) {
            throw new KPAException("Nepoznat ID korisnika.");
        }

        EntityManager em = EntityManagerWrapper.getEntityManager();
        try {
            em.getTransaction().begin();
            OstalospiDAO.getInstance().delete(em, ostalospi);
            em.getTransaction().commit();
        } finally {
            em.close();
        }
    }

    private void validate(Ostalospi ostalospi) throws KPAException {
        List<String> errors = new ArrayList<>();
        if (ostalospi.getNapomena() == null || ostalospi.getNapomena().trim().isEmpty()) {
            errors.add("Parametar ime je obavezan.");
        }
        if (!errors.isEmpty()) {
            throw new KPAException(errors);
        }
    }

}
