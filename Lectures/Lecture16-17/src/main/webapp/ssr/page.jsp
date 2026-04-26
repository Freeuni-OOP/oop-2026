<%--server-side rendering--%>
<%@ page import="java.util.Date" %>

<html>

<head>
    <title>cracking page</title>
</head>

<body>

<%
    String username = request.getParameter("username");
    String password = request.getParameter("password");
%>

<h2>Welcome, <%= username %>!</h2>

<p>Your password is: <%= password %>
</p>

<br/>

<p>Current time: <%= new Date() %>
</p>

</body>
</html>