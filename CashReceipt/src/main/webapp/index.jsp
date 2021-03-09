<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="prefix" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<a href="/shopping/buy">Buy Product</a>
<%--    <a href="/pages/chooseCard.jsp">Choose card</a>--%>
<%--    <a href="/pages/printCheck.jsp">Print check</a>--%>


<head>
    <title>Purchases</title>
</head>
<body>
<table>
    <thead>
    <tr>
        <th>id</th>
        <th>name</th>
        <th>price</th>
        <th>number</th>
        <th>discountQ</th>
        <th>cost</th>

    </tr>
    </thead>
    <tbody>

    <prefix:forEach var="purchase" items="${purchases}">
        <tr>
            <td>${purchase.product.id}</td>
            <td>${purchase.product.name}</td>
            <td>${purchase.product.price}</td>
            <td>${purchase.number}</td>
            <td>${purchase.product.discountForQuantity}</td>
            <td>${purchase.getCost()}</td>

        </tr>
    </prefix:forEach>
    </tbody>
</table>


</body>
</html>
