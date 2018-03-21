package service;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import exceptions.KPAException;
import dao.ObavestenjeDAO;
import dao.EntityManagerWrapper;
import data.Obavestenje;

public class ObavestenjeService {

    private static final ObavestenjeService instance = new ObavestenjeService();

    private ObavestenjeService() {
    }

    public static ObavestenjeService getInstance() {
        return instance;
    }

    public List<Obavestenje> getAll() {
        return ObavestenjeDAO.getInstance().findAll(EntityManagerWrapper.getEntityManager());
    }

    public Obavestenje getObavestenjeById(int id) {
        return ObavestenjeDAO.getInstance().findObavestenjeById(EntityManagerWrapper.getEntityManager(), id);
    }

    public Obavestenje add(Obavestenje obavestenje) throws KPAException {
        //validirati ulazne parametre, parametre koje je korisnik uneo u formi
        validate(obavestenje);

        EntityManager em = EntityManagerWrapper.getEntityManager();
        try {
            em.getTransaction().begin();
            final Obavestenje newObavestenje = ObavestenjeDAO.getInstance().add(em, obavestenje);
            em.getTransaction().commit();

            return newObavestenje;
        } finally {
            em.close();
        }
    }

    public Obavestenje edit(Obavestenje obavestenje) throws KPAException {
        //validirati ulazne parametre, parametre koje je korisnik uneo u formi
        validate(obavestenje);

        EntityManager em = EntityManagerWrapper.getEntityManager();
        try {
            em.getTransaction().begin();
            final Obavestenje updatedObavestenje = ObavestenjeDAO.getInstance().edit(em, obavestenje);
            em.getTransaction().commit();

            return updatedObavestenje;
        } finally {
            em.close();
        }
    }

    public void delete(Obavestenje obavestenje) throws KPAException {
        //validirati ulazne parametre, parametre koje je korisnik uneo u formi
        if (obavestenje.getIDObavestenje() == null) {
            throw new KPAException("Nepoznat ID korisnika.");
        }

        EntityManager em = EntityManagerWrapper.getEntityManager();
        try {
            em.getTransaction().begin();
            ObavestenjeDAO.getInstance().delete(em, obavestenje);
            em.getTransaction().commit();
        } finally {
            em.close();
        }
    }

    private void validate(Obavestenje obavestenje) throws KPAException {
        List<String> errors = new ArrayList<>();
        if (obavestenje.getNaziv() == null || obavestenje.getNaziv().trim().isEmpty()) {
            errors.add("Parametar naziv je obavezan.");
        }
        if (obavestenje.getDatum() == null || obavestenje.getDatum().trim().isEmpty()) {
            errors.add("Parametar datum je obavezan.");
        }
        if (obavestenje.getKomentar() == null || obavestenje.getKomentar().trim().isEmpty()) {
            errors.add("Parametar komentar je obavezan.");
        }
        if (obavestenje.getFKAdmin()==null) {
            errors.add("Parametar id admina je obavezan.");
        }
        if (!errors.isEmpty()) {
            throw new KPAException(errors);
        }
    }

}
