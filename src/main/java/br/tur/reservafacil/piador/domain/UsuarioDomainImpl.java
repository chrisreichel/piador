/**
 *
 */
package br.tur.reservafacil.piador.domain;


import java.net.PasswordAuthentication;

import br.tur.reservafacil.piador.domain.exceptions.UsuarioNotFoundException;
import br.tur.reservafacil.piador.pio.Usuario;
import br.tur.reservafacil.piador.pio.UsuarioRepository;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * @author eduardobregaida
 */
public class UsuarioDomainImpl implements UsuarioDomain {

    private final UsuarioRepository usuarioRepository;

    public UsuarioDomainImpl(UsuarioRepository usuarioRepository) {
	this.usuarioRepository = usuarioRepository;
    }

    @Override
    public void novoUsuario(Usuario usuario) {
	checkNotNull(usuario);
	usuarioRepository.save(usuario);
    }

    @Override
    public Usuario login(PasswordAuthentication authentication) {
	checkNotNull(authentication);
	return usuarioRepository.findUsuarioByPasswordAuthentication(authentication).orElseThrow(() -> new UsuarioNotFoundException());
/*
	for (Usuario usuarioAux : usuarios) {
	    if (usuarioAux.getAuthentication().getUserName().equals(authentication.getAuthentication().getUserName()) && Arrays.equals(
			    usuarioAux.getAuthentication().getPassword(), authentication.getAuthentication().getPassword())) {
		return true;
	    }
	}
*/
    }

}