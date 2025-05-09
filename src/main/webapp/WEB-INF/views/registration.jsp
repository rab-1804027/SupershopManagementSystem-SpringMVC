<%--
  Created by IntelliJ IDEA.
  User: bappi
  Date: 5/2/25
  Time: 6:18 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
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

        .RegistrationForm {
            background: white;
            padding: 40px;
            border-radius: 12px;
            box-shadow: 0 4px 10px rgba(0, 0, 0, 0.1);
            width: 350px;
        }

        .RegistrationForm form {
            display: flex;
            flex-direction: column;
        }

        .RegistrationForm h2 {
            text-align: center;
            margin-bottom: 20px;
            color: #333;
        }

        .RegistrationForm input[type="text"],
        .RegistrationForm input[type="email"],
        .RegistrationForm input[type="number"],
        .RegistrationForm input[type="password"] {
            padding: 10px;
            margin-bottom: 15px;
            border: 1px solid #ccc;
            border-radius: 6px;
        }

        .RegistrationForm input[type="submit"] {
            padding: 10px;
            background: #007BFF;
            color: white;
            border: none;
            border-radius: 6px;
            cursor: pointer;
            font-weight: bold;
            transition: background 0.3s ease;
        }

        .RegistrationForm input[type="submit"]:hover {
            background: #0056b3;
        }
    </style>
    <title>Registration</title>
</head>
<body>

    <div class="RegistrationForm">
        <h1>Sign Up</h1>
        <form action="/auth/v1/registration" method="post">

            <input type="text" name="name" placeholder="Enter Your Name" required>
            ${errors.get("name")}
            <br>
            <input type="email" name="email" placeholder="Enter Your Email Address" required>
            ${errors.get("email")}
            <br>
            <input type="text" name="username" placeholder="Enter a Username" required>
            ${errors.get("username")}
            <br>
            <input type="password" name="password" placeholder="Set a Password" required>
            ${errors.get("password")}
            <br>
            ${inputUser}
            <input type="password" name="confirmPassword" placeholder="Repeat the password" required>
            ${errors.get("passwordMismatch")}
            <br>
            <input type="submit" value="Register">
        </form>
    </div>
</body>
</html>
