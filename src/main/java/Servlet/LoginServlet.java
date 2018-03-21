package Servlet;

import data.Login;
import data.Spi;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import service.LoginService;
import service.SpiService;

public class LoginServlet extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException {
        Login kor = createLogin(request);
        Login login = LoginService.getInstance().isAuthenticated(kor);
        String nextPage = null;
        if(login == null){
            request.setAttribute("errors", new String[]{"Неуспешна пријава на систем."});
        }
        else{
            if (login.getStudent()!=null) {
                System.out.println("*********************** STUDENT ************************************");
                request.getSession().setAttribute("korisnik", login);
                request.setAttribute("message", "Добродошао "+login.getKorIme());
                nextPage ="/student";
            }
            else if(login.getAdministracija() != null){
                System.out.println("*********************** ADMIN ************************************");
                request.getSession().setAttribute("administrator", login.getAdministracija());
                request.setAttribute("usao", "Добродошао "+login.getAdministracija().getIme()+" "+login.getAdministracija().getPrezime());
                nextPage = "/spi";
                List<Spi> osp = SpiService.getInstance().getAll();
                request.setAttribute("spi", osp);
            }
        }
        if(nextPage == null){
            response.sendRedirect(request.getHeader("Referer"));
        }
        else{
            request.getRequestDispatcher(nextPage).forward(request, response);
        }      
    }
    
    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException {
        processRequest(request, response);
    }
    
    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException {
        processRequest(request, response);
    }

    private Login createLogin(HttpServletRequest request) {
        Login l = null;
        try {
            String nalog = new String(request.getParameter("korIme").getBytes("ISO-8859-1"), "UTF-8");
            String sifra = new String(request.getParameter("loz").getBytes("ISO-8859-1"), "UTF-8");
            l = new Login();
            l.setKorIme(nalog);
            l.setLozinka(sifra);
            return l;
        } catch (UnsupportedEncodingException ex) {
            Logger.getLogger(LoginServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
        return l;
    }

}
