import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.UUID;

@WebServlet("/transfer")
public class TransferServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // Generate token HERE in servlet, store in session
        String csrfToken = UUID.randomUUID().toString();
        req.getSession().setAttribute("csrfToken", csrfToken);
        // Pass to JSP as request attribute too
        req.setAttribute("csrfToken", csrfToken);
        req.getRequestDispatcher("csrf.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws IOException {

        String amount = request.getParameter("amount");
        String to = request.getParameter("to");
        boolean safe = "true".equals(request.getParameter("safe"));

        response.setContentType("text/html; charset=UTF-8");

        if (safe) {
            // CSRF protection: validate token
            String sessionToken = (String) request.getSession().getAttribute("csrfToken");
            String requestToken = request.getParameter("csrfToken");

            if (sessionToken == null || !sessionToken.equals(requestToken)) {
                response.setStatus(403);
                response.getWriter().println("<h2 style='color:red'>[BLOCKED] CSRF Attack Blocked!</h2>");
                response.getWriter().println("<p>Token missing or invalid. Request rejected.</p>");
                response.getWriter().println("<a href='transfer'>&larr; Go back</a>");
                return;
            }
            response.getWriter().println("<h2 style='color:green'>[OK] Transfer Successful (CSRF token verified)</h2>");
        } else {
            // No protection — anyone can trigger this
            response.getWriter().println("<h2 style='color:red'>[WARNING] Transfer Executed (NO CSRF check!)</h2>");
            response.getWriter().println("<p>An attacker could have triggered this without your knowledge.</p>");
        }

        response.getWriter().println("<p>Sent <b>$" + amount + "</b> to <b>" + to + "</b></p>");
        response.getWriter().println("<a href='transfer'>&larr; Go back</a>");
    }
}
