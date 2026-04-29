package ge.edu.freeuni.controller;

import ge.edu.freeuni.dao.UserDao;
import ge.edu.freeuni.model.User;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.IOException;
import java.sql.SQLException;

public class ControllerServlet extends HttpServlet {

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        String admin = config.getInitParameter("adminEmail");
        getServletContext().setAttribute("adminEmail", admin);
    }

    @Override
    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("result.jsp")
                .forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request,
                          HttpServletResponse response)
            throws ServletException, IOException {

        String name = request.getParameter("name");

        User user = new User(name);
        request.setAttribute("user", user);

        UserDao userDao = (UserDao) getServletContext().getAttribute("userDao");
        String dbInitError = (String) getServletContext().getAttribute("dbInitError");

        if (dbInitError != null) {
            request.setAttribute("dbStatus", "DB init error: " + dbInitError);
        } else if (userDao != null) {
            try {
                userDao.save(user);
                request.setAttribute("dbStatus", "Saved to MySQL successfully");
            } catch (SQLException e) {
                request.setAttribute("dbStatus", "DB save failed: " + e.getMessage());
            }
        } else {
            request.setAttribute("dbStatus", "UserDao is not configured");
        }

        HttpSession session = request.getSession();
        session.setAttribute("username", name);

        request.getRequestDispatcher("result.jsp")
                .forward(request, response);
    }
}
