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
        <th>productName</th>
        <th>productNumber</th>
    </tr>
    </thead>
    <tbody>
    <form action="/shopping/purchase" method="GET">
        <tr>
            <td>
                <select name="productName">
                    <prefix:forEach var="product" items="${products}">
                        <option value="${product.id}">${product.name} - \$${product.price}</option>
                    </prefix:forEach>
                </select>
            </td>
            <td><input type="text" name="productNumber" placeholder="productNumber"></td>
            <td><input type="submit" value="buy"></td>
        </tr>
<%--        ${pageContext.request.contextPath}--%>
    </form>
    </tbody>
</table>
</body>
</html>
