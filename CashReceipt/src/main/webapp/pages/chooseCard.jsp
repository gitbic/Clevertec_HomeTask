<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="prefix" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Choose card</title>
</head>
<body>
<table>
    <thead>
    <tr>
        <th>card</th>
    </tr>
    </thead>
    <tbody>

    <form action="/shopping/purchase" method="GET">
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
