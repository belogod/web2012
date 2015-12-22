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
<body>
<h1>Издательства</h1>
<table border="1">
    <thead>
    <th>название</th><th>адрес</th>
    </thead>
    <tbody>
    <c:forEach var="iz" items="${izdats}">
        <tr>
            <td>${iz.nazvanie}</td><td>${iz.adres}</td>
        </tr>
    </c:forEach>
    </tbody>
</table>
</body>
</html>
