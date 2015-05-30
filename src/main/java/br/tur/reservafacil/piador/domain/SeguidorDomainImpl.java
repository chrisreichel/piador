package br.tur.reservafacil.piador.domain;

import br.tur.reservafacil.piador.pio.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.google.common.base.Preconditions.checkNotNull;

@Service
public class SeguidorDomainImpl implements SeguidorDomain {

    private UsuarioRepository usuarioRepository;

    @Autowired
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
