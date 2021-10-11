<%--
  Created by IntelliJ IDEA.
  User: cepui
  Date: 12.10.2021
  Time: 0:10
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:useBean id="meals" scope="request" type="java.util.List<ru.javawebinar.topjava.model.MealTo>"/>
<html lang="ru">
<head>
    <title>Meals</title>
</head>
<body>
    <h3><a href="index.html">Home</a></h3>
    <hr>
    <h2>Meals</h2>
    <table>
        <c:forEach var="meals" items="${meals}">

            <tr bgcolor="${meals.excess ? 'red' : 'green'}">
            <td>
                ${meals.description}
            </td>
            <td>
                ${meals.dateTime}
            </td>
            <td>
                ${meals.calories}
            </td>
                <td>
                    ${meals.excess}
                </td>
            </tr>
        </c:forEach>
    </table>
</body>
</html>
