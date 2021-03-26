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

    <form action=${URL["SETUP_CARD_URL_PATTERN"]} method="GET">
        <tr>
            <td>
                <select name="cardNumber">
                    <prefix:forEach var="card" items="${cards}">
                        <option value="${card.number}">
                                card ${card.number} - discount %${card.discount}
                        </option>
                    </prefix:forEach>
                </select>
            </td>
            <td><input type="submit" value="choose"></td>
        </tr>
    </form>
    </tbody>
</table>
</body>
</html>
