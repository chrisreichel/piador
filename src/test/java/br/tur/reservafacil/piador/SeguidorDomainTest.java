package br.tur.reservafacil.piador;

import static org.junit.Assert.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import br.tur.reservafacil.piador.domain.SeguidorDomain;
import br.tur.reservafacil.piador.domain.SeguidorDomainImpl;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class SeguidorDomainTest {

    private static final String RONI = "@roni";
    private SeguidorDomain domain;
    private Map<String, List<String>> repoSeguidores = new HashMap<>();

    @Before
    public void init() {
	domain = new SeguidorDomainImpl(repoSeguidores);
	domain.seguir("@bruno", RONI);
    }

    @Test
    public void segueAlguem() {
	Assert.assertTrue("NÃO CONSEGUIMOS SEGUIR FULANO", repoSeguidores.get("@bruno").contains(RONI));
    }

    @Test(expected = Exception.class)
    public void listarSeguidoresComParametroNulo() {
	domain.listarSeguidores(null);
	}

	@Test
	public void listarSeguidores() {
		List<String> listarSeguidores = domain.listarSeguidores("@bruno");
		assertTrue(listarSeguidores.contains(RONI));
	}
}