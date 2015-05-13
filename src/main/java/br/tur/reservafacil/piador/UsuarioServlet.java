package br.tur.reservafacil.piador;

import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by christian on 13/05/15.
 */
@WebServlet("/usuario")
public class UsuarioServlet extends HttpServlet {

    private static final Logger LOGGER = Logger.getLogger(UsuarioServlet.class);

    @Override protected void doPost(HttpServletRequest req, HttpServletResponse resp)
		    throws ServletException, IOException {

    }
}
