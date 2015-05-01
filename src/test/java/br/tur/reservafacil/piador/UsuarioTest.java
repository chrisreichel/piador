/**
 * 
 */
package br.tur.reservafacil.piador;

import static org.junit.Assert.*;

import java.awt.color.ProfileDataException;
import java.net.Authenticator;
import java.net.PasswordAuthentication;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import br.tur.reservafacil.piador.domain.PostagemDomainImpl;
import br.tur.reservafacil.piador.domain.UsuarioDomain;
import br.tur.reservafacil.piador.domain.UsuarioDomainImpl;
import br.tur.reservafacil.piador.pio.Pio;
import br.tur.reservafacil.piador.pio.Profile;
import br.tur.reservafacil.piador.pio.Usuario;

/**
 * @author eduardobregaida
 *
 */
public class UsuarioTest {
	private UsuarioDomain usuarioDomain;
	private Usuario usuario;

	@Before
	public void init() {
		Profile profile = new Profile();
		profile.setNome("Eduardo");
		profile.setSobrenome("Bregaida");
		profile.setEmail("eduardo.bregaida@gmail.com");
		PasswordAuthentication authentication = new PasswordAuthentication(
				"@bregaida", "123".toCharArray());
		this.usuario = new Usuario(authentication, null, null, profile);
		this.usuarioDomain = new UsuarioDomainImpl(usuario);
	}

	@Test
	public void deveriaLogarUsuario() {
		usuarioDomain.novoUsuario(usuario);
		assertTrue(usuarioDomain.login(usuario));
	}

	@Test
	public void deveriaNaoLogarUsuario() {
		usuarioDomain.novoUsuario(usuario);
		usuario = new Usuario(new PasswordAuthentication("@joao", "123".toCharArray()), null, null, null);
		assertFalse(usuarioDomain.login(usuario));
	}
}
