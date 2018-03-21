package service;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import exceptions.KPAException;
import dao.SaktDAO;
import dao.EntityManagerWrapper;
import data.Sakt;

public class SaktService {

    private static final SaktService instance = new SaktService();

    private SaktService() {
    }

    public static SaktService getInstance() {
        return instance;
    }

    public List<Sakt> getAll() {
        return SaktDAO.getInstance().findAll(EntityManagerWrapper.getEntityManager());
    }

    public Sakt getSaktById(int id) {
        return SaktDAO.getInstance().findSaktById(EntityManagerWrapper.getEntityManager(), id);
    }

    public Sakt add(Sakt sakt) throws KPAException {
        //validirati ulazne parametre, parametre koje je korisnik uneo u formi
        validate(sakt);

        EntityManager em = EntityManagerWrapper.getEntityManager();
        try {
            em.getTransaction().begin();
            final Sakt newSakt = SaktDAO.getInstance().add(em, sakt);
            em.getTransaction().commit();

            return newSakt;
        } finally {
            em.close();
        }
    }

    public Sakt edit(Sakt sakt) throws KPAException {
        //validirati ulazne parametre, parametre koje je korisnik uneo u formi
        validate(sakt);

        EntityManager em = EntityManagerWrapper.getEntityManager();
        try {
            em.getTransaction().begin();
            final Sakt updatedSakt = SaktDAO.getInstance().edit(em, sakt);
            em.getTransaction().commit();

            return updatedSakt;
        } finally {
            em.close();
        }
    }

    public void delete(Sakt sakt) throws KPAException {
        //validirati ulazne parametre, parametre koje je korisnik uneo u formi
        if (sakt.getIDSkat() == null) {
            throw new KPAException("Nepoznat ID korisnika.");
        }

        EntityManager em = EntityManagerWrapper.getEntityManager();
        try {
            em.getTransaction().begin();
            SaktDAO.getInstance().delete(em, sakt);
            em.getTransaction().commit();
        } finally {
            em.close();
        }
    }

    private void validate(Sakt sakt) throws KPAException {
        List<String> errors = new ArrayList<>();
        if (sakt.getTerenskaObukaGoc() == null || sakt.getTerenskaObukaGoc().trim().isEmpty()) {
            errors.add("Parametar goc je obavezan.");
        }
        if (sakt.getFKBrindeksa() == null) {
            errors.add("Parametar broj indeksa je obavezan.");
        }
        if (sakt.getDatum() == null || sakt.getDatum().trim().isEmpty()) {
            errors.add("Parametar datum je obavezan.");
        }
        if (!errors.isEmpty()) {
            throw new KPAException(errors);
        }
    }

    public List<Sakt> getSaktByIndeks(String brindeksa) {
        return SaktDAO.getInstance().findSaktByIndeks(EntityManagerWrapper.getEntityManager(), brindeksa);
    }

}
