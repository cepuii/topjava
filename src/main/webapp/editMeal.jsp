<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:useBean id="meal" scope="request" class="ru.javawebinar.topjava.model.Meal"/>
<html>
<head>
    <title>Add new user</title>
</head>
<body>
<form method="POST" action="meals?action=listMeals">
    <label> Date : <input
            type="datetime-local" name="date"
            value="${meal.dateTime}"/> <br/>
    </label>
    <label> Description : <input
            type="text" name="description"
            value="${meal.description}"/><br/>
    </label>
    <label> Calories : <input
            type="number" name="calories"
            value="${meal.calories}"/> <br/>
    </label>
    <input type="submit" value="Submit"/>
</form>
</body>
</html>