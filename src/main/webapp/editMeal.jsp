<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:useBean id="meal" scope="request" class="ru.javawebinar.topjava.model.Meal"/>
<html>
<head>
    <title>Add new user</title>
</head>
<body>
<h3><a href="index.html">Home</a></h3>
<hr>
<h2 style="margin-top: 20px; margin-left: 100px"> Edit meal</h2>
<div style="margin-top: 20px; margin-left: 20px">
    <form method="POST" action="meals?action=submit&mealId=${meal.id}">
        <label> Date :
            <input type="datetime-local" name="date" value="${meal.dateTime}"/> <br/>
        </label> <br>
        <label> Description :
            <input type="text" name="description" value="${meal.description}"/><br/>
        </label> <br>
        <label> Calories :
            <input type="number" name="calories" value="${meal.calories}"/> <br/>
        </label> <br>
        <input type="submit" value="Submit"/>
        <input type="reset" value="Cancel"/>
    </form>
</div>
</body>
</html>