<%@ page language="java" contentType="text/html; charset=EUC-KR" pageEncoding="EUC-KR"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<jsp:useBean id="meal" scope="request" class="ru.javawebinar.topjava.model.Meal"/>
<!DOCTYPE html>
<html>
<head>

    <title>Add new user</title>
</head>
<body>


<form method="POST" action='meals'>
    ID : <input type="text" readonly="readonly" name="mealid"
                     value="<c:out value="${meal.id}" />" /> <br />
    Description :  <input
        type="text" name="description"
        value="<c:out value="${meal.description}" />" /> <br />
    Date :
    <input
            type="datetime-local" name="date"
            value="<c:out value="${meal.dateTime}" />" /> <br />

    Calories : <input
        type="number" name="calories"
        value="<c:out value="${meal.calories}" />" /> <br />
    <input type="submit" value="Submit" />
</form>
</body>
</html>