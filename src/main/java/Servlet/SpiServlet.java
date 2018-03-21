package Servlet;

import data.Ostalospi;
import data.Spi;
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
import service.OstalospiService;
import service.SpiService;
import service.StudentService;

// 
public class SpiServlet extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String action = request.getParameter("action");
        TipAkcije tipAkcije = TipAkcije.getForAction(action);

        switch (tipAkcije) {
            case LIST: {
                break;
            }
            case ADD: {
                Spi spi = createSpiADD(request, TipAkcije.ADD);
                if (spi == null) {
                    request.setAttribute("errors", "Корисник са тим бројем индекса не постоји у бази!");
                    break;
                } else {
                    try {
                        SpiService.getInstance().add(spi);
                        request.setAttribute("message", "Успешно сте додали корисника.");
                    } catch (KPAException ex) {
                        request.setAttribute("errors", "Грешка! Тренитно није могуће додати корисника!");
                    }
                }
                break;
            }
            case EDIT: {
                Spi spi = createSpiEDIT(request, TipAkcije.EDIT);
                try {
                    SpiService.getInstance().edit(spi);
                    request.setAttribute("message", "Успешно сте изменили корисника.");
                } catch (KPAException e) {
                    request.setAttribute("errors", "Грешка! Тренутно није могуће изменити корисника!");
                }
                break;
            }
            case DELETE: {
                Spi spi = createSpiDELETE(request, TipAkcije.DELETE);
                try {
                    Ostalospi osp = OstalospiService.getInstance().getOstalospiById(spi.getFKOStalo().getIDOstalo());
                    SpiService.getInstance().delete(spi);
                    OstalospiService.getInstance().delete(osp);
                    request.setAttribute("message", "Успешно обрисали корисника.");
                } catch (KPAException e) {
                    request.setAttribute("errors", "Грешка! Тренутно није могуће обрисати корисника!");
                }
                break;
            }
        }
        System.out.println("***************************** SPI SERVLET ************************************************");
        List<Spi> osp = SpiService.getInstance().getAll();
        request.setAttribute("spi", osp);
        request.getRequestDispatcher("spi.jsp").forward(request, response);
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

    private Spi createSpiADD(HttpServletRequest request, TipAkcije tipAkcije) throws UnsupportedEncodingException {
        if (request.getCharacterEncoding() == null) {
            request.setCharacterEncoding("UTF-8");
        }
        Integer pkClan = null;
        Spi s = null;
        String du = request.getParameter("duznost");
        String duznost = null;
        if (du != null) {
            duznost = new String(du.getBytes("ISO-8859-1"), "UTF-8");
        }
        String rv = request.getParameter("radnoVreme");
        String radnoVreme = null;
        if (rv != null) {
            radnoVreme = new String(rv.getBytes("ISO-8859-1"), "UTF-8");
        }
        String da = request.getParameter("Datum");
        String Datum = null;
        if (da != null) {
            Datum = new String(da.getBytes("ISO-8859-1"), "UTF-8");
        }
        String br = request.getParameter("brIndeksa");
        String brIndeksa = null;
        if (br != null) {
            brIndeksa = new String(br.getBytes("ISO-8859-1"), "UTF-8");
        }
        Ostalospi sob = new Ostalospi();
        Student stud = null;
        if (brIndeksa != null) {
            try {
                stud = StudentService.getInstance().getStudentByBrInd(brIndeksa);
            } catch (Exception ex) {
                s = null;
            }
        }
        if (stud != null) {
            s = new Spi();
            s.setDatum(Datum);
            s.setRadnoVreme(radnoVreme);
            s.setDuznost(duznost);
            s.setFKBrIndeks(stud);
            s.setFKOStalo(sob);
        }
        if (duznost == null || radnoVreme == null || Datum == null || brIndeksa == null) {
            s = null;
        }
        return s;
    }

    private Spi createSpiDELETE(HttpServletRequest request, TipAkcije tipAkcije) throws UnsupportedEncodingException {
        if (request.getCharacterEncoding() == null) {
            request.setCharacterEncoding("UTF-8");
        }
        Integer pkClan = null;
        if (tipAkcije == TipAkcije.EDIT || tipAkcije == TipAkcije.DELETE) {
            String[] pkClanParam = request.getParameterValues("IDSpi");
            if (pkClanParam != null) {
                pkClan = Integer.parseInt(pkClanParam[0]);
            }
        }

        Spi s = SpiService.getInstance().getSpiById(pkClan);

        return s;
    }

    private Spi createSpiEDIT(HttpServletRequest request, TipAkcije tipAkcije) throws UnsupportedEncodingException {
        if (request.getCharacterEncoding() == null) {
            request.setCharacterEncoding("UTF-8");
        }
        Integer pkClan = null;
        if (tipAkcije == TipAkcije.EDIT || tipAkcije == TipAkcije.DELETE) {
            String[] pkClanParam = request.getParameterValues("IDSpi");
            if (pkClanParam != null) {
                pkClan = Integer.parseInt(pkClanParam[0]);
            }
        }
        String duznost = new String(request.getParameter("duznost").getBytes("ISO-8859-1"), "UTF-8");
        String radnoVreme = new String(request.getParameter("radnoVreme").getBytes("ISO-8859-1"), "UTF-8");
        String Datum = new String(request.getParameter("Datum").getBytes("ISO-8859-1"), "UTF-8");
        String brIndeksa = new String(request.getParameter("brIndeksa").getBytes("ISO-8859-1"), "UTF-8");

        String IDOstalo = new String(request.getParameter("IDOstalo").getBytes("ISO-8859-1"), "UTF-8");
        String javiose = new String(request.getParameter("javiose").getBytes("ISO-8859-1"), "UTF-8");
        String zakasnjenje = new String(request.getParameter("zakasnjenje").getBytes("ISO-8859-1"), "UTF-8");
        String izostanak = new String(request.getParameter("izostanak").getBytes("ISO-8859-1"), "UTF-8");
        String opravdanje = new String(request.getParameter("opravdanje").getBytes("ISO-8859-1"), "UTF-8");
        String napomena = new String(request.getParameter("napomena").getBytes("ISO-8859-1"), "UTF-8");

        Ostalospi sob = new Ostalospi();
        sob.setIDOstalo(Integer.parseInt(IDOstalo));
        sob.setIzostanak(vrati(izostanak));
        sob.setJaviose(vrati(javiose));
        sob.setNapomena(napomena);
        sob.setZakasnjenje(vrati(zakasnjenje));
        sob.setOpravdanje(vrati(opravdanje));
        Student stud = StudentService.getInstance().getStudentByBrInd(brIndeksa);
        Spi s = null;
        if (stud != null) {
            s = new Spi(pkClan);
            s.setDatum(Datum);
            s.setRadnoVreme(radnoVreme);
            s.setDuznost(duznost);
            s.setFKBrIndeks(stud);
            s.setFKOStalo(sob);
        }
        return s;
    }

    private boolean vrati(String s) {
        return s.equalsIgnoreCase("да");
    }
}
