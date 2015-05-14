package br.tur.reservafacil.piador.domain;

import br.tur.reservafacil.piador.domain.PostagemDomain;
import br.tur.reservafacil.piador.domain.SeguidorDomain;
import br.tur.reservafacil.piador.pio.Pio;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class TimelineService {

    private PostagemDomain postagemDomain;
    private SeguidorDomain seguidorDomain;

    public TimelineService(PostagemDomain postagemDomain, SeguidorDomain seguidorDomain) {
        this.postagemDomain = postagemDomain;
        this.seguidorDomain = seguidorDomain;
    }

    public List<Pio> montaTimeline(String usuario) {
    	final List<Pio> resultado = new ArrayList<>();
        final List<String> seguidores = seguidorDomain.listarSeguidores(usuario);
        resultado.addAll(postagemDomain.listarPosts(usuario));
    	for (String seguidor: seguidores) {
    		resultado.addAll(postagemDomain.listarPosts(seguidor));
    	}
        Collections.sort(resultado, new Comparator<Pio>() {
            @Override public int compare(Pio o1, Pio o2) {
                return o2.getDataCriacao().compareTo(o1.getDataCriacao());
            }
        });

        return resultado;
    }
}
