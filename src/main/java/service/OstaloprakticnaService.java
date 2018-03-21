package service;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import exceptions.KPAException;
import dao.OstaloprakticnaDAO;
import dao.EntityManagerWrapper;
import data.Ostaloprakticna;

public class OstaloprakticnaService {

    private static final OstaloprakticnaService instance = new OstaloprakticnaService();

    private OstaloprakticnaService() {
    }

    public static OstaloprakticnaService getInstance() {
        return instance;
    }

    public List<Ostaloprakticna> getAll() {
        return OstaloprakticnaDAO.getInstance().findAll(EntityManagerWrapper.getEntityManager());
    }

    public Ostaloprakticna getOstaloprakticnaById(int id) {
        return OstaloprakticnaDAO.getInstance().findOstaloprakticnaById(EntityManagerWrapper.getEntityManager(), id);
    }

    public Ostaloprakticna add(Ostaloprakticna ostaloprakticna) throws KPAException {
        //validirati ulazne parametre, parametre koje je korisnik uneo u formi
        validate(ostaloprakticna);

        EntityManager em = EntityManagerWrapper.getEntityManager();
        try {
            em.getTransaction().begin();
            final Ostaloprakticna newOstaloprakticna = OstaloprakticnaDAO.getInstance().add(em, ostaloprakticna);
            em.getTransaction().commit();

            return newOstaloprakticna;
        } finally {
            em.close();
        }
    }

    public Ostaloprakticna edit(Ostaloprakticna ostaloprakticna) throws KPAException {
        //validirati ulazne parametre, parametre koje je korisnik uneo u formi
        validate(ostaloprakticna);

        EntityManager em = EntityManagerWrapper.getEntityManager();
        try {
            em.getTransaction().begin();
            final Ostaloprakticna updatedOstaloprakticna = OstaloprakticnaDAO.getInstance().edit(em, ostaloprakticna);
            em.getTransaction().commit();

            return updatedOstaloprakticna;
        } finally {
            em.close();
        }
    }

    public void delete(Ostaloprakticna ostaloprakticna) throws KPAException {
        //validirati ulazne parametre, parametre koje je korisnik uneo u formi
        if (ostaloprakticna.getIDPrakticnaNastava() == null) {
            throw new KPAException("Nepoznat ID korisnika.");
        }

        EntityManager em = EntityManagerWrapper.getEntityManager();
        try {
            em.getTransaction().begin();
            OstaloprakticnaDAO.getInstance().delete(em, ostaloprakticna);
            em.getTransaction().commit();
        } finally {
            em.close();
        }
    }

    private void validate(Ostaloprakticna ostaloprakticna) throws KPAException {
        List<String> errors = new ArrayList<>();
        if (ostaloprakticna.getNapomena() == null || ostaloprakticna.getNapomena().trim().isEmpty()) {
            errors.add("Parametar napomena je obavezan.");
        }
        if (!errors.isEmpty()) {
            throw new KPAException(errors);
        }
    }

}
