/**
 * 
 */
package br.tur.reservafacil.piador.domain;

import static com.google.common.base.Preconditions.checkNotNull;

import java.net.PasswordAuthentication;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import br.tur.reservafacil.piador.pio.Usuario;

/**
 * @author eduardobregaida
 *
 */
public class UsuarioDomainImpl implements UsuarioDomain {
	
	private Usuario usuario;
	private List<Usuario> usuarios = new ArrayList<>();
	
	public UsuarioDomainImpl(Usuario usuario) {
		this.usuario = usuario;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	@Override
	public void novoUsuario(Usuario usuario) {
		checkNotNull(usuario);
		usuarios.add(usuario);
	}

	@Override
	public boolean login(Usuario usuario) {
		checkNotNull(usuario);
		checkNotNull(usuario.getAuthentication());
			for (Usuario usuarioAux : usuarios) {
				if(usuarioAux.getAuthentication().getUserName().equals(usuario.getAuthentication().getUserName()) && Arrays.equals(usuarioAux.getAuthentication().getPassword(),usuario.getAuthentication().getPassword())){
				 return true;	
				}
		}
		return false;
	}

	
	

}
