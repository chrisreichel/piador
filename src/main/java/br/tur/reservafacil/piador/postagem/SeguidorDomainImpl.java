package br.tur.reservafacil.piador.postagem;

import br.tur.reservafacil.piador.pio.Pio;
import br.tur.reservafacil.piador.pio.UsuarioRepository;

import java.util.ArrayList;
import java.util.List;

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
	return usuarioRepository.findSeguidoresByUsername(username);
    }
}
