package br.tur.reservafacil.piador.domain;

import br.tur.reservafacil.piador.pio.Pio;
import br.tur.reservafacil.piador.pio.PioRepository;

import java.util.List;

import static com.google.common.base.Preconditions.checkNotNull;

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