package servlets;

import bean.Student;
import dao.StudentDAO;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/student")
public class StudentServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String studentName = null;
        Cookie[] cookies = req.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals("student-name")) {
                    studentName = cookie.getValue();
                    break;
                }
            }
        }
        req.setAttribute("studentName", studentName);
        req.getRequestDispatcher("student.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String firstName = req.getParameter("first-name");
        String lastName = req.getParameter("last-name");

        Student student = new Student(firstName, lastName);
        StudentDAO studentDAO = (StudentDAO) getServletContext().getAttribute("studentDAO");
        studentDAO.addStudent(student);

        Cookie cookie = new Cookie("student-name", firstName);
        cookie.setMaxAge(60 * 60 * 24);
        resp.addCookie(cookie);

        resp.sendRedirect(req.getContextPath() + "/student");
    }
}
