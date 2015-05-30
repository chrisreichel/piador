package br.tur.reservafacil.piador;

import br.tur.reservafacil.piador.domain.TimelineService;
import br.tur.reservafacil.piador.pio.Pio;
import br.tur.reservafacil.piador.pio.Usuario;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

@WebServlet("/timeline")
public class TimelineServlet extends BaseServlet {

    private static final Logger LOGGER = Logger.getLogger(TimelineServlet.class);

    @Override protected void doGet(HttpServletRequest request, HttpServletResponse response)
                    throws ServletException, IOException {

        final Optional<Usuario> usuarioLogado = HttpServletRequestUtils.getUsuario(request);
        if (!usuarioLogado.isPresent()) {
            new ServletException("Usuário não está logado");
        }

        final String timelineDoUsuario = obterUsuario(usuarioLogado.get(), request);
        if (usuarioLogado.get().getAuthentication().getUserName().equalsIgnoreCase(timelineDoUsuario)) {
            request.setAttribute("self", usuarioLogado.get().getAuthentication().getUserName());
        }
        final TimelineService service = getTimelineService();
        final List<Pio> pios = service.montaTimeline(timelineDoUsuario);
        request.setAttribute("users", getUsuarioDomain().listaTodosUsuarios());
        request.setAttribute("pios", pios);

        request.getRequestDispatcher("timeline.jsp").include(request, response);
    }

    String obterUsuario(Usuario usuario, HttpServletRequest request) {
        final String username = (String) request.getParameter("username");
        if(username != null){
            return username;
        }
        return usuario.getAuthentication().getUserName();
    }
}