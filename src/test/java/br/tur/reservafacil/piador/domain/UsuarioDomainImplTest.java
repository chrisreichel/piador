package br.tur.reservafacil.piador.domain;

import br.tur.reservafacil.piador.domain.duble.UsuarioRepositoryDuble;
import br.tur.reservafacil.piador.domain.exceptions.UsuarioJaExisteException;
import br.tur.reservafacil.piador.domain.exceptions.UsuarioNotFoundException;
import br.tur.reservafacil.piador.pio.Usuario;
import org.junit.Before;
import org.junit.Test;

import java.net.PasswordAuthentication;

import static org.junit.Assert.*;

/**
 * Created by christian on 13/05/15.
 */
public class UsuarioDomainImplTest {

    private UsuarioRepositoryDuble repository;
    private UsuarioDomainImpl      domain;

    @Before public void setUp()
                    throws Exception {
        repository = new UsuarioRepositoryDuble();
        domain = new UsuarioDomainImpl(repository);
    }

    @Test public void deveCriarNovoUsuario()
                    throws Exception {
        //Given
        final Usuario newUsuario = new Usuario("junit", "senha", "user@aol.com");
        //When
        assertTrue(repository.db.isEmpty());
        domain.novoUsuario(newUsuario);
        //Then
        assertFalse(repository.db.isEmpty());
        assertEquals(repository.db.size(), 1);
    }

    @Test(expected = UsuarioJaExisteException.class)
    public void deveFalharNaTentativaDeCriarUmUsuarioQueJaExiste()
                    throws Exception {
        //Given
        final Usuario oldUsuario = new Usuario("junit", "senha1", "user@aol.com");
        final Usuario newUsuario = new Usuario("junit", "senha2", "user@aol.com.br");
        domain.novoUsuario(oldUsuario);
        //When
        domain.novoUsuario(newUsuario);
        //Then exception
    }

    @Test public void deveLogarUmUsuarioExistente() throws Exception {
        //Given
        final Usuario newUsuario = new Usuario("junit", "senha", "user@aol.com");
        domain.novoUsuario(newUsuario);
        //When
        final Usuario loggedUser = domain.login(new PasswordAuthentication("junit", "senha".toCharArray()));
        //Then
        assertSame(newUsuario, loggedUser);
    }

    @Test(expected = UsuarioNotFoundException.class)
    public void deveFalharAoTentarLogarUmUsuarioQueNaoExiste() throws Exception {
        domain.login(new PasswordAuthentication("junit", "senha".toCharArray()));
    }

    @Test(expected = UsuarioNotFoundException.class)
    public void deveFalharAoTentarLogarComSenhaErrada() throws Exception {
        //Given
        final Usuario newUsuario = new Usuario("junit", "senha", "user@aol.com");
        domain.novoUsuario(newUsuario);
        //When
        final Usuario loggedUser = domain.login(new PasswordAuthentication("junit", "password".toCharArray()));
    }
}