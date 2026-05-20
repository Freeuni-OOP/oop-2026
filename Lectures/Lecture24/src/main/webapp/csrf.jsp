<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    // Token was set by TransferServlet's doGet and stored in session + request attribute
    String csrfToken = (String) request.getAttribute("csrfToken");
    if (csrfToken == null) {
        // Fallback: read from session if accessed directly
        csrfToken = (String) session.getAttribute("csrfToken");
    }
%>
<html>
<head><title>CSRF Demo</title></head>
<body>
<h2>CSRF (Cross-Site Request Forgery) Demo</h2>

<h3 style="color:gray">🔑 Current CSRF Token (session): <code><%= csrfToken %></code></h3>

<!-- ===== VULNERABLE FORM ===== -->
<h3 style="color:red">⚠️ Vulnerable Transfer (no CSRF token)</h3>
<p>An attacker page could silently submit this form on your behalf.</p>
<form method="post" action="transfer?safe=false">
    Send $<input type="number" name="amount" value="1000"/> to:
    <input type="text" name="to" value="attacker@evil.com"/>
    <input type="submit" value="Transfer (Unsafe)"/>
</form>

<hr/>

<!-- ===== SAFE FORM ===== -->
<h3 style="color:green">✅ Protected Transfer (with CSRF token)</h3>
<p>Token is tied to your session — an attacker can't know it.</p>
<form method="post" action="transfer?safe=true">
    Send $<input type="number" name="amount" value="1000"/> to:
    <input type="text" name="to" value="attacker@evil.com"/>
    <input type="hidden" name="csrfToken" value="<%= csrfToken %>"/>
    <input type="submit" value="Transfer (Safe)"/>
</form>

<hr/>

<!-- ===== SIMULATED ATTACKER PAGE ===== -->
<h3 style="color:darkred">Simulated Attacker Page</h3>
<p>Click below to open the fake "evil.com" page — it will try to forge a transfer using your session:</p>
<a href="evil.html" target="_blank" style="font-size:1.2em; color:red; font-weight:bold;">
    &rarr; Open evil.com (opens in new tab)
</a>
<p style="color:gray; font-size:0.9em">
    In real life this would be a completely separate website. Here it's just a separate HTML file to simulate it.
</p>

</body>
</html>

