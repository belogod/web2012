<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%--
  Created by IntelliJ IDEA.
  User: Belogod
  Date: 20.12.2015
  Time: 18:41
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Все Книги</title>
    <link href="style.css" rel="stylesheet">
</head>
<body>
<table>
    <thead>
    <th>автор</th><th>название</th><th>стр</th><th>издательство</th><th></th>
    </thead>
    <tbody>
    <c:forEach var="book" items="${books}">
        <tr>
            <td>${book.avtor.name}</td>
            <td>${book.nazvanie}</td>
            <td>${book.pages}</td>
            <td>${book.izdatelstvo}</td>
            <td><a href="delete?book_id=${book.id}">удалить</a></td>
        </tr>
    </c:forEach>
    </tbody>
</table>
<a href="books?add=1" title="Новая книга">Добавить</a></body>
</html>
