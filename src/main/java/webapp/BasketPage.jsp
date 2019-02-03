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
                    out.println("<form method=\"delete\">");
                    out.println("<input type=\"submit\" name=\"removablePizza\" value=" + s + ">");
                    out.println("</form>");
                }
            }
    %>
    <br>
    <form method="post">
            Enter your address: <br>
            <input type="text" name="address"> <br>
            Enter your phone: <br>
            <input type="text" name="phone"> <br>
            <input type="submit" value="Confirm order">
    </form>
</body>
</html>