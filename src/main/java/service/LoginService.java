package service;

import java.util.Base64;
import javax.persistence.EntityManager;
import exceptions.KPAException;
import dao.LoginDAO;
import dao.EntityManagerWrapper;
import data.Login;

public class LoginService {

    private static final LoginService instance = new LoginService();

    private LoginService() {
    }

    public static LoginService getInstance() {
        return instance;
    }

    public Login resetPassword(Login login, String nova) throws KPAException{
        EntityManager em = EntityManagerWrapper.getEntityManager();
        try {
            em.getTransaction().begin();
            login.setLozinka(nova);
            Login updatedSpi = LoginDAO.getInstance().resetPassword(em, login);
            em.getTransaction().commit();

            return updatedSpi;
        } finally {
            em.close();
        }
    }
    
    public Login isAuthenticated(Login login) {

        Login authenticationSuccessful = null;
        EntityManager em = EntityManagerWrapper.getEntityManager();
        try {
            authenticationSuccessful = LoginDAO.getInstance().checkExistence(em, login);
        } catch (KPAException ex) {
        } finally {
            em.close();
        }

        return authenticationSuccessful;
    }

    public Login isAuthenticated(String authString) {
        final String nalogSifraFromAuth = nalogSifraFromAuthSring(authString);
        final Login login = createLogin(nalogSifraFromAuth);
        return isAuthenticated(login);
    }

    private String nalogSifraFromAuthSring(String authString) {
        // Stiglo 'Basic ZGpvbGU6ZGpvbGU=' ==>djole:djole
        String[] authParts = authString.split("\\s+");
        String authInfo = authParts[1];
        // Dekodiranje iz Base64 formata u UTF-8 format
        byte[] byteNalogSifra = Base64.getDecoder().decode(authInfo);
        String nalogSifra = new String(byteNalogSifra);
        System.out.println("NALOG SIFRA :"+nalogSifra);
        return nalogSifra;
    }

    private Login createLogin(String nalogSifra) {
        String[] userPass = nalogSifra.split(":");
        final String nalog = userPass[0];
        final String sifra = userPass[1];
        Login l = new Login();
        l.setKorIme(nalog);
        l.setLozinka(sifra);
        return l;
    }

}
