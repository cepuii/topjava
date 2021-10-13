<%--suppress HtmlDeprecatedAttribute --%>
<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<jsp:useBean id="meals" scope="request" type="java.util.List<ru.javawebinar.topjava.model.MealTo>"/>
<html>
<head>
    <title>Meals</title>
</head>
<body>
<h3><a href="index.html">Home</a></h3>
<hr>
<h2 style="margin-top: 20px; margin-left: 100px"> Meals list</h2>
<div style="margin-top: 20px; margin-left: 20px">
    <table border="2">
        <colgroup span='5' width="200">
        <thead>
        <tr>
            <th>Date</th>
            <th>Description</th>
            <th>Calories</th>
            <th colspan=2>Action</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${meals}" var="meal">
            <tr style="color: ${meal.excess ? 'red' : 'green'}">
                <td>
                        <fmt:parseDate value="${meal.dateTime}" pattern="yyyy-MM-dd'T'HH:mm" var="parsedDateTime"
                                       type="both"/>
                        <fmt:formatDate pattern="yyyy-MM-dd HH:mm" value="${parsedDateTime}"/>
                <td>${meal.description}</td>
                <td>${meal.calories}</td>
                <td><a href="meals?action=edit&mealId=${meal.id}">Update</a></td>
                <td><a href="meals?action=delete&mealId=${meal.id}">Delete</a></td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</div>
<p><a style="margin-left: 20px" href="editMeal.jsp">Add Meal</a></p>
</body>
</html>
