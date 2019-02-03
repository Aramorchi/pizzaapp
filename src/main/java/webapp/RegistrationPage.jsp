<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Registration page</title>
</head>
<body>
    <%
        String errorMessage = (String) request.getAttribute("errorMessage");
        if(errorMessage != null) {
            out.println(String.format("<b><font color=\"red\">%s</font></b>", errorMessage));
        }
    %>
    <form method="post">
        Enter your login: <br>
        <input type="text" name="username"> <br>
        Enter your password:
        <input type="password" name="password"> <br>
        Repeat your password:
        <input type="password" name="passwordRepeat"> <br>
        <input type="submit" name="Sign Up">
    </form>
</body>
</html>