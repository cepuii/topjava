<!DOCTYPE html>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<jsp:useBean id="meals" scope="request" type="java.util.List<ru.javawebinar.topjava.model.MealTo>"/>
<html>
<head>
    <title>Meals</title>
</head>
<body>
<table>
    <thead>
    <tr>
        <th>Description</th>
        <th>Date</th>
        <th>Calories</th>
        <th colspan=2>Action</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach items="${meals}" var="meal">
        <tr style="color: ${meals.excess ? 'red' : 'green'}">
            <td>
                    <fmt:parseDate value="${meals.dateTime}" pattern="yyyy-MM-dd'T'HH:mm" var="parsedDateTime"
                                   type="both"/>
                    <fmt:formatDate pattern="yyyy-MM-dd HH:mm" value="${parsedDateTime}"/>
            <td>${meals.description}</td>
            <td>${meals.calories}</td>
            <td><a href="meals?action=edit&mealId=${meals.id}">Update</a></td>
            <td><a href="meals?action=delete&mealId=${meals.id}">Delete</a></td>
        </tr>
    </c:forEach>
    </tbody>
</table>
<p><a href="editMeal.jsp">Add Meal</a></p>
</body>
</html>
