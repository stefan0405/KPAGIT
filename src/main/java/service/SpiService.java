package service;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import exceptions.KPAException;
import dao.SpiDAO;
import dao.EntityManagerWrapper;
import dao.OstalospiDAO;
import data.Spi;

public class SpiService {

    private static final SpiService instance = new SpiService();

    private SpiService() {
    }

    public static SpiService getInstance() {
        return instance;
    }

    public List<Spi> getAll() {
        return SpiDAO.getInstance().findAll(EntityManagerWrapper.getEntityManager());
    }

    public Spi getSpiById(int id) {
        return SpiDAO.getInstance().findSpiById(EntityManagerWrapper.getEntityManager(), id);
    }

    public List<Spi> getSpiByIndeks(String indeks) {
        return SpiDAO.getInstance().findSpiByIndeks(EntityManagerWrapper.getEntityManager(), indeks);
    }

    public Spi add(Spi spi) throws KPAException {
        //validirati ulazne parametre, parametre koje je korisnik uneo u formi
        validate(spi);

        EntityManager em = EntityManagerWrapper.getEntityManager();
        try {
            em.getTransaction().begin();
            final Spi newSpi = SpiDAO.getInstance().add(em, spi);
            em.getTransaction().commit();

            return newSpi;
        } finally {
            em.close();
        }
    }

    public Spi edit(Spi spi) throws KPAException {
        //validirati ulazne parametre, parametre koje je korisnik uneo u formi
        validate(spi);

        EntityManager em = EntityManagerWrapper.getEntityManager();
        try {
            em.getTransaction().begin();
            Spi updatedSpi = SpiDAO.getInstance().edit(em, spi);
            em.getTransaction().commit();

            return updatedSpi;
        } finally {
            em.close();
        }
    }

    public void delete(Spi spi) throws KPAException {
        //validirati ulazne parametre, parametre koje je korisnik uneo u formi
        if (spi.getIDSpi() == null) {
            throw new KPAException("Nepoznat ID korisnika.");
        }

        EntityManager em = EntityManagerWrapper.getEntityManager();
        try {
            em.getTransaction().begin();
            SpiDAO.getInstance().delete(em, spi);
            em.getTransaction().commit();
        } finally {
            em.close();
        }
    }

    private void validate(Spi spi) throws KPAException {
        List<String> errors = new ArrayList<>();
        if (spi.getDuznost() == null || spi.getDuznost().trim().isEmpty()) {
            errors.add("Parametar duznost je obavezan.");
        }
        if (spi.getRadnoVreme() == null || spi.getRadnoVreme().trim().isEmpty()) {
            errors.add("Parametar radno vreme je obavezan.");
        }
        if (spi.getDatum() == null || spi.getDatum().trim().isEmpty()) {
            errors.add("Parametar datum je obavezan.");
        }
        if (spi.getFKBrIndeks() == null) {
            errors.add("Parametar broj indeksa je obavezan.");
        }
        if (!errors.isEmpty()) {
            throw new KPAException(errors);
        }
    }

}
