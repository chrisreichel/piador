package br.tur.reservafacil.piador;

import br.tur.reservafacil.piador.pio.Usuario;
import org.junit.Test;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Optional;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * Created by christian on 13/05/15.
 */
public class HttpServletRequestUtilsTest {

    @Test public void deveObterUsuarioDaSessao()
		    throws Exception {
        //Given
        final Usuario usuarioNaSession = new Usuario("login", "senha");
        final HttpServletRequest request = mock(HttpServletRequest.class);
        final HttpSession session = mock(HttpSession.class);
        when(request.getSession(false)).thenReturn(session);
        when(session.getAttribute(HttpServletRequestUtils.SESSION_USER_ATTR)).thenReturn(usuarioNaSession);
        //When
        final Optional<Usuario> usuarioOptional = HttpServletRequestUtils.getUsuario(request);
        //Then
        assertTrue(usuarioOptional.isPresent());
        assertSame(usuarioOptional.get(), usuarioNaSession);
    }

    @Test public void deveNaoObterUsuarioDaSession()
                    throws Exception {
        //Given
        final HttpServletRequest request = mock(HttpServletRequest.class);
        final HttpSession session = mock(HttpSession.class);
        when(request.getSession(false)).thenReturn(session);
        //When
        final Optional<Usuario> usuarioOptional = HttpServletRequestUtils.getUsuario(request);
        //Then
        assertFalse(usuarioOptional.isPresent());
    }
}