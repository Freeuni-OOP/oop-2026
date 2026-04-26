import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;

@WebServlet("/cookie")
public class CookieServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws IOException {
        String username = req.getParameter("username");

        if (username == null) {
            resp.getWriter().write("Provide username");
            return;
        }

        Cookie cookie = new Cookie("username", username);
        cookie.setMaxAge(60 * 60); // 1 hour
        resp.addCookie(cookie);

        resp.getWriter().write("Cookie saved!");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws IOException {

        Cookie[] cookies = req.getCookies();

        if (cookies != null) {
            for (Cookie c : cookies) {
                if ("username".equals(c.getName())) {
                    resp.getWriter().write("Username: " + c.getValue());
                    return;
                }
            }
        }

        resp.getWriter().write("No cookie found");
    }
}