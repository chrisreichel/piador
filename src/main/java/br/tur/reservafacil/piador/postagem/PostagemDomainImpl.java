package br.tur.reservafacil.piador.postagem;

import static com.google.common.base.Preconditions.checkNotNull;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import br.tur.reservafacil.piador.pio.Pio;
import br.tur.reservafacil.piador.pio.PioRepository;

public class PostagemDomainImpl implements PostagemDomain {

    private final PioRepository pioRepository;

    public PostagemDomainImpl(PioRepository pioRepository) {
	this.pioRepository = pioRepository;
    }

    @Override
    public void fazPostagem(Pio pio) {
	checkNotNull(pio);
	pioRepository.save(pio);
    }

    @Override
    public List<Pio> listarPosts(String username) {
	return pioRepository.findByUsername(username);
    }

}