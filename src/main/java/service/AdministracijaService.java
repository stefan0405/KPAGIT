package service;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import exceptions.KPAException;
import dao.AdministracijaDAO;
import dao.EntityManagerWrapper;
import data.Administracija;

public class AdministracijaService {

    private static final AdministracijaService instance = new AdministracijaService();

    private AdministracijaService() {
    }

    public static AdministracijaService getInstance() {
        return instance;
    }

    public List<Administracija> getAll() {
        return AdministracijaDAO.getInstance().findAll(EntityManagerWrapper.getEntityManager());
    }

    public Administracija getAdministracijaById(int id) {
        return AdministracijaDAO.getInstance().findAdministracijaById(EntityManagerWrapper.getEntityManager(), id);
    }

    public Administracija add(Administracija administracija) throws KPAException {
        //validirati ulazne parametre, parametre koje je korisnik uneo u formi
        validate(administracija);

        EntityManager em = EntityManagerWrapper.getEntityManager();
        try {
            em.getTransaction().begin();
            final Administracija newAdministracija = AdministracijaDAO.getInstance().add(em, administracija);
            em.getTransaction().commit();

            return newAdministracija;
        } finally {
            em.close();
        }
    }

    public Administracija edit(Administracija administracija) throws KPAException {
        //validirati ulazne parametre, parametre koje je korisnik uneo u formi
        validate(administracija);

        EntityManager em = EntityManagerWrapper.getEntityManager();
        try {
            em.getTransaction().begin();
            final Administracija updatedAdministracija = AdministracijaDAO.getInstance().edit(em, administracija);
            em.getTransaction().commit();

            return updatedAdministracija;
        } finally {
            em.close();
        }
    }

    public void delete(Administracija administracija) throws KPAException {
        //validirati ulazne parametre, parametre koje je korisnik uneo u formi
        if (administracija.getIDAdmin() == null) {
            throw new KPAException("Nepoznat ID korisnika.");
        }

        EntityManager em = EntityManagerWrapper.getEntityManager();
        try {
            em.getTransaction().begin();
            AdministracijaDAO.getInstance().delete(em, administracija);
            em.getTransaction().commit();
        } finally {
            em.close();
        }
    }

    private void validate(Administracija administracija) throws KPAException {
        List<String> errors = new ArrayList<>();
        if (administracija.getIme() == null || administracija.getIme().trim().isEmpty()) {
            errors.add("Parametar ime je obavezan.");
        }
        if (administracija.getPrezime() == null || administracija.getPrezime().trim().isEmpty()) {
            errors.add("Parametar prezime je obavezan.");
        }
        if (administracija.getFKUloga() == null) {
            errors.add("Parametar uloga je obavezan.");
        }
        if (administracija.getFKLogin() == null) {
            errors.add("Parametar login je obavezan.");
        }
        if (!errors.isEmpty()) {
            throw new KPAException(errors);
        }
    }

}
