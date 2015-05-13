package br.tur.reservafacil.piador;

import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * Created by christian on 13/05/15.
 */
@WebServlet("/index")
public class IndexServlet extends HttpServlet{

    private static final Logger LOGGER = Logger.getLogger(IndexServlet.class);

    @Override protected void doGet(HttpServletRequest request, HttpServletResponse response)
                    throws ServletException, IOException {

        final HttpSession session = request.getSession(false);
        LOGGER.info("Sessao: " + session);
        if (session != null && session.getAttribute("USUARIO") != null) {
            request.getRequestDispatcher("/timeline").include(request, response);
        } else {
            request.getRequestDispatcher("notloged.jsp").include(request, response);
        }
    }

}
