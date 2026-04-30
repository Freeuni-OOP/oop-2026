<%@ page import="util.Constants" %>
<%@ page import="dao.StudentDAO" %>
<%@ page import="filter.Filter" %>
<%@ page import="java.util.List" %>
<%@ page import="bean.Student" %>
<%@ page import="static util.Constants.USER_NAME_COOKIE" %>
<%@ page contentType="text/html;charset=UTF-8" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Students form</title>
    <link rel="stylesheet" href="styles/student.css"/>
</head>

<body>

<%
    Cookie[] cookies = request.getCookies();
    Cookie userCookie = null;
    for (Cookie cookie : cookies) {
        if (cookie.getName().equals(USER_NAME_COOKIE)) {
            userCookie = cookie;
            break;
        }
    }

    if (userCookie == null) {
%>
<form id="students-form" method="POST" action="/student">
    <h2>Add Student</h2>
    <label for="first-name">
        First name: <input id="first-name" name="first-name"/>
    </label>

    <label for="last-name">
        Last name: <input id="last-name" name="last-name"/>
    </label>

    <button type="submit">Submit</button>
</form>

<form id="search-form" action="/student" method="get">
    <h2>Search Students</h2>
    <label for="search-first-name">
        <input type="text" id="search-first-name" placeholder="enter first name" name="first-name"/>
    </label>
    <label for="search-last-name">
        <input type="text" id="search-last-name" placeholder="enter last name" name="last-name"/>
    </label>
    <button type="submit">Search</button>
</form>
<br/>

<table id="students-table">
    <tr>
        <th>First name</th>
        <th>Last name</th>
    </tr>

    <%
        StudentDAO dao = (StudentDAO) application.getAttribute(Constants.DAO_KEY);

        Filter filter = (Filter) request.getAttribute("filter");
        List<Student> students = dao.filterStudents(filter);

        for (Student student : students) {
    %>

    <tr>
        <td><%= student.getFirstName() %>
        </td>
        <td><%= student.getLastName() %>
        </td>
    </tr>

    <% } %>
</table>

<%
} else {
%>

<h2>Hello <%= userCookie.getValue()%>
</h2>

<%
    }
%>

</body>

</html>
