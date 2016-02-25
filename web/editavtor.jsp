<%--
  Created by IntelliJ IDEA.
  User: Yevhen
  Date: 17.02.2016
  Time: 17:26
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Изменить автора</title>
    <link href="style.css" rel="stylesheet">
</head>
<body>
    <form action="avtors" method="post">
        <input type="hidden" name="edit_aid" value="${avtor.id}">
        <input type="text" name="avtorname" value="${avtor.name}"><br/>
        <input type="text" name="avtorcomment" value="${avtor.comment}"><br/>
        <input type="submit" value="Готово">
    </form>
</body>
</html>
