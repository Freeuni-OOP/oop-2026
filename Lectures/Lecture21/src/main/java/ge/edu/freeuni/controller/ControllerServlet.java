package ge.edu.freeuni.controller;

import ge.edu.freeuni.dao.UserDao;
import ge.edu.freeuni.dto.UserDTO;

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
                         HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

        UserDao userDao = (UserDao) getServletContext().getAttribute("userDao");
        if (userDao == null) {
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            response.getWriter().write("{\"error\":\"userDao is not initialized\"}");
            return;
        }

        try {
            List<UserDTO> users = userDao.getUsers();
            response.getWriter().write(toJson(users));
        } catch (SQLException e) {
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            response.getWriter().write("{\"error\":\"" + escapeJson(e.getMessage()) + "\"}");
        }

    }

    private String toJson(List<UserDTO> users) {
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
