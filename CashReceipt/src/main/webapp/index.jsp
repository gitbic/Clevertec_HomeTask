<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="prefix" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<a href=${URL["BUY_PRODUCT_URL_PATTERN"]}>Buy Product</a>
<a href=${URL["CHOOSE_CARD_URL_PATTERN"]}>Choose card</a>


<head>
    <title>Purchases</title>
</head>
<body>

<p>Discount card number: ${discountCard.number} discount: ${discountCard.discount}%</p>
<p>-------------------------------------------------------</p>
<table>
    <thead>
    <tr>
        <th>id</th>
        <th>name</th>
        <th>price</th>
        <th>number</th>
        <th>cost</th>
        <th>discount</th>
        <th>delete</th>

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
            <td>
            <prefix:if test="${purchase.product.discountForQuantity}">
                <prefix:if test="${purchase.number >= quantityForDiscount}">
                    ${discountForProduct}%
                </prefix:if>
            </prefix:if>
            </td>
            <form action=${URL["DELETE_PURCHASE_URL_PATTERN"]} method="GET">
                <td><input type="submit" value="delete"></td>
                <input type="hidden" name="deletePurchase" value="${purchase.product.id}">
            </form>


        </tr>
    </prefix:forEach>
    </tbody>

</table>
<p>-------------------------------------------------------</p>
<p></p>
<prefix:if test="${purchases.size() > 0}">
    <p><b>Total cost</b> ${totalCost}</p>
    <p><b>Discount card</b> ${discountCard.discount}%</p>
    <p><b>Final cost</b> ${finalCost}</p>
</prefix:if>

</body>
</html>
