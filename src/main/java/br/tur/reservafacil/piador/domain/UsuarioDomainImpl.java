/**
 *
 */
package br.tur.reservafacil.piador.domain;

import br.tur.reservafacil.piador.domain.exceptions.UsuarioJaExisteException;
import br.tur.reservafacil.piador.domain.exceptions.UsuarioNotFoundException;
import br.tur.reservafacil.piador.pio.Usuario;
import br.tur.reservafacil.piador.pio.UsuarioRepository;
import br.tur.reservafacil.piador.pio.UsuarioRepositoryDefaultImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.net.PasswordAuthentication;
import java.util.Collection;
import java.util.Optional;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * @author eduardobregaida
 */
@Service
public class UsuarioDomainImpl implements UsuarioDomain {

    private final UsuarioRepository usuarioRepository;

    public UsuarioDomainImpl() {
        usuarioRepository =  new UsuarioRepositoryDefaultImpl();
    }

    @Autowired
    public UsuarioDomainImpl(UsuarioRepository usuarioRepository) {
	this.usuarioRepository = usuarioRepository;
    }

    @Override
    public void novoUsuario(Usuario usuario) {
	checkNotNull(usuario);
        checkNotNull(usuario.getAuthentication());
        final Optional<Usuario> userNoBanco = usuarioRepository.findUsuarioByLogin(usuario.getAuthentication().getUserName());
        if(userNoBanco.isPresent()){
            throw new UsuarioJaExisteException();
        }
	usuarioRepository.insert(usuario);
    }

    @Override
    public Usuario login(PasswordAuthentication authentication) {
	checkNotNull(authentication);
	return usuarioRepository.findUsuarioByPasswordAuthentication(authentication).orElseThrow(() -> new UsuarioNotFoundException());
    }

    @Override public Collection<Usuario> listaTodosUsuarios() {
        return usuarioRepository.findAllUsuarios();
    }

}