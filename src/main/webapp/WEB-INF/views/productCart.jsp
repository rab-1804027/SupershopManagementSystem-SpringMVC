<%--
  Created by IntelliJ IDEA.
  User: bappi
  Date: 5/9/25
  Time: 3:36 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <style>
        .navbar{
            display: flex;
            align-items: center;
            justify-content: space-between;
            width: 100%;
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


        .productCartList{
            margin: 40px auto;
            width: 90%;
            font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
            background-color: #f4f6f8;
        }

        .productCartList h1 {
            text-align: center;
            margin-bottom: 20px;
            color: #333;
        }

        /* Table base */
        .productCartList table {
            width: 100%;
            border-collapse: collapse;
            background-color: #fff;
            border-radius: 8px;
            overflow: hidden;
            box-shadow: 0 2px 10px rgba(0, 0, 0, 0.05);
        }

        .productCartList thead {
            background-color: #28a745;
            color: white;
        }

        .productCartList th, .productCartList td {
            padding: 12px 15px;
            text-align: left;
            border-bottom: 1px solid #ddd;
        }

        .productCartList tbody tr:hover {
            background-color: #f1f1f1;
        }

        /* Button styles */
        .productCartList button {
            background-color: #007BFF;
            border: none;
            padding: 6px 10px;
            border-radius: 4px;
            cursor: pointer;
            margin-right: 5px;
            transition: background-color 0.3s ease;
        }

        .productCartList button a {
            color: white;
            text-decoration: none;
            font-weight: bold;
        }

        /* Hover effect */
        .productCartList button:hover {
            background-color: #0056b3;
        }

        /* Optional: Delete button style */
        .productCartList td button:nth-child(2) {
            background-color: #dc3545;
        }

        .productCartList td button:nth-child(2):hover {
            background-color: #a71d2a;
        }

        .totalPrice {
            width: 100%;
            text-align: center;
            margin-top: 30px;
            padding: 20px 0;
            background-color: #ffffff;
            box-shadow: 0 2px 8px rgba(0, 0, 0, 0.05);
            border-radius: 8px;
            font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
        }

        .totalPrice h1 {
            color: #28a745;
            margin-bottom: 20px;
        }

        .totalPrice a{
            text-decoration: none;
        }

        .totalPrice button {
            background-color: #28a745;
            color: white;
            padding: 10px 25px;
            font-size: 16px;
            border: none;
            border-radius: 6px;
            cursor: pointer;
            transition: background-color 0.3s ease;
        }

        .totalPrice button:hover {
            background-color: #218838;
        }

    </style>
    <title>Cart</title>
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
            <li><a href="/auth/v1/logout"><h1>Logout</h1></a></li>
        </ul>
    </nav>
</div>

<c:set var="cart" value="${sessionScope.cart}"></c:set>

<div class = "productCartList">
    <table>
        <caption><h1>Selected Product Items</h1></caption>
        <thead>
        <th>ID</th>
        <th>Name</th>
        <th>Unit Price</th>
        <th>Quantity</th>
        <th>Price</th>
        <th>Manage</th>
        </thead>
        <tbody>
        <c:forEach items="${cart.cartItems}" var="cartItem">
            <tr>
                <td>${cartItem.product.id}</td>
                <td>${cartItem.product.name}</td>
                <td>${cartItem.product.price}</td>
                <td>${cartItem.quantity}</td>
                <td>${cartItem.product.price*cartItem.quantity}</td>
                <td>
                    <form action="/api/v1/product/cart/remove?id=${cartItem.product.id}" method="post"><button>Remove</button></form>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</div>

<c:if test="${not empty cart.cartItems}">
    <div class="totalPrice">
        <form id="checkoutForm" action="/api/v1/product/cart/checkout" method="post" target="_blank">
            <h1>Total Price: ${sessionScope.cart.totalPrice}</h1>
            <button type="button" onclick="handleCheckout()">Checkout</button>
        </form>
    </div>
</c:if>

<script>
    async function handleCheckout() {
        try {
            // First, submit the form
            document.getElementById('checkoutForm').submit();

            // Wait a moment to ensure form submission
            await new Promise(resolve => setTimeout(resolve, 0));

            // Redirect to dashboard
            window.location.href = '/dashboard';
        } catch (error) {
            console.error('Error during checkout:', error);
        }
    }
</script>



</body>
</html>

