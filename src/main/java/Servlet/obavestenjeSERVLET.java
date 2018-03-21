package Servlet;

import data.Administracija;
import data.Obavestenje;
import exceptions.KPAException;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import service.ObavestenjeService;

// 
public class obavestenjeSERVLET extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String action = request.getParameter("action");
        TipAkcije tipAkcije = TipAkcije.getForAction(action);

        switch (tipAkcije) {
            case ADD: {
                Obavestenje obavestenje = createObavestenjeADD(request, TipAkcije.ADD);
                try {
                    ObavestenjeService.getInstance().add(obavestenje);
                    request.setAttribute("message", "Успешно сте додали обавештење.");
                } catch (KPAException ex) {
                    request.setAttribute("errors", "Грешка! Тренутно није могуће додати обавештење!");
                }
                break;
            }
            case EDIT: {
                Obavestenje spi = createObavestenjeADD(request, TipAkcije.EDIT);
                try {
                    ObavestenjeService.getInstance().edit(spi);
                    request.setAttribute("message", "Успешно сте изменили обавештење.");
                } catch (KPAException ex) {
                    request.setAttribute("errors", "Грешка! Тренутно није могуће изменити обавештење!");
                }
                break;
            }
            case DELETE: {
                Obavestenje spi = createObavestenjeADD(request, TipAkcije.DELETE);
                try {
                    ObavestenjeService.getInstance().delete(spi);
                    request.setAttribute("message", "Успешно сте обрисали обавештење.");
                } catch (KPAException ex) {
                    request.setAttribute("errors", "Грешка! Тренутно није могуће обрисати обавештење!");
                }
                break;
            }
        }
        System.out.println("***************************** OBAVESTENJE SERVLET ************************************************");
        List<Obavestenje> o = ObavestenjeService.getInstance().getAll();
        request.setAttribute("obavestenje", o);
        request.getRequestDispatcher("obavestenje.jsp").forward(request, response);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    private Obavestenje createObavestenjeADD(HttpServletRequest request, TipAkcije tipAkcije) throws UnsupportedEncodingException {
        if (request.getCharacterEncoding() == null) {
            request.setCharacterEncoding("UTF-8");
        }
        Integer pkClan = null;
        String Datum = null;
        if (tipAkcije == TipAkcije.EDIT || tipAkcije == TipAkcije.DELETE) {
            String[] pkClanParam = request.getParameterValues("IDObavestenje");
            if (pkClanParam != null) {
                pkClan = Integer.parseInt(pkClanParam[0]);
            }
        } 
        if(tipAkcije == TipAkcije.ADD){
            SimpleDateFormat ff = new SimpleDateFormat("dd/mm/yyyy");
            java.util.Date dat = new java.util.Date();
            Datum = ff.format(dat);
        }
        else {
            Datum = new String(request.getParameter("datum").getBytes("ISO-8859-1"), "UTF-8");
        }

        String naslov = new String(request.getParameter("naslov").getBytes("ISO-8859-1"), "UTF-8");
        String tekst = new String(request.getParameter("tekst").getBytes("ISO-8859-1"), "UTF-8");

        Administracija l = (Administracija) request.getSession().getAttribute("administrator");
        Obavestenje o = new Obavestenje(pkClan);
        o.setNaziv(naslov);
        o.setKomentar(tekst);
        o.setDatum(Datum);
        o.setFKAdmin(l);
        return o;
    }

}
