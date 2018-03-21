package dao;

import data.Login;
import exceptions.KPAException;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.Query;
import javax.xml.bind.DatatypeConverter;


public class LoginDAO {

    private static LoginDAO instance = new LoginDAO();

    private LoginDAO() {
    }

    public static LoginDAO getInstance() {
        return instance; 
    }

    public Login checkExistence(EntityManager em, Login login) throws KPAException {
        Login log = null;
        try {
            Query query = em.createQuery("SELECT b FROM Login b WHERE b.korIme=:korIme AND b.lozinka=:lozinka");
            query.setParameter("korIme", login.getKorIme());
            query.setParameter("lozinka", calculateHash(login));
            log = (Login) query.getSingleResult();
        } catch (NoResultException ex) {
        } catch (UnsupportedEncodingException | NoSuchAlgorithmException | NullPointerException ex) {
            throw new KPAException("Neuspešna provera korisničkog naloga.", ex);
        }
        return log;
    }
    
    public Login resetPassword(EntityManager em, Login login) throws KPAException {
        try {
            login.setLozinka(calculateHash(login));
        } catch (UnsupportedEncodingException | NoSuchAlgorithmException ex) {
            Logger.getLogger(LoginDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        em.merge(login);
        em.flush();
        return login;
    }
    
    private String calculateHash(Login login) throws UnsupportedEncodingException, NoSuchAlgorithmException {
        String nalogAndSifra = login.getKorIme() + login.getLozinka();

        MessageDigest messageDigest = MessageDigest.getInstance("SHA-256");
        byte hashBytes[] = messageDigest.digest(nalogAndSifra.getBytes("UTF-8"));

        return DatatypeConverter.printBase64Binary(hashBytes);
    }
}
