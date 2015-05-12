/**
 *
 */
package br.tur.reservafacil.piador;

import static org.junit.Assert.*;

import java.net.PasswordAuthentication;
import java.util.ArrayList;

import br.tur.reservafacil.piador.domain.exceptions.UsuarioNotFoundException;
import br.tur.reservafacil.piador.pio.UsuarioRepository;
import br.tur.reservafacil.piador.pio.UsuarioRepositoryDefaultImpl;
import org.junit.Before;
import org.junit.Test;

import br.tur.reservafacil.piador.domain.UsuarioDomain;
import br.tur.reservafacil.piador.domain.UsuarioDomainImpl;
import br.tur.reservafacil.piador.pio.Profile;
import br.tur.reservafacil.piador.pio.Usuario;

/**
 * @author eduardobregaida
 */
public class UsuarioTest {

    private UsuarioDomain          usuarioDomain;
    private Usuario                usuario;
    private PasswordAuthentication validAuthentication;
    private UsuarioRepository      usuarioRepository;
    private PasswordAuthentication invalidAuthentication;

    @Before
    public void init() {
	Profile profile = new Profile();
	profile.setNome("Eduardo");
	profile.setSobrenome("Bregaida");
	profile.setEmail("eduardo.bregaida@gmail.com");

	validAuthentication   = new PasswordAuthentication("@eduardo.bregaida", "123".toCharArray());
	invalidAuthentication = new PasswordAuthentication("@eduardo.bregaida", "321".toCharArray());

	usuario = new Usuario(validAuthentication, new ArrayList<>(), new ArrayList<>(), profile);

	usuarioRepository = new UsuarioRepositoryDefaultImpl();
	usuarioDomain = new UsuarioDomainImpl(usuarioRepository);
	usuarioDomain.novoUsuario(usuario);
    }

    @Test
    public void deveriaLogarUsuario() {
	assertNotNull(usuarioDomain.login(validAuthentication));
    }

    @Test(expected = UsuarioNotFoundException.class)
    public void deveriaNaoLogarUsuario() {
	usuarioDomain.login(invalidAuthentication);
    }
}