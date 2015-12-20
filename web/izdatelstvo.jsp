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
    <title>Издательства книги</title>
</head>
<body>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Книги по автору</title>
</head>
<body>
<h1>Автор: ${avtor.name}</h1>
<table border="1">
    <thead>
    <th>название</th><th>стр</th><th>издательство</th>
    </thead>
    <tbody>
    <c:forEach var="book" items="${avtor.books}">
        <tr>
            <td>${izdatelstvo.nazvanie}</td><td>${izdatelstvo.adres}</td>
        </tr>
    </c:forEach>
    </tbody>
</table>
</body>
</html>

</body>
</html>
