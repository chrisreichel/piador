package br.tur.reservafacil.piador;

import org.apache.commons.io.FileUtils;
import org.junit.Test;
import org.mockito.Mockito;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.File;
import java.io.PrintWriter;

import static org.junit.Assert.*;

public class SimpleServletTest extends Mockito {

    @Test public void testDoGet() throws Exception {
        //Given
        final String tmpFileName = "/tmp/getResponse_" + System.currentTimeMillis();
        final HttpServletRequest request = mock(HttpServletRequest.class);
        final HttpServletResponse response = mock(HttpServletResponse.class);
        final PrintWriter writer = new PrintWriter(tmpFileName);
        when(response.getWriter()).thenReturn(writer);
        //When
        new SimpleServlet().doGet(request, response);
        writer.flush();//pode não ter sido chamado
        //Then
        assertTrue(FileUtils.readFileToString(new File(tmpFileName), "UTF-8")
                                   .contains("<HTML><BODY>\n"
                                                             + "<BR><BR>\n"
                                                             + "info:\n"
                                                             + "<BR><BR>\n"
                                                             + "<H2>metod GET</H2>\n"
                                                             + "<BR><BR>\n"
                                                             + "SERVER_NAME=null<BR>\n"
                                                             + "REQUEST_METHOD=null<BR>\n"
                                                             + "QUERY_STRING=null<BR>\n"
                                                             + "REMOTE_HOST=null<BR>\n"
                                                             + "REMOTE_ADDR=null\n"
                                                             + "</BODY></HTML>\n"));
    }

    @Test public void testDoPost() throws Exception {
        //Given
        final String tmpFileName = "/tmp/postResponse_" + System.currentTimeMillis();
        final HttpServletRequest request = mock(HttpServletRequest.class);
        final HttpServletResponse response = mock(HttpServletResponse.class);
        final PrintWriter writer = new PrintWriter(tmpFileName);
        when(response.getWriter()).thenReturn(writer);
        //When
        new SimpleServlet().doPost(request, response);
        writer.flush();//pode não ter sido chamado
        //Then
        assertTrue(FileUtils.readFileToString(new File(tmpFileName), "UTF-8").contains("Hello via POST"));
    }
}