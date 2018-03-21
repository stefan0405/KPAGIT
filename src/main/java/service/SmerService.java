package service;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import exceptions.KPAException;
import dao.SmerDAO;
import dao.EntityManagerWrapper;
import data.Smer;

public class SmerService {

    private static final SmerService instance = new SmerService();

    private SmerService() {
    }

    public static SmerService getInstance() {
        return instance;
    }

    public List<Smer> getAll() {
        return SmerDAO.getInstance().findAll(EntityManagerWrapper.getEntityManager());
    }

    public Smer getSmerById(int id) {
        return SmerDAO.getInstance().findSmerById(EntityManagerWrapper.getEntityManager(), id);
    }

    public Smer add(Smer smer) throws KPAException {
        //validirati ulazne parametre, parametre koje je korisnik uneo u formi
        validate(smer);

        EntityManager em = EntityManagerWrapper.getEntityManager();
        try {
            em.getTransaction().begin();
            final Smer newSmer = SmerDAO.getInstance().add(em, smer);
            em.getTransaction().commit();

            return newSmer;
        } finally {
            em.close();
        }
    }

    public Smer edit(Smer smer) throws KPAException {
        //validirati ulazne parametre, parametre koje je korisnik uneo u formi
        validate(smer);

        EntityManager em = EntityManagerWrapper.getEntityManager();
        try {
            em.getTransaction().begin();
            final Smer updatedSmer = SmerDAO.getInstance().edit(em, smer);
            em.getTransaction().commit();

            return updatedSmer;
        } finally {
            em.close();
        }
    }

    public void delete(Smer smer) throws KPAException {
        //validirati ulazne parametre, parametre koje je korisnik uneo u formi
        if (smer.getIDSmer() == null) {
            throw new KPAException("Nepoznat ID korisnika.");
        }

        EntityManager em = EntityManagerWrapper.getEntityManager();
        try {
            em.getTransaction().begin();
            SmerDAO.getInstance().delete(em, smer);
            em.getTransaction().commit();
        } finally {
            em.close();
        }
    }

    private void validate(Smer smer) throws KPAException {
        List<String> errors = new ArrayList<>();
        if (smer.getNaziv() == null || smer.getNaziv().trim().isEmpty()) {
            errors.add("Parametar naziv je obavezan.");
        }
        if (!errors.isEmpty()) {
            throw new KPAException(errors);
        }
    }

}
