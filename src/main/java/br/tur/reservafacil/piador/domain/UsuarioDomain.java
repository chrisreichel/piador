/**
 *
 */
package br.tur.reservafacil.piador.domain;

import br.tur.reservafacil.piador.pio.Usuario;

import java.net.PasswordAuthentication;
import java.util.Collection;

/**
 * @author eduardobregaida
 */
public interface UsuarioDomain {

    void novoUsuario(Usuario usuario);

    Usuario login(PasswordAuthentication usuario);

    Collection<Usuario> listaTodosUsuarios();
}
