<%@ page import="javax.servlet.http.Cookie" %>

<html>
<head>
    <title>Hello username</title>
    <link rel="stylesheet" href="styles.css">
    <link rel="icon" type="image/svg+xml" href="HelloWorld.svg">
</head>

<body>

<%
    String username = null;

    // 1. Read cookies first
    Cookie[] cookies = request.getCookies();
    if (cookies != null) {
        for (Cookie c : cookies) {
            if ("username".equals(c.getName())) {
                username = c.getValue();
                break;
            }
        }
    }

    // 2. If not found → set cookie
    if (username == null) {
        username = "world";

        Cookie cookie = new Cookie("username", username);
        cookie.setMaxAge(3600); // expires in 1 hour
        response.addCookie(cookie);
    }
%>

<h2>Hello <%= username %>!</h2>

<%-- try GET instead--%>
<form action="ssr/page.jsp" method="POST">
    <label for="username">Username:</label>
    <input type="text" id="username" name="username"/>

    <br><br>

    <label for="password">Password: </label>
    <input type="password" id="password" name="password"/>
    <br><br>

    <button type="submit">Login</button>
</form>

<a href="ssr/page.jsp">Go to page</a>

</body>
</html>