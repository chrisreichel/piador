package br.tur.reservafacil.piador.domain;

import br.tur.reservafacil.piador.pio.Pio;
import br.tur.reservafacil.piador.pio.PioRepository;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

import static com.google.common.base.Preconditions.checkNotNull;

@Service
public class PostagemDomainImpl implements PostagemDomain {

    private PioRepository pioRepository;

    public PostagemDomainImpl() {
    }

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
        final List<Pio> pios = pioRepository.findByUsername(username);
	return (pios != null) ? pios : Collections.<Pio>emptyList();
    }

}