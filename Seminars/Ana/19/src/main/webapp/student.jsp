<%@ page import="dao.StudentDAO" %>
<%@ page import="java.util.List" %>
<%@ page import="bean.Student" %><%--
  Created by IntelliJ IDEA.
  User: asepiashvili
  Date: 28.04.26
  Time: 11:47
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Student Form</title>
</head>
<body>
<%
    String studentName = (String) request.getAttribute("studentName");
    if (studentName != null) {
%>
<h2>Hello <%=studentName%>!</h2>
<form method="POST" action="/student">
    <label for="first-name">
        First name: <input id="first-name" name="first-name" type="text">
    </label>
    <label for="last-name">
        Last name: <input id="last-name" name="last-name" type="text">
    </label>
    <button type="submit">Add</button>
</form>
<%
    }
%>
<table id="students-table">
    <thead>
    <tr>
        <th>First Name</th>
        <th>Last Name</th>
    </tr>
    </thead>
    <tbody id="students-tbody">
    <%
        StudentDAO dao = (StudentDAO) application.getAttribute("studentDAO");
        List<Student> students = dao.getAllStudents();
        for (Student student : students) {
    %>
    <tr>
        <td><%=student.getFirstName()%>
        </td>
        <td><%=student.getLastName()%>
        </td>
    </tr>
    <%
        }
    %>
    </tbody>
</table>
</body>
</html>