<%@ page import ="java.util.List"%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <%
        String name = (String) request.getAttribute("username");
        out.println(String.format("Hello <b>%s<b>", name));
    %>
    <title>Choose any pizzas</title>
</head>
<body>
    <form action="signout" method="get">
        <input type="submit" value="Sign Out">
    </form>
    <form action="catalog" method="get">
        <input type="submit" value="Show catalog">
    </form>
</body>
</html>