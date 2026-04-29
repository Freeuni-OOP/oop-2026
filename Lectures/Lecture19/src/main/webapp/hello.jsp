<%@ page contentType="text/html;charset=UTF-8" %>

<html>
<body>
<h2>Hello JSP</h2>

<%
    String name = request.getParameter("name");
    if (name != null) {
%>
<p>Hello, <%= name %>!</p>
<%
} else {
%>
<p>No name provided</p>
<%
    }
%>

</body>
</html>