<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Belogod
  Date: 20.12.2015
  Time: 20:20
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Издательства</title>
</head>
<link href="style.css" rel="stylesheet">
<body>
<h1>Издательства</h1>
<table border="1">
    <thead>
    <th>название</th><th>адрес</th><th>города</th><th></th>
    </thead>
    <tbody>
    <c:forEach var="iz" items="${izdats}">
        <tr>
            <td>${iz.nazvanie}</td><td>${iz.adres}</td><td><a href="izdat?sities_by_izdat=${iz.id}">показать</a></td>

            <td><a href="delete?iz_id=${iz.id}">удалить</a> </td>
        </tr>
    </c:forEach>
    </tbody>
</table>
</body>
</html>
