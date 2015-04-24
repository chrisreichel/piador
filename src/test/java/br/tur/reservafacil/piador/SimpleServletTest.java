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
                                   .contains("Hello World via GET"));
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