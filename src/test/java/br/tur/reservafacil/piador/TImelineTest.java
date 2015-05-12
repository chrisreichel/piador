package br.tur.reservafacil.piador;

import br.tur.reservafacil.piador.pio.Pio;
import br.tur.reservafacil.piador.postagem.PostagemDomain;
import br.tur.reservafacil.piador.postagem.SeguidorDomain;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class TImelineTest {

    private TimelineService timelineService;
    private SeguidorDomain  seguidorDomain;
    private PostagemDomain  postagemDomain;

    @Before
    public void init() throws Exception {
	postagemDomain = mock(PostagemDomain.class);
	seguidorDomain = mock(SeguidorDomain.class);
	timelineService = new TimelineService(postagemDomain, seguidorDomain);
    }

    @Test
    public void deveExibirATimeline() {
	final List<Pio> postagens = new ArrayList<>();
	postagens.add(new Pio("@chrisreichel", "Hello world 1"));
	postagens.add(new Pio("@chrisreichel", "Hello world 2"));
	postagens.add(new Pio("@chrisreichel", "Hello world 3"));
	final List<Pio> postagens2 = new ArrayList<>();
	postagens2.add(new Pio("@chris", "Hello world 1"));
	postagens2.add(new Pio("@chris", "Hello world 2"));
	postagens2.add(new Pio("@chris", "Hello world 3"));
	when(postagemDomain.listarPosts("@chrisreichel")).thenReturn(postagens);
	when(postagemDomain.listarPosts("@chris")).thenReturn(postagens2);

	List<String> seguidores = new ArrayList<String>() {{
	    add("@chris");
	    add("@chrisreichel");
	}};

	when(seguidorDomain.listarSeguidores("@bruno")).thenReturn(seguidores);
	List<Pio> pios = timelineService.montaTimeline("@bruno");

	assertNotNull(pios);

	assertEquals(6, pios.size());

    }

}
