package Servlet;

import data.Lokacija;
import data.Prakticnanastava;
import exceptions.KPAException;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
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

// 
public class lokacijeSERVLET extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String action = request.getParameter("action");
        TipAkcije tipAkcije = TipAkcije.getForAction(action);

        switch (tipAkcije) {
            case ADD: {
                Lokacija lokacija = createLokacijaADD(request, TipAkcije.ADD);
                try {
                    LokacijaService.getInstance().add(lokacija);
                } catch (KPAException ex) {
                    request.setAttribute("errors", "Грешка! Тренитно није могуће додати локацију!");
                }
                break;
            }
            case EDIT: {
                Lokacija spi = createLokacijaADD(request, TipAkcije.EDIT);
                try {
                    LokacijaService.getInstance().edit(spi);
                } catch (KPAException ex) {
                    request.setAttribute("errors", "Грешка! Тренитно није могуће изменити локацију!");
                }
                break;
            }
            case DELETE: {
                Lokacija spi = createLokacijaADD(request, TipAkcije.DELETE);
                try {
                    LokacijaService.getInstance().delete(spi);
                } catch (KPAException ex) {
                    request.setAttribute("errors", "Грешка! Тренитно није могуће обрисати локацију!");
                }
                break;
            }
        }
        if (tipAkcije != TipAkcije.LIST) {
            request.setAttribute("message", "Успешно извршена акција.");
        }
        System.out.println("***************************** OBAVESTENJE SERVLET ************************************************");
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

    private Lokacija createLokacijaADD(HttpServletRequest request, TipAkcije tipAkcije) throws UnsupportedEncodingException {
        if (request.getCharacterEncoding() == null) {
            request.setCharacterEncoding("UTF-8");
        }
        Integer pkClan = null;
        String naziv = new String(request.getParameter("naziv").getBytes("ISO-8859-1"), "UTF-8");
        String pUprava = new String(request.getParameter("pUprava").getBytes("ISO-8859-1"), "UTF-8");
        String pStanica = new String(request.getParameter("pStanica").getBytes("ISO-8859-1"), "UTF-8");

        Lokacija o = new Lokacija(pkClan);
        o.setNaziv(naziv);
        o.setPolicijskaStanica(pStanica);
        o.setPolicijskaUprava(pUprava);
        return o;
    }

}
