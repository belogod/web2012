<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Yevhen
  Date: 17.02.2016
  Time: 18:08
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Добавить город издательства</title>
    <link href="style.css" rel="stylesheet">
</head>
<body>
    <h1>Издательство: ${izdat.nazvanie}</h1>
    <form action="izdat" method="post">
        <input type="hidden" name="addcityforizdat" value="${izdat.id}">
        <select name="city_select">
            <option disabled>Выберите город</option>
            <c:forEach var="city" items="${cities}">
                <option value="${city.id}">${city.name}</option>
            </c:forEach>
        </select>
        <input type="submit" value="Добавить">
    </form>
</body>
</html>
