package servlets;

import bean.Student;
import dao.StudentDAO;
import util.Constants;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/student")
public class StudentServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("student.jsp").forward(req, resp);
    }

    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String firstName = req.getParameter("first-name");
        String lastName = req.getParameter("last-name");

        Student student = new Student(firstName, lastName);

        ServletContext context = req.getServletContext();
        StudentDAO dao = (StudentDAO) context.getAttribute(Constants.DAO_KEY);

        dao.addStudent(student);
        resp.sendRedirect(req.getContextPath() + "/student");
    }
}
