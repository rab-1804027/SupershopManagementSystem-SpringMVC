<%--
  Created by IntelliJ IDEA.
  User: bappi
  Date: 5/2/25
  Time: 6:18 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <style>
        body {
            margin: 0;
            padding: 0;
            height: 100vh;
            background: #f0f2f5;
            display: flex;
            justify-content: center;
            align-items: center;
        }

        .Login{
            background: white;
            padding: 40px;
            border-radius: 12px;
            box-shadow: 0 4px 10px rgba(0, 0, 0, 0.1);
            width: 350px;
        }

        .Login form {
            display: flex;
            flex-direction: column;
        }

        .Login h2 {
            text-align: center;
            margin-bottom: 20px;
            color: #333;
        }

        .Login input[type="text"],
        .Login input[type="password"] {
            padding: 10px;
            margin-bottom: 15px;
            border: 1px solid #ccc;
            border-radius: 6px;
        }

        .Login input[type="submit"] {
            padding: 10px;
            background: #007BFF;
            color: white;
            border: none;
            border-radius: 6px;
            cursor: pointer;
            font-weight: bold;
            transition: background 0.3s ease;
        }

        .Login input[type="submit"]:hover {
            background: #0056b3;
        }
    </style>
    <title>Login</title>
</head>
<body>

<div class="Login">
    <h2>Login into SuperShop</h2>
    <h4 style="color: red;">${error}</h4>
    <c:if test="${not empty errors}">
        <c:forEach var="error" items="${errors}">
            <h4 style="color: red;">${error.defaultMessage}.</h4>
        </c:forEach>
    </c:if>

    <form action="/api/v1/auth/login" method="post">
        <input type="text" name="username" placeholder="Enter Username" >
        <br>
        <input type="password" name="password" placeholder="Enter Password" >
        <br>
        <input type="submit" value="Login">
    </form>
</div>

</body>
</html>
