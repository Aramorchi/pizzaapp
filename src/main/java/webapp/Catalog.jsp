<%@ page import ="java.util.List"%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Choose any pizzas</title>
</head>
<body>
    <%
    List<String> names = (List<String>) request.getAttribute("pizzaNames");

            if (names != null && !names.isEmpty()) {
                for (String s : names) {
                    out.println("<li>" + s + "</li>");
                    out.println("<form method=\"post\">");
                    out.println("<input type=\"submit\" name=\"pizza\" value=" + s + ">");
                    out.println("</form>");
                }
            }
    %>
</body>
</html>