package br.tur.reservafacil.piador;

import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/LoadPageServlet")
public class LoadPageServlet extends HttpServlet {

    private static final Logger LOGGER = Logger.getLogger(LoadPageServlet.class);

    @Override protected void doGet(HttpServletRequest request, HttpServletResponse response)
                    throws ServletException, IOException {

        request.getRequestDispatcher("/hello.jsp").include(request, response);
    }
}
