<%@ page import="util.Constants" %>
<%@ page import="dao.StudentDAO" %>
<%@ page import="java.util.List" %>
<%@ page import="bean.Student" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Students form</title>
    <link rel="stylesheet" href="styles/student.css"/>
</head>

<body>

<form id="students-form" method="POST" action="<%= request.getContextPath() %>/student">

    <label for="first-name">
        First name: <input id="first-name" name="first-name"/>
    </label>

    <label>
        Last name: <input id="last-name" name="last-name"/>
    </label>

    <button type="submit">Submit</button>
</form>

<table id="students-table">
    <tr>
        <th>First name</th>
        <th>Last name</th>
    </tr>

    <%
        StudentDAO dao = (StudentDAO) application.getAttribute(Constants.DAO_KEY);

        List<Student> students = dao.getAllStudents();

        for (Student student : students) {
    %>

    <tr>
        <td><%= student.getFirstName() %></td>
        <td><%= student.getLastName() %></td>
    </tr>

    <% } %>
</table>

</body>

</html>
