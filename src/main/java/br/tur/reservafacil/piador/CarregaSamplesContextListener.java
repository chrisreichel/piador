package br.tur.reservafacil.piador;

import br.tur.reservafacil.piador.pio.PioRepositoryDefaultImpl;
import br.tur.reservafacil.piador.pio.UsuarioRepositoryDefaultImpl;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

/**
 * Created by christian on 13/05/15.
 */
//TODO: Quando houver persistência de verdade, apagar esta classe
@WebListener
public class CarregaSamplesContextListener implements ServletContextListener {

    @Override public void contextInitialized(ServletContextEvent servletContextEvent) {
        final UsuarioRepositoryDefaultImpl userRepo = new UsuarioRepositoryDefaultImpl();
        userRepo.initSeguidores();
        userRepo.initUsuarios();
        final PioRepositoryDefaultImpl pioRepo = new PioRepositoryDefaultImpl();
        pioRepo.initRepos();
    }

    @Override public void contextDestroyed(ServletContextEvent servletContextEvent) {
        //Awaaaaayyyyyyyy!
    }
}
