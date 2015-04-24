package br.tur.reservafacil.piador;

import org.apache.log4j.Logger;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/SimpleServlet")
public class SimpleServlet extends HttpServlet {

    private static Logger logger = Logger.getLogger(SimpleServlet.class);

    @Override protected void doGet(HttpServletRequest request, HttpServletResponse response)
                    throws ServletException, IOException {
        logger.warn("Recebido GET");
        response.getWriter().print("Hello World via GET");
    }

    @Override protected void doPost(HttpServletRequest request, HttpServletResponse response)
                    throws ServletException, IOException {
        logger.warn("Recebido POST");
        response.getWriter().print("Hello via POST");
    }
}