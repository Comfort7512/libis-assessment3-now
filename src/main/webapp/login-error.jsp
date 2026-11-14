<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<!DOCTYPE html>
<html>
<head>
    <title>Login Failed</title>
</head>

<body>

<h2>Authentication Failed</h2>

<p>Incorrect email or password. Please try again.</p>

<a href="${pageContext.request.contextPath}/login.jsp">
    Return to Login
</a>

</body>
</html>