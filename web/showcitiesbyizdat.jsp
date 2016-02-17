<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Yevhen
  Date: 25.12.2015
  Time: 17:39
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Города</title>
</head>
<body>
    <h1>Издательство ${iz.nazvanie}</h1>
    <table border="1">
        <thead>
        <th>Города</th>
        </thead>
        <tbody>
        <c:forEach var="city" items="${iz.cities}">
        <tr>
            <td>${city.name}</td>
        </tr>
        </c:forEach>
        </tbody>
    </table>
    <a href="izdat?addcitybyizdat_id=${iz.id}">Добавить город</a>
</body>
</html>
