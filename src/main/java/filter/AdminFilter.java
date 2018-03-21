package filter;

import data.Login;
import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import service.LoginService;
import util.UtilMethods;

public class AdminFilter implements Filter {

    final String AUTHORIZATION_HEADER = "Authorization";

    @Override
    public void init(FilterConfig fc) throws ServletException {
        // empty
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain filter)
            throws IOException, ServletException {
        if (request instanceof HttpServletRequest) {
            // TODO: dodati proveru tipova za request i response
            HttpServletRequest httpRequest = (HttpServletRequest) request;
            HttpServletResponse httpResponse = (HttpServletResponse) response;

            authAndResponse(httpRequest, httpResponse, filter);
        }
    }

    private void authAndResponse(HttpServletRequest httpRequest,
            HttpServletResponse httpResponse, FilterChain filter)
            throws ServletException, IOException {
        String authString = httpRequest.getHeader(AUTHORIZATION_HEADER);

        if (authString == null) {
            String errorMessage = responseMessageNoAuth(httpResponse);
            httpResponse.getWriter().write(errorMessage);
            return;
        }
        Login authenticationSuccessful = LoginService.getInstance().isAuthenticated(authString);
        if (authenticationSuccessful.getAdministracija()!=null) {
            httpRequest.getSession().setAttribute("student", authenticationSuccessful);
            httpRequest.getRequestDispatcher("Slike/rese.html").include(httpRequest, httpResponse);
            filter.doFilter(httpRequest, httpResponse);
        } else {
            String errorMessage = responseMessageBadAuth(httpResponse);
            httpResponse.getWriter().write(errorMessage);
        }
    }

    @Override
    public void destroy() {
        // empty
    }

    // -------------------------------------------------------------------------
    // Pomoćne metode
    //
    private String responseMessageNoAuth(HttpServletResponse httpResponse) {
        createResponseHeader(httpResponse, HttpServletResponse.SC_UNAUTHORIZED);

        final String errorMessage = "U headeru nisu poslati parametri za 'Basic Authorization'.";
        final String jsonErrorMessage = UtilMethods.createJsonErrorMessage(errorMessage);
        return jsonErrorMessage;
    }

    private String responseMessageBadAuth(HttpServletResponse httpResponse) {
        createResponseHeader(httpResponse, HttpServletResponse.SC_UNAUTHORIZED);

        final String errorMessage = "Nemate privilegije za ovo.";
        final String jsonErrorMessage = UtilMethods.createJsonErrorMessage(errorMessage);
        return jsonErrorMessage;
    }

    private void createResponseHeader(HttpServletResponse httpResponse, int status) {
        httpResponse.setStatus(status);
        httpResponse.setContentType("application/json");
        httpResponse.setCharacterEncoding("UTF-8");
    }

}
