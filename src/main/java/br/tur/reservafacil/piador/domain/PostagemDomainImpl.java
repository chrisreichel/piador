package br.tur.reservafacil.piador.domain;

import static com.google.common.base.Preconditions.checkNotNull;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import br.tur.reservafacil.piador.pio.Pio;

/**
 * Created by enrique on 4/30/15.
 */
public class PostagemDomainImpl
		implements PostagemDomain {

	private Map<String, List<Pio>> repoPios;

	public PostagemDomainImpl(Map<String, List<Pio>> repo) {
		this.repoPios = repo;
	}


	@Override public void fazPostagem(Pio pio) {
		checkNotNull(pio);
		if (repoPios.containsKey(pio.getUsername())) {
			repoPios.get(pio.getUsername()).add(pio);
		} else {
			List<Pio> pios = new ArrayList<Pio>();
			pios.add(pio);
			repoPios.put(pio.getUsername(), pios);
		}
	}

	@Override public List<Pio> listarPosts(String username) {
		return repoPios.get(username);
	}
	
	

}
