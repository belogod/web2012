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
    <link href="style.css" rel="stylesheet">


    <link rel="stylesheet" type="text/css" href="style.css">
</head>
<body>
<table>
    <thead>
     <tr>
        <th>Номер п\п</th><th>Имя</th><th>Коментарии</th><th>Книги автора</th><th>Удалить</th>
     </tr>
    </thead>
<tbody>
<c:forEach var="avtor" items="${avtors}">
    <tr>
        <td>${avtor.id}</td><td>${avtor.name}</td><td>${avtor.comment}</td><td><a href="avtors?books_by_aid=${avtor.id}">книги</a></td>
        <td><a href="avtors?edit_aid=${avtor.id}">Изменить</a></td>
    </tr>
</c:forEach>
</tbody>
</table>
<hr/>
<form action="avtors" method="get">
    <input type="hidden" name="avtors_by_comment" value="1">
    <input type="text" name="comment" title="comment">
    <input type="submit" value="Отобрать">
</form>
<hr/>
<form action="avtors" method="post">
    <input type="hidden" name="add_avtor" value="1">
    <label for="name">Имя автора:</label>
    <input type="text" name="name" id="name">
    <label for="comment">Страна:</label>
    <input type="text" name="comment" id="comment">
    <input type="submit" value="Добавить">
</form>

</body>
</html>
