package service;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import exceptions.KPAException;
import dao.PrakticnanastavaDAO;
import dao.EntityManagerWrapper;
import data.Prakticnanastava;

public class PrakticnanastavaService {

    private static final PrakticnanastavaService instance = new PrakticnanastavaService();

    private PrakticnanastavaService() {
    }

    public static PrakticnanastavaService getInstance() {
        return instance;
    }

    public List<Prakticnanastava> getAll() {
        return PrakticnanastavaDAO.getInstance().findAll(EntityManagerWrapper.getEntityManager());
    }

    public Prakticnanastava getPrakticnanastavaById(int id) {
        return PrakticnanastavaDAO.getInstance().findPrakticnanastavaById(EntityManagerWrapper.getEntityManager(), id);
    }

    public Prakticnanastava add(Prakticnanastava prakticnanastava) throws KPAException {
        //validirati ulazne parametre, parametre koje je korisnik uneo u formi
        validate(prakticnanastava);

        EntityManager em = EntityManagerWrapper.getEntityManager();
        try {
            em.getTransaction().begin();
            final Prakticnanastava newPrakticnanastava = PrakticnanastavaDAO.getInstance().add(em, prakticnanastava);
            em.getTransaction().commit();

            return newPrakticnanastava;
        } finally {
            em.close();
        }
    }

    public Prakticnanastava edit(Prakticnanastava prakticnanastava) throws KPAException {
        //validirati ulazne parametre, parametre koje je korisnik uneo u formi
        validate(prakticnanastava);

        EntityManager em = EntityManagerWrapper.getEntityManager();
        try {
            em.getTransaction().begin();
            final Prakticnanastava updatedPrakticnanastava = PrakticnanastavaDAO.getInstance().edit(em, prakticnanastava);
            em.getTransaction().commit();

            return updatedPrakticnanastava;
        } finally {
            em.close();
        }
    }

    public void delete(Prakticnanastava prakticnanastava) throws KPAException {
        //validirati ulazne parametre, parametre koje je korisnik uneo u formi
        if (prakticnanastava.getIDPrakticnaNastava() == null) {
            throw new KPAException("Nepoznat ID korisnika.");
        }

        EntityManager em = EntityManagerWrapper.getEntityManager();
        try {
            em.getTransaction().begin();
            PrakticnanastavaDAO.getInstance().delete(em, prakticnanastava);
            em.getTransaction().commit();
        } finally {
            em.close();
        }
    }

    private void validate(Prakticnanastava prakticnanastava) throws KPAException {
        List<String> errors = new ArrayList<>();
        if (prakticnanastava.getFKBrIndeks() == null) {
            errors.add("Parametar broj indeksa je obavezan.");
        }
        if (prakticnanastava.getDatum() == null || prakticnanastava.getDatum().trim().isEmpty()) {
            errors.add("Parametar datum je obavezan.");
        }
        if (!errors.isEmpty()) {
            throw new KPAException(errors);
        }
    }

    public List<Prakticnanastava> getSaktByIndeks(String brindeksa) {
        return PrakticnanastavaDAO.getInstance().getSaktByIndeks(EntityManagerWrapper.getEntityManager(),brindeksa);
    }

}
