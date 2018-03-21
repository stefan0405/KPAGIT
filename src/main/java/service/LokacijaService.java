package service;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import exceptions.KPAException;
import dao.LokacijaDAO;
import dao.EntityManagerWrapper;
import data.Lokacija;

public class LokacijaService {

    private static final LokacijaService instance = new LokacijaService();

    private LokacijaService() {
    }

    public static LokacijaService getInstance() {
        return instance;
    }

    public List<Lokacija> getAll() {
        return LokacijaDAO.getInstance().findAll(EntityManagerWrapper.getEntityManager());
    }

    public Lokacija getLokacijaById(int id) {
        return LokacijaDAO.getInstance().findLokacijaById(EntityManagerWrapper.getEntityManager(), id);
    }
    
    public Lokacija getLokacijaByParm(String pUprava, String pStanica) {
        return LokacijaDAO.getInstance().findLokacijaByParm(EntityManagerWrapper.getEntityManager(), pUprava, pStanica);
    }

    public Lokacija add(Lokacija lokacija) throws KPAException {
        //validirati ulazne parametre, parametre koje je korisnik uneo u formi
        validate(lokacija);

        EntityManager em = EntityManagerWrapper.getEntityManager();
        try {
            em.getTransaction().begin();
            List<Lokacija> list = LokacijaService.getInstance().getAll();
            int br = list.get(list.size()-1).getIDLokacije();
            br +=1;
            lokacija.setIDLokacije(br);
            final Lokacija newLokacija = LokacijaDAO.getInstance().add(em, lokacija);
            em.getTransaction().commit();

            return newLokacija;
        } finally {
            em.close();
        }
    }

    public Lokacija edit(Lokacija lokacija) throws KPAException {
        //validirati ulazne parametre, parametre koje je korisnik uneo u formi
        validate(lokacija);

        EntityManager em = EntityManagerWrapper.getEntityManager();
        try {
            em.getTransaction().begin();
            final Lokacija updatedLokacija = LokacijaDAO.getInstance().edit(em, lokacija);
            em.getTransaction().commit();

            return updatedLokacija;
        } finally {
            em.close();
        }
    }

    public void delete(Lokacija lokacija) throws KPAException {
        //validirati ulazne parametre, parametre koje je korisnik uneo u formi
        if (lokacija.getIDLokacije() == null) {
            throw new KPAException("Nepoznat ID korisnika.");
        }

        EntityManager em = EntityManagerWrapper.getEntityManager();
        try {
            em.getTransaction().begin();
            LokacijaDAO.getInstance().delete(em, lokacija);
            em.getTransaction().commit();
        } finally {
            em.close();
        }
    }

    private void validate(Lokacija lokacija) throws KPAException {
        List<String> errors = new ArrayList<>();
        if (lokacija.getNaziv() == null || lokacija.getNaziv().trim().isEmpty()) {
            errors.add("Parametar naziv je obavezan.");
        }
        if (lokacija.getPolicijskaUprava() == null || lokacija.getPolicijskaUprava().trim().isEmpty()) {
            errors.add("Parametar policijska uprava je obavezan.");
        }
        if (lokacija.getPolicijskaStanica() == null || lokacija.getPolicijskaStanica().trim().isEmpty()) {
            errors.add("Parametar policijska stanica je obavezan.");
        }
        if (!errors.isEmpty()) {
            throw new KPAException(errors);
        }
    }

}
