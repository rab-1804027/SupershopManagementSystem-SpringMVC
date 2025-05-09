<%--
  Created by IntelliJ IDEA.
  User: bappi
  Date: 5/9/25
  Time: 6:33 PM
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

    .saleRecords {
      margin: 40px auto;
      width: 90%;
      font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
      background-color: #f4f6f8;
    }

    .saleRecords h1 {
      text-align: center;
      margin-bottom: 20px;
      color: #333;
    }

    /* Table base */
    .saleRecords table {
      width: 100%;
      border-collapse: collapse;
      background-color: #fff;
      border-radius: 8px;
      overflow: hidden;
      box-shadow: 0 2px 10px rgba(0, 0, 0, 0.05);
    }

    .saleRecords thead {
      background-color: #28a745;
      color: white;
    }

    .saleRecords th, .saleRecords td {
      padding: 12px 15px;
      text-align: left;
      border-bottom: 1px solid #ddd;
    }

    .saleRecords tbody tr:hover {
      background-color: #f1f1f1;
    }

    /* Button styles */
    .saleRecords button {
      background-color: #007BFF;
      border: none;
      padding: 6px 10px;
      border-radius: 4px;
      cursor: pointer;
      margin-right: 5px;
      transition: background-color 0.3s ease;
    }

    .saleRecords button a {
      color: white;
      text-decoration: none;
      font-weight: bold;
    }

    /* Hover effect */
    .saleRecords button:hover {
      background-color: #0056b3;
    }

    /* Optional: Delete button style */
    .saleRecords td button:nth-child(2) {
      background-color: #dc3545;
    }

    .saleRecords td button:nth-child(2):hover {
      background-color: #a71d2a;
    }
  </style>
  <title>Sale Records</title>
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

<div class = "saleRecords">
  <table>
    <caption><h1>Sale Records</h1></caption>
    <thead>
    <th>Id</th>
    <th>Total Price</th>
    <th>Time</th>
    <th>Invoice</th>
    </thead>
    <tbody>
    <c:forEach items="${saleRecords}" var="sale">
      <tr>
        <td>${sale.id}</td>
        <td>${sale.totalPrice}</td>
        <td>${sale.saleTime}</td>
        <td>
          <form action="/getInvoicePdf" method="post" target="_blank">
            <input type="hidden" name="saleId" value="${sale.id}">
            <button>GetInvoicePdf</button>
          </form>
        </td>
      </tr>
    </c:forEach>
    </tbody>
  </table>
</div>

</body>
</html>

