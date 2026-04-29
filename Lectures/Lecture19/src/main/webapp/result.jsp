<%@ page contentType="text/html;charset=UTF-8" %>
<%@ page import="ge.edu.freeuni.model.User" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%
    User user = (User) request.getAttribute("user");
%>

<html>
<body>

<h3>Welcome <%= user != null ? user.getName() : "" %>
</h3>

<p>Session User: <%= session.getAttribute("username") %>
</p>

<p>Admin Email (ServletConfig → Context):
    <%= application.getAttribute("adminEmail") %>
</p>

<p>DB Status: <%= request.getAttribute("dbStatus") != null ? request.getAttribute("dbStatus") : "No DB action" %></p>

<%--Print numbers from 1 to 5 using JSP scriptlet--%>
<c:forEach var="i" begin="1" end="5">
    <p>Number: ${i}</p>
</c:forEach>

</body>
</html>
