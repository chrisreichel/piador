package br.tur.reservafacil.piador;

import br.tur.reservafacil.piador.domain.*;
import br.tur.reservafacil.piador.pio.PioRepositoryDefaultImpl;
import br.tur.reservafacil.piador.pio.Usuario;
import br.tur.reservafacil.piador.pio.UsuarioRepositoryDefaultImpl;
import org.apache.log4j.Logger;

import br.tur.reservafacil.piador.pio.Pio;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.Optional;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/timeline")
public class TimelineServlet extends HttpServlet {

    private static final Logger LOGGER = Logger.getLogger(TimelineServlet.class);

    @Override protected void doGet(HttpServletRequest request, HttpServletResponse response)
                    throws ServletException, IOException {

        final Optional<Usuario> usuarioLogado = HttpServletRequestUtils.getUsuario(request);
        if(!usuarioLogado.isPresent()){
            new ServletException("Usuário não está logado");
        }

        final String timelineDoUsuario = obterUsuario(usuarioLogado.get(), request);
        if(usuarioLogado.get().getAuthentication().getUserName().equalsIgnoreCase(timelineDoUsuario)){
            request.setAttribute("self", true);
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

    UsuarioDomain getUsuarioDomain(){
        return new UsuarioDomainImpl();
    }

    PostagemDomain getPostagemDomain(){
        return new PostagemDomainImpl(new PioRepositoryDefaultImpl());
    }

    SeguidorDomain getSeguidorDomain(){
        return new SeguidorDomainImpl(new UsuarioRepositoryDefaultImpl());
    }

    TimelineService getTimelineService(){
        return new TimelineService(getPostagemDomain(), getSeguidorDomain());
    }
}