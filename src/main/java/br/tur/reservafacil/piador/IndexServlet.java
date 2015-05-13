package br.tur.reservafacil.piador;

import br.tur.reservafacil.piador.pio.Usuario;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Optional;

/**
 * Created by christian on 13/05/15.
 */
@WebServlet("/index")
public class IndexServlet extends HttpServlet{

    private static final Logger LOGGER = Logger.getLogger(IndexServlet.class);

    @Override protected void doGet(HttpServletRequest request, HttpServletResponse response)
                    throws ServletException, IOException {

        final Optional<Usuario> usuario = HttpServletRequestUtils.getUsuario(request);
        if (usuario.isPresent()) {
            LOGGER.debug("Welcome back: " + usuario.get().getAuthentication().getUserName());
            new TimelineServlet().doGet(request, response);
        } else {
            request.getRequestDispatcher("notloged.jsp").include(request, response);
        }
    }

}
