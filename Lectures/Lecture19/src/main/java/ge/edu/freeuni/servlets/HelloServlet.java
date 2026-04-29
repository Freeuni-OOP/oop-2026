package ge.edu.freeuni.servlets;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.*;

@WebServlet("/hello")
public class HelloServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws IOException {

        response.setContentType("text/html");

        PrintWriter out = response.getWriter();

        String name = request.getParameter("name");

        out.println("<html><body>");
        out.println("<h2>Hello JSP</h2>");

        if (name != null) {
            out.println("<p>Hello, " + name + "!</p>");
        } else {
            out.println("<p>No name provided</p>");
        }

        out.println("</body></html>");
    }
}