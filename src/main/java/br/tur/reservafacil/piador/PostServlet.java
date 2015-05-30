package br.tur.reservafacil.piador;

import br.tur.reservafacil.piador.pio.Pio;
import br.tur.reservafacil.piador.pio.Usuario;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Optional;

/**
 * Created by christian on 13/05/15.
 */
@WebServlet("/novoPost")
public class PostServlet extends BaseServlet {

    private static final Logger LOGGER = Logger.getLogger(PostServlet.class);

    @Override protected void doPost(HttpServletRequest request, HttpServletResponse response)
		    throws ServletException, IOException {

        final Optional<Usuario> usuario = HttpServletRequestUtils.getUsuario(request);
        final Pio pio = new Pio(usuario.get().getAuthentication().getUserName(), (String) request.getParameter("conteudo"));
        getPostagemDomain().fazPostagem(pio);
        response.sendRedirect("/piador/timeline");
    }
}
