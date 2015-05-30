package br.tur.reservafacil.piador;

import br.tur.reservafacil.piador.domain.PostagemDomain;
import br.tur.reservafacil.piador.domain.SeguidorDomain;
import br.tur.reservafacil.piador.domain.TimelineService;
import br.tur.reservafacil.piador.domain.UsuarioDomain;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import javax.servlet.http.HttpServlet;

/**
 * Created by enrique on 5/29/15.
 */
public abstract class BaseServlet extends HttpServlet {
    public WebApplicationContext getSpringContext() {
	final WebApplicationContext ctx = WebApplicationContextUtils.getRequiredWebApplicationContext(getServletContext());
	return ctx;
    }

    UsuarioDomain getUsuarioDomain(){
	final WebApplicationContext springContext = getSpringContext();
	return springContext.getBean(UsuarioDomain.class);
    }

    PostagemDomain getPostagemDomain(){
	final WebApplicationContext springContext = getSpringContext();
	return springContext.getBean(PostagemDomain.class);
    }

    SeguidorDomain getSeguidorDomain(){
	final WebApplicationContext springContext = getSpringContext();
	return springContext.getBean(SeguidorDomain.class);
    }

    TimelineService getTimelineService(){
	final WebApplicationContext springContext = getSpringContext();
	return springContext.getBean(TimelineService.class);
    }
}
