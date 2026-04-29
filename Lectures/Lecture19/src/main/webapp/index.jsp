<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title>MVC Example</title>
</head>
<body>

<h2>
    <%= application.getAttribute("appName") %>
</h2>

<form action="submit" method="post">
    <label> Enter Name: <input type="text" name="name"/></label>
    <input type="submit" value="Send"/>
</form>

</body>
</html>
