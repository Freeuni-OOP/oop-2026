import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.*;
import java.io.IOException;

@WebServlet("/comment")
public class CommentServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("xss.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws IOException {

        String comment = request.getParameter("comment");
        boolean safe = "true".equals(request.getParameter("safe"));

        response.setContentType("text/html");

        String output = safe ? escapeHtml(comment) : comment;
        String mode = safe ? "<span style='color:green'>✅ SAFE (escaped)</span>"
                           : "<span style='color:red'>⚠️ VULNERABLE (raw)</span>";

        response.getWriter().println("<h2>Comment Result — " + mode + "</h2>");
        response.getWriter().println("<p><b>Your comment:</b> " + output + "</p>");
        if (safe) {
            response.getWriter().println("<p><i>HTML special chars were escaped — no script runs.</i></p>");
        } else {
            response.getWriter().println("<p><i>Raw input rendered — script tags execute!</i></p>");
        }
        response.getWriter().println("<br/><a href='xss.jsp'> <- Go back</a>");
    }

    private String escapeHtml(String input) {
        if (input == null) return "";
        return input
            .replace("&", "&amp;")
            .replace("<", "&lt;")
            .replace(">", "&gt;")
            .replace("\"", "&quot;")
            .replace("'", "&#x27;");
    }
}
