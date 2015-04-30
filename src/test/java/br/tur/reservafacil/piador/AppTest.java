package br.tur.reservafacil.piador;

import br.tur.reservafacil.piador.pio.Pio;
import br.tur.reservafacil.piador.postagem.PostagemDomain;
import br.tur.reservafacil.piador.postagem.PostagemDomainImpl;

import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.assertNotNull;

/**
 * Created by enrique on 4/30/15.
 */
public class AppTest {

    private PostagemDomain domain;
    private Map<String, List<Pio>> repos = new HashMap<String, List<Pio>>();

    @Before
    public void init() {
	Pio pio = new Pio("@nadison", "Hello World. Este é meu pio");
	repos.put(pio.getUsername(), Arrays.asList(pio));
	this.domain = new PostagemDomainImpl(repos);
    }

    @Test
    public void deveriaEnviarUmPost()
		    throws Exception {
	// Given
	Pio pio = new Pio("@chrisreichel", "Hello World. Este é meu pio");
	domain.fazPostagem(pio);
	// When
	final List<Pio> pios = domain.listarPosts("@chrisreichel");
	// Then
		assertNotNull(pios);
	}

	@Test(expected = Exception.class)
	public void deveLancarExceptionSeRepoIndisponivel() {
		this.domain = new PostagemDomainImpl(null);
		domain.fazPostagem(new Pio("@nadison", "Hello World. Este é meu pio"));
	}

	@Test(expected = Exception.class)
	public void deveLancarExceptionSeNaoAdicionarPostagem() {
		domain.fazPostagem(null);
	}

	@Test
	public void deveriaListarPost() throws Exception {
		List<Pio> pios = domain.listarPosts("@nadison");
		assertNotNull(pios);
	}

	

}
