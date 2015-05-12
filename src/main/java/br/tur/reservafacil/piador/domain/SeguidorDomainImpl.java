package br.tur.reservafacil.piador.domain;

import br.tur.reservafacil.piador.pio.UsuarioRepository;

import java.util.List;

import static com.google.common.base.Preconditions.checkNotNull;


public class SeguidorDomainImpl implements SeguidorDomain {

    private final UsuarioRepository usuarioRepository;

    public SeguidorDomainImpl(UsuarioRepository usuarioRepository) {
	super();
	this.usuarioRepository = usuarioRepository;
    }

    @Override
    public void seguir(String username, String seguidor) {
	usuarioRepository.addSeguidor(username, seguidor);
    }

    @Override
    public List<String> listarSeguidores(String username) {
	checkNotNull(username);
	return usuarioRepository.findSeguidoresByUsername(username);
    }
}
