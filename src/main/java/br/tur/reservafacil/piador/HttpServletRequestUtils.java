package br.tur.reservafacil.piador;

import br.tur.reservafacil.piador.pio.Usuario;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.net.PasswordAuthentication;
import java.util.Optional;

/**
 * Created by christian on 13/05/15.
 */
public class HttpServletRequestUtils {

    static final String SESSION_USER_ATTR = "$_USUARIO_PIADOR_$";

    static final String REQ_ERR_ATTR = "erro";

    static final String REQ_USERNAME_ATTR = "login";
    static final String REQ_PASSWORD_ATTR = "senha";
    static final String REQ_EMAIL_ATTR    = "email";

    public static Optional<Usuario> getUsuario(HttpServletRequest request) {
        final HttpSession session = request.getSession(false);
        if (session == null) {
            return Optional.empty();
        }
        final Usuario usuario = (Usuario)session.getAttribute(SESSION_USER_ATTR);
        return (usuario != null) ? Optional.of(usuario) : Optional.empty();
    }

    public static void setUsuarioNaSessao(Usuario usuario, HttpServletRequest request){
        final HttpSession session = request.getSession();
        session.setAttribute(SESSION_USER_ATTR, usuario);
    }

    public static PasswordAuthentication toPasswordAuthentication(HttpServletRequest request){
        final String username = (String) request.getParameter(REQ_USERNAME_ATTR);
        final String senha = (String) request.getParameter(REQ_PASSWORD_ATTR);
        return new PasswordAuthentication(username, senha.toCharArray());
    }

    public static void adicionaErro(String errorMsg, HttpServletRequest request) {
        request.setAttribute(REQ_ERR_ATTR, errorMsg);
    }

    public static Usuario toUsuario(HttpServletRequest request) {
        final String username = (String) request.getParameter(REQ_USERNAME_ATTR);
        final String email = (String) request.getParameter(REQ_EMAIL_ATTR);
        final String senha = (String) request.getParameter(REQ_PASSWORD_ATTR);
        return new Usuario(username, senha, email);
    }
}
