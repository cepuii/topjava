<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<jsp:useBean id="meals" scope="request" type="java.util.List<ru.javawebinar.topjava.model.MealTo>"/>
<html>
<head>
    <title>Meals</title>
</head>
<body>
<table border=1>
    <thead>
    <tr>
        <th>Id</th>
        <th>Description</th>
        <th>Date</th>
        <th>Calories</th>
        <th colspan=2>Action</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach items="${meals}" var="meals">
    
        <tr style="color: ${meals.excess ? 'red' : 'green'}">
            <td><c:out value="${meals.id}" /></td>
            <td><c:out value="${meals.description}" /></td>
            <td><fmt:parseDate value="${meals.dateTime}" pattern="yyyy-MM-dd'T'HH:mm" var="parsedDateTime" type="both" />
                    <fmt:formatDate pattern="dd.MM.yyyy HH:mm" value="${parsedDateTime}" />
            <td><c:out value="${meals.calories}" /></td>
            <td><a href="meals?action=edit&mealId=<c:out value="${meals.id}"/>">Update</a></td>
            <td><a href="meals?action=delete&mealId=<c:out value="${meals.id}"/>">Delete</a></td>
        </tr>
    </c:forEach>
    </tbody>
</table>
<p><a href="editMeal.jsp">Add Meal</a></p>
</body>
</html>
