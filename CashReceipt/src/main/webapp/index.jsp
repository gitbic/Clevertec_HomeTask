<%--<%@ page contentType="text/html;charset=UTF-8" language="java" %>--%>
<%--<%@ taglib prefix="prefix" uri="http://java.sun.com/jsp/jstl/core" %>--%>
<%--<html>--%>
<%--<head>--%>
<%--    <title>Create Users</title>--%>
<%--</head>--%>
<%--<body>--%>
<%--<table>--%>
<%--    <thead>--%>
<%--    <tr>--%>
<%--        <th>productName</th>--%>
<%--        <th>productNumber</th>--%>
<%--    </tr>--%>
<%--    </thead>--%>
<%--    <tbody>--%>
<%--    <form action="/shopping/buy" method="GET">--%>
<%--        <tr>--%>
<%--            <td>--%>
<%--                <select name="productName">--%>
<%--                    <option>Пункт 1</option>--%>
<%--                    <option>Пункт 2</option>--%>
<%--                </select>--%>
<%--            </td>--%>

<%--            <td><input type="text" name="productNumber" placeholder="productNumber"></td>--%>
<%--            <td><input type="submit" value="Create"></td>--%>
<%--        </tr>--%>

<%--        ${pageContext.request.contextPath}--%>
<%--    </form>--%>
<%--    </tbody>--%>

<%--</table>--%>
<%--</body>--%>
<%--</html>--%>


<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="prefix" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Purchases</title>
</head>
<body>
<table>
    <thead>
    <tr>
        <th>Element</th>
    </tr>
    </thead>
    <tbody>
<%--    <form action="/shopping/main" method="GET">--%>
        <prefix:forEach var="element" items="${elements}">
        <tr>
            <td>${element}</td>
        </tr>
        </prefix:forEach>
<%--        ${pageContext.request.contextPath}--%>
    </tbody>
    <div>
<%--        <a href="/pages/buyProduct.jsp">Byu Product</a>--%>
        <a href="/shopping/buy">Byu Product</a>
        <a href="/pages/chooseCard.jsp">Choose card</a>
        <a href="/pages/printCheck.jsp">Print check</a>
    </div>
</table>
</body>
</html>
