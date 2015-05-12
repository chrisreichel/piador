package br.tur.reservafacil.piador;

import br.tur.reservafacil.piador.domain.PostagemDomain;
import br.tur.reservafacil.piador.domain.SeguidorDomain;
import br.tur.reservafacil.piador.pio.Pio;

import java.util.ArrayList;
import java.util.List;

public class TimelineService {

    private PostagemDomain postagemDomain;
    private SeguidorDomain seguidorDomain;

    public TimelineService(PostagemDomain postagemDomain, SeguidorDomain seguidorDomain) {
        this.postagemDomain = postagemDomain;
        this.seguidorDomain = seguidorDomain;
    }

    public List<Pio> montaTimeline(String usuario) {
    	List<Pio> resultado = new ArrayList<>();
    	List<String> seguidores = seguidorDomain.listarSeguidores(usuario);
    	for (String seguidor: seguidores) {
    		resultado.addAll(postagemDomain.listarPosts(seguidor));
    	}
        return resultado;
    }
}
