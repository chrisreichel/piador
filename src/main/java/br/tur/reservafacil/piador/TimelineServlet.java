package br.tur.reservafacil.piador;

import org.apache.log4j.Logger;

import br.tur.reservafacil.piador.pio.Pio;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/timeline")
public class TimelineServlet extends HttpServlet {

    private static Logger logger = Logger.getLogger(TimelineServlet.class);
    

    @Override protected void doGet(HttpServletRequest request, HttpServletResponse response)
                    throws ServletException, IOException {

    	String usuario = request.getParameter("username");

    	TimelineService service = new TimelineService();
    	List<Pio> pios = service.montaTimeline(usuario);

        logger.warn("Recebido GET");
        final PrintWriter out = response.getWriter();
        out.println("<HTML><BODY>");
        out.println("<BR><BR>");
        out.println("info:");
        out.println("<BR><BR>");
        out.println("<H2>metod GET</H2>");
        out.println("<BR><BR>");
        pios.forEach(out::println);
        out.println("SERVER_NAME="+request.getServerName()+"<BR>");
        out.println("REQUEST_METHOD="+request.getMethod()+"<BR>");
        out.println("QUERY_STRING="+request.getQueryString()+"<BR>");
        out.println("REMOTE_HOST="+request.getRemoteHost()+"<BR>");
        out.println("REMOTE_ADDR="+request.getRemoteAddr());
        out.println("</BODY></HTML>");
    }

    @Override protected void doPost(HttpServletRequest request, HttpServletResponse response)
                    throws ServletException, IOException {
        logger.warn("Recebido POST");
        final String username = request.getParameter("username");
        final String password = request.getParameter("password");
        logger.warn("Login: " + username);
        logger.warn("Senha: " + password);
        response.getWriter().println("Hello via POST");
        response.getWriter().println("Login: " + username);
        response.getWriter().println("Senha: " + password);
        response.getWriter().flush();
    }
}