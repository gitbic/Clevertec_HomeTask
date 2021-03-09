<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="prefix" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<a href="/shopping/buy">Buy Product</a>
<a href="/shopping/card">Choose card</a>
<%--<a href="/shopping/pdf">Print check</a>--%>


<head>
    <title>Purchases</title>
</head>
<body>

<prefix:if test="${purchases.size() > 0}">
    <p>Discount card number: ${discountCard.number} discount: ${discountCard.discount}%</p>
</prefix:if>
<table>
    <thead>

    <tr>
        <th>id</th>
        <th>name</th>
        <th>price</th>
        <th>number</th>
        <th>cost</th>
        <th>discount</th>

    </tr>
    </thead>
    <tbody>

    <prefix:forEach var="purchase" items="${purchases}">
        <tr>
            <td>${purchase.product.id}</td>
            <td>${purchase.product.name}</td>
            <td>${purchase.product.price}</td>
            <td>${purchase.number}</td>
            <td>${purchase.getCost()}</td>

            <prefix:if test="${purchase.product.discountForQuantity}">
                <prefix:if test="${purchase.number >= quantityForDiscount}">
                    <td>${discountForProduct}%</td>
                </prefix:if>
            </prefix:if>


        </tr>
    </prefix:forEach>
    </tbody>
</table>

<p></p>
<prefix:if test="${purchases.size() > 0}">
    <p><b>Total cost</b> ${totalCost}</p>
    <p><b>Discount card</b> ${discountCard.discount}%</p>
    <p><b>Final cost</b> ${finalCost}</p>
</prefix:if>

</body>
</html>
