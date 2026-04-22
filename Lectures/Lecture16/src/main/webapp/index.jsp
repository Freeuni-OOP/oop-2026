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

</body>
</html>