<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="prefix" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Users</title>
</head>
<body>
<table>
    <thead>
    <tr>
        <th>Element</th>
    </tr>
    </thead>
    <tbody>
    <prefix:forEach var="element" items="${elements}">
        <tr>
            <td>${element}</td>
        </tr>
    </prefix:forEach>

    </tbody>
    <a href="/index.jsp">Create</a>
</table>
</body>
</html>
