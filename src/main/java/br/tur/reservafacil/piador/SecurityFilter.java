package br.tur.reservafacil.piador;

import br.tur.reservafacil.piador.pio.Usuario;
import org.apache.log4j.Logger;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.Optional;

@WebFilter(urlPatterns={"/*"})
public class SecurityFilter  implements Filter{

    private static final Logger LOGGER = Logger.getLogger(SecurityFilter.class);

    @Override public void init(FilterConfig filterConfig)
		    throws ServletException {
    }

    @Override public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain)
		    throws IOException, ServletException {
        final HttpServletRequest request = (HttpServletRequest) servletRequest;
        if(!shouldIgnoreRequest(request.getRequestURI())){
            final Optional<Usuario> usuario = HttpServletRequestUtils.getUsuario(request);
            LOGGER.info("=============================");
            LOGGER.info("Filtrando: " + request.getRequestURI());
            if(!usuario.isPresent()){
                LOGGER.error("Usuario nao encontrado na sessao");
	        throw new ServletException("Acesso negado");
            }
            LOGGER.info("=============================");
        }
        filterChain.doFilter(servletRequest, servletResponse);
    }

    boolean shouldIgnoreRequest(String url){
        if(url.endsWith(".css") || url.endsWith(".js") || url.endsWith("/piador/")
		        || url.endsWith("/index") || url.endsWith("/login") || url.endsWith("/usuario")){
            return true;
        }
        return false;
    }

    @Override public void destroy() {}
}
