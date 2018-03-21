package Servlet;

import data.Lokacija;
import data.Ostaloprakticna;
import data.Prakticnanastava;
import data.Student;
import exceptions.KPAException;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import service.LokacijaService;
import service.PrakticnanastavaService;
import service.StudentService;

// 
public class PrakticnaSERVLET extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String action = request.getParameter("action");
        TipAkcije tipAkcije = TipAkcije.getForAction(action);
        switch (tipAkcije) {
            case LIST: {
                break;
            }
            case ADD: {
                Prakticnanastava prakticnanastava = createPrakticnanastavaADD(request, TipAkcije.ADD);
                if (prakticnanastava != null) {
                    try {
                        PrakticnanastavaService.getInstance().add(prakticnanastava);
                        request.setAttribute("message", "Успешно сте додали практичну наставу.");
                    } catch (KPAException e) {
                        request.setAttribute("errors", "Грешка! Тренутно није могуће додати практичну наставу!");
                    }
                } else {
                    request.setAttribute("errors", "Корисник са тим бројем индекса не постоји у бази или се полицијска станица и управа не поклапају!");
                }
                break;
            }
            case EDIT: {
                Prakticnanastava prakticnanastava = createPrakticnanastavaEDIT(request, TipAkcije.EDIT);
                if (prakticnanastava != null) {
                    try {
                        PrakticnanastavaService.getInstance().edit(prakticnanastava);
                        request.setAttribute("message", "Успешно сте изменили корисника.");
                    } catch (KPAException e) {
                        request.setAttribute("errors", "Грешка! Тренутно није могуће изменити корисника!");
                    }
                }else{
                    request.setAttribute("errors", "Полицијска станица и полицијска управа се не поклапају!");
                }
                break;
            }
            case DELETE: {
                Prakticnanastava prakticnanastava = createPrakticnanastavaDELETE(request, TipAkcije.DELETE);
                try {
                    PrakticnanastavaService.getInstance().delete(prakticnanastava);
                    request.setAttribute("message", "Успешно сте обрисали корисника.");
                } catch (KPAException e) {
                    request.setAttribute("errors", "Грешка! Тренутно није могуће орисати корисника!");
                }
                break;
            }
        }
        System.out.println("***************************** PRAKTICNA SERVLET ************************************************");
        List<Prakticnanastava> osp = PrakticnanastavaService.getInstance().getAll();
        request.setAttribute("prakticnanastava", osp);
        List<Lokacija> lok = LokacijaService.getInstance().getAll();
        List<String> pu = new ArrayList<>();
        List<String> ps = new ArrayList<>();
        for (Lokacija l : lok) {
            pu.add(l.getPolicijskaUprava());
            ps.add(l.getPolicijskaStanica());
        }
        request.setAttribute("pu", pu);
        request.setAttribute("ps", ps);
        request.getRequestDispatcher("prakticna.jsp").forward(request, response);
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

    private Prakticnanastava createPrakticnanastavaADD(HttpServletRequest request, TipAkcije tipAkcije) throws UnsupportedEncodingException {
        if (request.getCharacterEncoding() == null) {
            request.setCharacterEncoding("UTF-8");
        }
        Integer pkClan = null;
        String brIndeksa = new String(request.getParameter("brIndeksa").getBytes("ISO-8859-1"), "UTF-8");
        String pUprava = new String(request.getParameter("pUprava").getBytes("ISO-8859-1"), "UTF-8");
        String pStanica = new String(request.getParameter("pStanica").getBytes("ISO-8859-1"), "UTF-8");
        String Datum = new String(request.getParameter("Datum").getBytes("ISO-8859-1"), "UTF-8");
        Ostaloprakticna op = new Ostaloprakticna();
        Lokacija l = LokacijaService.getInstance().getLokacijaByParm(pUprava, pStanica);
        Student stud = null;
        try {
            stud = StudentService.getInstance().getStudentByBrInd(brIndeksa);
        } catch (Exception e) {
            stud = null;
        }
        Prakticnanastava s = null;
        if (stud != null) {
            s = new Prakticnanastava();
            s.setDatum(Datum);
            s.setFKLokacija(l);
            s.setFKBrIndeks(stud);
            s.setFKOstaloPrakticna(op);
        }
        return s;
    }

    private Prakticnanastava createPrakticnanastavaDELETE(HttpServletRequest request, TipAkcije tipAkcije) throws UnsupportedEncodingException {
        request.setCharacterEncoding("UTF-8");
        Integer pkClan = null;
        if (tipAkcije == TipAkcije.EDIT || tipAkcije == TipAkcije.DELETE) {
            String[] pkClanParam = request.getParameterValues("IDPrakticnaNastava");
            if (pkClanParam != null) {
                pkClan = Integer.parseInt(pkClanParam[0]);
            }
        }

        Prakticnanastava s = PrakticnanastavaService.getInstance().getPrakticnanastavaById(pkClan);

        return s;
    }

    private Prakticnanastava createPrakticnanastavaEDIT(HttpServletRequest request, TipAkcije tipAkcije) throws UnsupportedEncodingException {
        request.setCharacterEncoding("UTF-8");
        Integer pkClan = null;
        if (tipAkcije == TipAkcije.EDIT || tipAkcije == TipAkcije.DELETE) {
            String[] pkClanParam = request.getParameterValues("IDPrakticnaNastava");
            if (pkClanParam != null) {
                pkClan = Integer.parseInt(pkClanParam[0]);
            }
        }
        int ostaloID = Integer.parseInt(request.getParameter("IDOStalo"));
        String brIndeksa = new String(request.getParameter("brIndeksa").getBytes("ISO-8859-1"), "UTF-8");
        String pUprava = new String(request.getParameter("pUprava").getBytes("ISO-8859-1"), "UTF-8");
        String pStanica = new String(request.getParameter("pStanica").getBytes("ISO-8859-1"), "UTF-8");
        String Datum = new String(request.getParameter("Datum").getBytes("ISO-8859-1"), "UTF-8");

        String izostanak = new String(request.getParameter("izostanak").getBytes("ISO-8859-1"), "UTF-8");
        String prekoredno = new String(request.getParameter("prekoredno").getBytes("ISO-8859-1"), "UTF-8");
        String opravdanje = new String(request.getParameter("opravdanje").getBytes("ISO-8859-1"), "UTF-8");
        String na = request.getParameter("napomena");
        String napomena = null;
        if (na != null) {
            napomena = new String(na.getBytes("ISO-8859-1"), "UTF-8");
        }
        Ostaloprakticna op = new Ostaloprakticna(ostaloID);
        op.setIzostanak(vrati(izostanak));
        op.setNapomena(napomena);
        op.setOpravdanje(vrati(opravdanje));
        op.setPrekorednoDezurstvo(vrati(prekoredno));
        Lokacija l = null;
        Student stud = null;
        try {
            l = LokacijaService.getInstance().getLokacijaByParm(pUprava, pStanica);
            stud = StudentService.getInstance().getStudentByBrInd(brIndeksa);
        } catch (Exception e) {
            l = null;
            stud = null;
        }
        Prakticnanastava s = null;
        if (stud != null) {
            s = new Prakticnanastava(pkClan);
            s.setDatum(Datum);
            s.setFKLokacija(l);
            s.setFKBrIndeks(stud);
            s.setFKOstaloPrakticna(op);
        }
        return s;
    }

    private boolean vrati(String s) {
        return s.equalsIgnoreCase("да");
    }
}
