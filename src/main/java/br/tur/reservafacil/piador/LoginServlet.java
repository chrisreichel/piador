package br.tur.reservafacil.piador;

import br.tur.reservafacil.piador.domain.UsuarioDomain;
import br.tur.reservafacil.piador.domain.UsuarioDomainImpl;
import br.tur.reservafacil.piador.domain.exceptions.UsuarioNotFoundException;
import br.tur.reservafacil.piador.pio.Usuario;
import org.apache.log4j.Logger;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.PasswordAuthentication;

/**
 * Created by christian on 13/05/15.
 */
@WebServlet("/login")
public class LoginServlet extends HttpServlet {

    private static final Logger LOGGER = Logger.getLogger(LoginServlet.class);

    @Override protected void doPost(HttpServletRequest request, HttpServletResponse response)
		    throws ServletException, IOException {


        final PasswordAuthentication auth = HttpServletRequestUtils.toPasswordAuthentication(request);
        try {
            final Usuario usuario = getUsuarioDomain().login(auth);
            HttpServletRequestUtils.setUsuarioNaSessao(usuario, request);
        }
        catch (UsuarioNotFoundException e){
            HttpServletRequestUtils.adicionaErro("Usuário não existe ou senha incorreta.", request);
            LOGGER.info("Usuario " + auth.getUserName() + " nao pode ser authenticado (não existe/senha errada)");
        }
        response.sendRedirect("/piador/index");
    }

    UsuarioDomain getUsuarioDomain(){
	return new UsuarioDomainImpl();
    }
}
