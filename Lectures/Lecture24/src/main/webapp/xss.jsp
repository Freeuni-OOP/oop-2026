<%--
  Created by IntelliJ IDEA.
  User: gadikashvili
  Date: 5/20/2026
  Time: 3:45 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>XSS Demo</title>
</head>
<body>
    <h2>XSS (Cross-Site Scripting) Demo</h2>

    <h3 style="color:red">⚠️ Vulnerable Form (raw output)</h3>
    <p>Try typing: <code>&lt;script&gt;alert('XSS!')&lt;/script&gt;</code></p>
    <form method="post" action="comment?safe=false">
        Comment: <input type="text" name="comment" size="50"/>
        <input type="submit" value="Send (Unsafe)"/>
    </form>

    <hr/>

    <h3 style="color:green">✅ Safe Form (escaped output)</h3>
    <p>Same input, but sanitized on the server.</p>
    <form method="post" action="comment?safe=true">
        Comment: <input type="text" name="comment" size="50"/>
        <input type="submit" value="Send (Safe)"/>
    </form>
</body>
</html>
