<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="prefix" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Buy product</title>
</head>
<body>
<table>
    <thead>
    <tr>
        <th>product</th>
        <th>number</th>
    </tr>
    </thead>
    <tbody>
    <p>Скидка распространяется на помеченные продукты, купленные в количестве <b>${quantityForDiscount}</b> и более штук</p>

        <form action=${URL["CREATE_PURCHASE_URL_PATTERN"]} method="GET">
        <tr>
            <td>
                <select name="productName">
                    <prefix:forEach var="product" items="${products}">
                        <option value="${product.id}">
                                ${product.name} - \$${product.price}
                                    <prefix:if test="${product.discountForQuantity}">
                                        - DISCONT
                                    </prefix:if>
                        </option>
                    </prefix:forEach>
                </select>
            </td>

            <td>
                <select name="productNumber">
                    <prefix:forEach var="i" begin="1" end="10">
                        <option value="${i}">${i}</option>
                    </prefix:forEach>
                </select>
            </td>
            <td><input type="submit" value="buy"></td>
        </tr>
<%--        ${pageContext.request.contextPath}--%>
    </form>
    </tbody>
</table>
</body>
</html>
