import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;

@WebServlet("/profile")
public class ProfileServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response)
            throws IOException {

        HttpSession session = request.getSession(false); // returns null if no session

        if (session == null) {
            response.getWriter().println("Not logged in");
            return;
        }

        String user = (String) session.getAttribute("user");

        if (user == null) {
            response.getWriter().println("Not logged in");
        } else {
            response.getWriter().println("Welcome " + user);
        }
    }
}