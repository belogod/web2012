<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Yevhen
  Date: 08.02.2016
  Time: 17:16
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Новая книга</title>
</head>
<body>
<form action="books" method="post">
    <input type="hidden" name="add_book" value="1">
    <label for="avtorlist">Автор</label>
    <select name="avtor_select" id="avtorlist">
        <option disabled>Выберите автора</option>
        <option value="-1">Новый автор</option>
    <c:forEach var="avtor" items="${avtors}">
        <option value="${avtor.id}">${avtor.name}</option>
    </c:forEach>
    </select>
    <input type="text" name="altavtor">
    <label for="book_title">Название</label>
    <input type="text" name="book_title" id="book_title">
    <label for="book_pages">Количество страниц</label>
    <input type="number" name="book_pages" id="book_pages" min="1">
    <select name="izdat_select">
        <option disabled>Выберите издательство</option>
        <c:forEach var="izdat" items="${izdats}">
            <option value="${izdat.id}">${izdat.nazvanie}</option>
        </c:forEach>
    </select>
    <input type="submit" value="Ok">
</form>
</body>
</html>
