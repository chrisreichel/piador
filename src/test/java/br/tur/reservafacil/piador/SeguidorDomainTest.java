package br.tur.reservafacil.piador;

import br.tur.reservafacil.piador.domain.SeguidorDomain;
import br.tur.reservafacil.piador.domain.SeguidorDomainImpl;
import br.tur.reservafacil.piador.pio.UsuarioRepository;
import br.tur.reservafacil.piador.pio.UsuarioRepositoryDefaultImpl;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertTrue;

public class SeguidorDomainTest {

    private static final String RONI = "@roni";
    private SeguidorDomain    domain;
    private UsuarioRepository usuarioRepository;

    @Before
    public void init() {
	usuarioRepository = new UsuarioRepositoryDefaultImpl();

	domain = new SeguidorDomainImpl(usuarioRepository);
	domain.seguir("@bruno", RONI);
    }

    @Test
    public void segueAlguem() {
	/*Assert.assertTrue("NÃO CONSEGUIMOS SEGUIR FULANO", repoSeguidores.get("@bruno").contains(RONI));*/
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