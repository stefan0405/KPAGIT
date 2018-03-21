package Servlet;

import data.Login;
import data.Lokacija;
import data.Prakticnanastava;
import data.Sakt;
import data.Spi;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import service.LokacijaService;
import service.PrakticnanastavaService;
import service.SaktService;
import service.SpiService;

public class StudentSERVLET extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Login s = (Login) request.getSession().getAttribute("korisnik");
        request.getSession().setAttribute("korisnik", s);
        System.out.println("INDEKS : " + s.getStudent().getBrindeksa());
        List<Spi> spi = SpiService.getInstance().getSpiByIndeks(s.getStudent().getBrindeksa());
        List<Spi> spiNovo = new ArrayList<>();
        for (Spi sp : spi) {
            String datum = sp.getDatum();
            datum = datum.replace("-", "/");
            datum = datum.replace(".", "/");
            java.text.SimpleDateFormat formatter = new java.text.SimpleDateFormat("dd/MM/yyyy");
            java.util.Date trenutno = new java.util.Date();
            java.util.Date pars = null;
            try {
                pars = formatter.parse(datum);
                double rez = trenutno.getTime() - pars.getTime();
                if (rez <= 0) {
                    spiNovo.add(sp);
                }
            } catch (ParseException ex) {
                Logger.getLogger(StudentSERVLET.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        List<Sakt> skat = SaktService.getInstance().getSaktByIndeks(s.getStudent().getBrindeksa());
        List<Sakt> skatNovo = new ArrayList<>();
        for (Sakt sa : skat) {
            String datum = sa.getDatum();
            datum = datum.replace("-", "/");
            datum = datum.replace(".", "/");
            java.text.SimpleDateFormat formatter = new java.text.SimpleDateFormat("dd/MM/yyyy");
            java.util.Date trenutno = new java.util.Date();
            java.util.Date pars = null;
            try {
                pars = formatter.parse(datum);
                double rez = trenutno.getTime() - pars.getTime();
                if (rez <= 0) {
                    skatNovo.add(sa);
                }
            } catch (ParseException ex) {
                Logger.getLogger(StudentSERVLET.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        List<Prakticnanastava> prakticna = PrakticnanastavaService.getInstance().getSaktByIndeks(s.getStudent().getBrindeksa());
        List<Prakticnanastava> prakNovo = new ArrayList<>();
        for (Prakticnanastava pra : prakticna) {
            String datum = pra.getDatum();
            datum = datum.replace("-", "/");
            datum = datum.replace(".", "/");
            java.text.SimpleDateFormat formatter = new java.text.SimpleDateFormat("dd/MM/yyyy");
            java.util.Date trenutno = new java.util.Date();
            java.util.Date pars = null;
            try {
                pars = formatter.parse(datum);
                double rez = trenutno.getTime() - pars.getTime();
                if (rez <= 0) {
                    prakNovo.add(pra);
                }
            } catch (ParseException ex) {
                Logger.getLogger(StudentSERVLET.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        List<Lokacija> lok = LokacijaService.getInstance().getAll();
        request.setAttribute("lokacija", lok);
        request.setAttribute("spi", spiNovo);
        request.setAttribute("skat", skatNovo);
        request.setAttribute("prakticna", prakNovo);
        request.getRequestDispatcher("student.jsp").forward(request, response);
    }

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }

    private Login createLogin(HttpServletRequest request) throws UnsupportedEncodingException {
        String nalog = new String(request.getParameter("korIme").getBytes("ISO-8859-1"), "UTF-8");
        String sifra = new String(request.getParameter("loz").getBytes("ISO-8859-1"), "UTF-8");
        Login l = new Login();
        l.setKorIme(nalog);
        l.setLozinka(sifra);
        return l;
    }

}
