/**
 * 
 */
package br.tur.reservafacil.piador.domain;

import br.tur.reservafacil.piador.pio.Usuario;

/**
 * @author eduardobregaida
 *
 */
public interface UsuarioDomain {

	public void novoUsuario(Usuario usuario);

	public boolean login(Usuario usuario);

}
