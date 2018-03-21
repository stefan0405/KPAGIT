package Servlet;

import data.Sakt;
import data.Student;
import exceptions.KPAException;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import service.SaktService;
import service.StudentService;

// 
public class SkatSERVLET extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String action = request.getParameter("action");
        TipAkcije tipAkcije = TipAkcije.getForAction(action);

        switch (tipAkcije) {
            case LIST: {
                break;
            }
            case ADD: {
                Sakt prakticnanastava = createSaktADD(request, TipAkcije.ADD);
                if (prakticnanastava != null) {
                    try {
                        SaktService.getInstance().add(prakticnanastava);
                        request.setAttribute("message", "Успешно сте додали корисника.");
                    } catch (KPAException e) {
                        request.setAttribute("errors", "Грешка! Тренутно није могуће додати корисника!");
                    }
                } else {
                    request.setAttribute("errors", "Корисник са тим бројем индекса не постоји у бази!");
                }
                break;
            }
            case EDIT: {
                Sakt prakticnanastava = createSaktEDIT(request, TipAkcije.EDIT);
                try {
                    SaktService.getInstance().edit(prakticnanastava);
                    request.setAttribute("message", "Успешно сте изменили корисника.");
                } catch (KPAException e) {
                    request.setAttribute("errors", "Грешка! Тренутно није могуће изменити корисника!");
                }
                break;
            }
            case DELETE: {
                Sakt prakticnanastava = createSaktDELETE(request, TipAkcije.DELETE);
                try {
                    SaktService.getInstance().delete(prakticnanastava);
                    request.setAttribute("message", "Успешно сте обрисали корисника.");
                } catch (KPAException e) {
                    request.setAttribute("errors", "Грешка! Тренутно није могуће обрисати корисника!");
                }
                break;
            }
        }
        System.out.println("***************************** SKAT SERVLET ************************************************");
        List<Sakt> osp = SaktService.getInstance().getAll();
        request.setAttribute("skat", osp);
        request.getRequestDispatcher("skat.jsp").forward(request, response);
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

    private Sakt createSaktADD(HttpServletRequest request, TipAkcije tipAkcije) throws UnsupportedEncodingException {
        if (request.getCharacterEncoding() == null) {
            request.setCharacterEncoding("UTF-8");
        }
        Integer pkClan = null;

        String ciscenjeOruzja = new String(request.getParameter("cOruzije").getBytes("ISO-8859-1"), "UTF-8");
        String Datum = new String(request.getParameter("Datum").getBytes("ISO-8859-1"), "UTF-8");
        String strojevaObuka = new String(request.getParameter("sObuka").getBytes("ISO-8859-1"), "UTF-8");
        String rukovanjeSluzbenimOruzjem = new String(request.getParameter("rso").getBytes("ISO-8859-1"), "UTF-8");
        String sektorZaVanredneSituacije = new String(request.getParameter("szvs").getBytes("ISO-8859-1"), "UTF-8");
        String terenskaObukaGoc = new String(request.getParameter("goc").getBytes("ISO-8859-1"), "UTF-8");
        String na = request.getParameter("napomena");
        String napomena = null;
        Student stud = null;
        if (na != null) {
            napomena = new String(na.getBytes("ISO-8859-1"), "UTF-8");
        }
        String brIndeksa = new String(request.getParameter("brIndeksa").getBytes("ISO-8859-1"), "UTF-8");
        try {
            stud = StudentService.getInstance().getStudentByBrInd(brIndeksa);
        } catch (Exception e) {
            stud = null;
        }
        Sakt s = null;
        if (stud != null) {
            s = new Sakt();
            s.setDatum(Datum);
            s.setCiscenjeOruzja(vrati(ciscenjeOruzja));
            s.setDatum(Datum);
            s.setFKBrindeksa(stud);
            s.setNapomena(napomena);
            s.setRukovanjeSluzbenimOruzjem(vrati(rukovanjeSluzbenimOruzjem));
            s.setSektorZaVanredneSituacije(vrati(sektorZaVanredneSituacije));
            s.setStrojevaObuka(vrati(strojevaObuka));
            s.setTerenskaObukaGoc(terenskaObukaGoc);
        }
        return s;
    }

    private Sakt createSaktDELETE(HttpServletRequest request, TipAkcije tipAkcije) throws UnsupportedEncodingException {
        request.setCharacterEncoding("UTF-8");
        Integer pkClan = null;
        if (tipAkcije == TipAkcije.EDIT || tipAkcije == TipAkcije.DELETE) {
            String[] pkClanParam = request.getParameterValues("IDSkat");
            if (pkClanParam != null) {
                pkClan = Integer.parseInt(pkClanParam[0]);
            }
        }

        Sakt s = SaktService.getInstance().getSaktById(pkClan);

        return s;
    }

    private Sakt createSaktEDIT(HttpServletRequest request, TipAkcije tipAkcije) throws UnsupportedEncodingException {
        request.setCharacterEncoding("UTF-8");
        Integer pkClan = null;
        if (tipAkcije == TipAkcije.EDIT || tipAkcije == TipAkcije.DELETE) {
            String[] pkClanParam = request.getParameterValues("IDSkat");
            if (pkClanParam != null) {
                pkClan = Integer.parseInt(pkClanParam[0]);
            }
        }
        String ciscenjeOruzja = new String(request.getParameter("sOruzije").getBytes("ISO-8859-1"), "UTF-8");
        String Datum = new String(request.getParameter("Datum").getBytes("ISO-8859-1"), "UTF-8");
        String strojevaObuka = new String(request.getParameter("sObuka").getBytes("ISO-8859-1"), "UTF-8");
        String rukovanjeSluzbenimOruzjem = new String(request.getParameter("rso").getBytes("ISO-8859-1"), "UTF-8");
        String sektorZaVanredneSituacije = new String(request.getParameter("szvs").getBytes("ISO-8859-1"), "UTF-8");
        String terenskaObukaGoc = new String(request.getParameter("goc").getBytes("ISO-8859-1"), "UTF-8");
        String na = request.getParameter("napomena");
        String napomena = null;
        if (na != null) {
            napomena = new String(na.getBytes("ISO-8859-1"), "UTF-8");
        }
        String brIndeksa = new String(request.getParameter("brIndeksa").getBytes("ISO-8859-1"), "UTF-8");

        Student stud = StudentService.getInstance().getStudentByBrInd(brIndeksa);
        Sakt s = null;
        if (stud != null) {
            s = new Sakt(pkClan);
            s.setDatum(Datum);
            s.setCiscenjeOruzja(vrati(ciscenjeOruzja));
            s.setDatum(Datum);
            s.setFKBrindeksa(stud);
            s.setNapomena(napomena);
            s.setRukovanjeSluzbenimOruzjem(vrati(rukovanjeSluzbenimOruzjem));
            s.setSektorZaVanredneSituacije(vrati(sektorZaVanredneSituacije));
            s.setStrojevaObuka(vrati(strojevaObuka));
            s.setTerenskaObukaGoc(terenskaObukaGoc);
        }
        return s;
    }

    private boolean vrati(String s) {
        return s.equalsIgnoreCase("да");
    }
}
