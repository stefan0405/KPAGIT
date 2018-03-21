package dao;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import data.Spi;

public class SpiDAO {

    private static final SpiDAO instance = new SpiDAO();

    private SpiDAO() {
    }

    public static SpiDAO getInstance() {
        return instance;
    }

    public List<Spi> findAll(EntityManager em) {
        Query query = em.createQuery("SELECT c FROM Spi c");
        return query.getResultList();
    }

    public Spi findSpiById(EntityManager em, int id) {
        Query query = em.createQuery("SELECT c FROM Spi c WHERE c.iDSpi=:iDSpi");
        query.setParameter("iDSpi", id);
        return (Spi) query.getSingleResult();
    }

    public Spi add(EntityManager em, Spi spi) {
        em.persist(spi);
        em.flush();
        return spi;
    }

    public Spi edit(EntityManager em, Spi spi) {
        em.merge(spi);
        return spi;
    }

    public void delete(EntityManager em, Spi spi) {
        Spi managedSpi = em.merge(spi);
        em.remove(managedSpi);
    }

    public List<Spi> findSpiByIndeks(EntityManager em, String indeks) {
        Query query = em.createQuery("SELECT c FROM Spi c WHERE c.fKBrIndeks.brindeksa=:fKBrIndeks");
        query.setParameter("fKBrIndeks", indeks);
        return query.getResultList();
    }

}
