package br.tur.reservafacil.piador;

import br.tur.reservafacil.piador.pio.Usuario;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Optional;

/**
 * Created by christian on 14/05/15.
 */
@WebServlet("/seguir")
public class SeguirServlet extends BaseServlet {

    @Override protected void doGet(HttpServletRequest request, HttpServletResponse response)
		    throws ServletException, IOException {
        final Optional<Usuario> usuario = HttpServletRequestUtils.getUsuario(request);
        if(usuario.isPresent()){
            final String usernameASeguir = request.getParameter("user");
            final Usuario usuarioEmSessao = usuario.get();
            getSeguidorDomain().seguir(usuarioEmSessao.getAuthentication().getUserName(), usernameASeguir);
            response.sendRedirect("/piador/timeline");
        }
    }
}
