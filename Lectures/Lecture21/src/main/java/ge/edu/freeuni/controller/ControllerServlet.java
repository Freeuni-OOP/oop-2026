package ge.edu.freeuni.controller;

import ge.edu.freeuni.dao.UserDao;
import ge.edu.freeuni.model.User;

import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@WebServlet("/controller")
public class ControllerServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response) throws IOException {
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

        UserDao userDao = getUserDao(response);
        if (userDao == null) {
            return;
        }

        try {
            List<User> users = userDao.getUsers();
            response.getWriter().write(toJson(users));
        } catch (SQLException e) {
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            response.getWriter().write("{\"error\":\"" + escapeJson(e.getMessage()) + "\"}");
        }

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

        UserDao userDao = getUserDao(response);
        if (userDao == null) {
            return;
        }

        String name = request.getParameter("name");

        if (name == null || name.trim().isEmpty()) {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            response.getWriter().write("{\"error\":\"name is required\"}");
            return;
        }

        String normalizedName = name.trim();
        try {
            userDao.save(normalizedName);
            response.getWriter().write("{\"status\":\"ok\",\"name\":\"" + escapeJson(normalizedName) + "\"}");
        } catch (SQLException e) {
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            response.getWriter().write("{\"error\":\"" + escapeJson(e.getMessage()) + "\"}");
        }
    }

    private UserDao getUserDao(HttpServletResponse response) throws IOException {
        UserDao userDao = (UserDao) getServletContext().getAttribute("userDao");
        if (userDao == null) {
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            response.getWriter().write("{\"error\":\"userDao is not initialized\"}");
        }
        return userDao;
    }

    private String toJson(List<User> users) {
        StringBuilder json = new StringBuilder("[");
        for (int i = 0; i < users.size(); i++) {
            if (i > 0) {
                json.append(',');
            }
            json.append("{\"name\":\"")
                    .append(escapeJson(users.get(i).getName()))
                    .append("\"}");
        }
        json.append(']');
        return json.toString();
    }

    private String escapeJson(String value) {
        if (value == null) {
            return "";
        }
        return value.replace("\\", "\\\\").replace("\"", "\\\"");
    }
}
