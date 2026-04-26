import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request,
                          HttpServletResponse response)
            throws IOException {

        String username = request.getParameter("username");

        // create session
        HttpSession session = request.getSession();

        // store user inside session
        session.setAttribute("user", username);

        response.getWriter().println("Logged in!");
        response.sendRedirect("/profile");
    }
}