/**
 *
 */
package br.tur.reservafacil.piador.domain;

import br.tur.reservafacil.piador.pio.Usuario;

import java.net.PasswordAuthentication;

/**
 * @author eduardobregaida
 */
public interface UsuarioDomain {

    public void novoUsuario(Usuario usuario);

    public Usuario login(PasswordAuthentication usuario);

}
