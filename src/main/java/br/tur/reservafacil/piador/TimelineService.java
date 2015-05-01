package br.tur.reservafacil.piador;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import br.tur.reservafacil.piador.pio.Pio;
import br.tur.reservafacil.piador.postagem.PostagemDomain;
import br.tur.reservafacil.piador.postagem.PostagemDomainImpl;
import br.tur.reservafacil.piador.postagem.SeguidorDomain;
import br.tur.reservafacil.piador.postagem.SeguidorDomainImpl;

public class TimelineService {

    private PostagemDomain postagemDomain;
    private SeguidorDomain seguidorDomain;

    public TimelineService(PostagemDomain postagemDomain, SeguidorDomain seguidorDomain) {
        this.postagemDomain = postagemDomain;
        this.seguidorDomain = seguidorDomain;
    }

    public TimelineService() {
    	Map<String, List<Pio>> repo = new HashMap<String, List<Pio>>();
        Map<String, List<String>> repoSeguidores = new HashMap<>();

	    final List<Pio> postagens = new ArrayList<>();
	    postagens.add(new Pio("@chrisreichel", "Hello world 1"));
	    postagens.add(new Pio("@chrisreichel", "Hello world 2"));
	    postagens.add(new Pio("@chrisreichel", "Hello world 3"));
	    repo.put("@chrisreichel", postagens);

	    repoSeguidores.put("@marcio", Arrays.asList("@chrisreichel"));
    	PostagemDomain postagemDomain = new PostagemDomainImpl(repo);

    	SeguidorDomain seguidorDomain = new SeguidorDomainImpl(repoSeguidores);
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
