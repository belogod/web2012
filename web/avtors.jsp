<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Belogod
  Date: 20.12.2015
  Time: 18:40
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<table border="1">
    <thead>
    <th></th><th></th><th></th><th></th>
        </thead>
<tbody>
<c:forEach var="avtor" items="${avtors}">
    <tr>
        <td>${avtor.id}</td><td>${avtor.name}</td><td>${avtor.comment}</td><td><a href="avtors?books_by_aid=${avtor.id}">книги</a></td>
    </tr>
</c:forEach>
</tbody>
</table>
</body>
</html>
