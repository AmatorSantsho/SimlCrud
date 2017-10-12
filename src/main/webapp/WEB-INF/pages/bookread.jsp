<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ page session="false" %>

<html>
<head>
    <title>BookReading</title>
    <link href="<c:url value="/resources/CSS/style.css" />" type="text/css" rel="stylesheet"/>
</head>
<a href="/books"> вернуться на Главную страницу</a>
<br>
<br>
<p class="big">Название книги:&nbsp;${book.title}</p>
<p class="big"> Краткое описание книги:&nbsp;${book.description}</p>
<p class="big">Автор книги:&nbsp;${book.author}</p>
<p class="big">ISBN:&nbsp;${book.isbn}</p>
<p class="big">Год издания:&nbsp;${book.printYear}</p>
<br>
<p>Текст книги будет здесь...</p>
</body>
</html>
