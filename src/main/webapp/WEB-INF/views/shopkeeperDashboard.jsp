
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>SuperShop Management</title>
    <style>
        .navbar{
            display: flex;
            align-items: center;
            justify-content: space-between;
            width: 100%;
            background-color: #fff;
        }
        .navbar nav{
            flex: 1;
            text-align: right;
        }
        .navbar nav ul{
            display: inline-block;
            list-style-type: none;
        }
        .navbar nav ul li{
            display: inline-block;
            margin-right: 20px;
        }
        .navbar a{
            text-decoration: none;
            color: #555;
        }
        a:hover {
            color: #007BFF;
            text-decoration: underline;
        }

        .productList {
            margin: 40px auto;
            width: 90%;
            font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
            background-color: #f4f6f8;
        }

        .productList h1 {
            text-align: center;
            margin-bottom: 20px;
            color: #333;
        }

        /* Table base */
        .productList table {
            width: 100%;
            border-collapse: collapse;
            background-color: #fff;
            border-radius: 8px;
            overflow: hidden;
            box-shadow: 0 2px 10px rgba(0, 0, 0, 0.05);
        }

        .productList thead {
            background-color: #28a745;
            color: white;
        }

        .productList th, .productList td {
            padding: 12px 15px;
            text-align: left;
            border-bottom: 1px solid #ddd;
        }

        .productList tbody tr:hover {
            background-color: #f1f1f1;
        }

        /* Button styles */
        .productList button {
            background-color: #007BFF;
            border: none;
            padding: 6px 10px;
            border-radius: 4px;
            cursor: pointer;
            margin-right: 5px;
            transition: background-color 0.3s ease;
        }

        .productList button a {
            color: white;
            text-decoration: none;
            font-weight: bold;
        }

        /* Hover effect */
        .productList button:hover {
            background-color: #0056b3;
        }

        /* Optional: Delete button style */
        .productList td button:nth-child(2) {
            background-color: #dc3545;
        }

        .productList td button:nth-child(2):hover {
            background-color: #a71d2a;
        }

    </style>
</head>
<body>
<div class="navbar">
    <div class = "title">
        <h1>SuperShop Management System</h1>
    </div>
    <nav>
        <ul>
            <li><a href="/api/v1/dashboard">Home</a></li>
            <li><a href="/api/v1/product/form">Add New Product</a></li>
            <li><a href="/api/v1/product/cart">Cart</a></li>
            <li><a href="/api/v1/product/saleRecords">Sale Records</a></li>
            <li><h1>${sessionScope.username}</h1></li>
            <li><a href="/api/v1/auth/logout"><h1>Logout</h1></a></li>
        </ul>
    </nav>
</div>

<div class = "productList">
    <table>
        <caption><h1>Product List</h1></caption>
        <h2 style="color: red; text-align: right">${error}</h2>
        <thead>
        <th>ID</th>
        <th>Name</th>
        <th>Price</th>
        <th>In Stock</th>
        <th>Manage</th>
        <th>AddToCart</th>
        </thead>
        <tbody>
        <c:forEach items="${products}" var="product">
            <tr>
                <td>${product.id}</td>
                <td>${product.name}</td>
                <td>${product.price}</td>
                <td>${product.stockQuantity}</td>
                <td><button><a href="/api/v1/product/update?id=${product.id}">Update</a></button></td>
                <td><form action="/api/v1/product/cart/add?id=${product.id}" method="post">
                    <input type="number" name="quantity" placeholder="Enter Quantity" required>
                    <button type="submit">Add</button>
                </form></td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</div>

</body>
</html>
