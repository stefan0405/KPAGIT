package Servlet;

import data.Login;
import exceptions.KPAException;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import service.LoginService;

public class resetSERVLET extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Login kor = createLogin(request);
        Login login = null;
        try{
            login = LoginService.getInstance().isAuthenticated(kor);
        }
        catch(Exception e){
            request.setAttribute("errors", new String[]{"Корисник са тим подацима не постоји у бази"});
        }
        
        if (login != null) {
            String[] novaLozinka = request.getParameterValues("novaLozinka");
            String nova = novaLozinka[0];
            Login da = null;
            try {
                da = LoginService.getInstance().resetPassword(login, nova);
            } catch (KPAException ex) {
                Logger.getLogger(resetSERVLET.class.getName()).log(Level.SEVERE, null, ex);
            }
            if (da != null) {
                request.setAttribute("message", "Успешно сте променили лозинку");
            } else {
                request.setAttribute("errors", new String[]{"Неуспешна промена лозинке."});
            }
        }
        else{
            request.setAttribute("errors", new String[]{"Корисник са тим подацима не постоји у бази"});
        }
        request.getRequestDispatcher("rese.jsp").forward(request, response);
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
        String brIndeksa = new String(request.getParameter("brIndeksa").getBytes("ISO-8859-1"), "UTF-8");
        String lozinka = new String(request.getParameter("lozinka").getBytes("ISO-8859-1"), "UTF-8");
        //Student st = StudentService.getInstance().getStudentByBrInd(brIndeksa[0]);
        Login l = new Login();
        l.setKorIme(brIndeksa);
        //l.setStudent(st);
        l.setLozinka(lozinka);
        return l;
    }

}
