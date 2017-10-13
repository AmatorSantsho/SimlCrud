<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib prefix="from" uri="http://www.springframework.org/tags/form" %>
<%@ page session="false" %>
<html>
<head>
    <title>Книжная полка</title>
    <link href="<c:url value="/resources/CSS/style.css" />" type="text/css" rel="stylesheet"/>
</head>
<h1>Найти книгу</h1>
<c:url var="findAction" value="/books/find"/>
<form action="${findAction}" method="post">
    <table>
        <tr>
            <td>
                <p1>Название книги</p1>
            </td>
            <td>
                <input type="text" name="title" size="100" maxlength="100"/>
            </td>
        </tr>
        <tr>
            <td>
                <p1>Описание книги</p1>
            </td>
            <td>
                <input type="text" name="" size="100" maxlength="100"/>
            </td>
        </tr>
        <tr>
            <td>
                <p1> Автор книги</p1>
            </td>
            <td>
                <input type="text" name="author" size="100" maxlength="100"/>
            </td>
        </tr>
        <td>
            <input type="submit"
                   value="Найти книгу"/>
        </td>
        </tr>
    </table>
</form>
<c:if test="${findResult==true}">
    <a href="/books"> вернуться на Главную страницу</a>
</c:if>
<h1>Список книг</h1>
</br>
<c:if test="${countBooks==0}">
    <p class="big">По вашему запросу ничего не найдено</p>
</c:if>
<c:if test="${!empty listBooks}">
    <table class="tg">
        <tr>
            <th width="80">ID книги</th>
            <th width="120">Название книги</th>
            <th width="180">Описание книги</th>
            <th width="120">Автор книги</th>
            <th width="80">ISBN</th>
            <th width="80">Год издания</th>
            <th width="120">Книга прочитана</th>
            <th width="60">Edit</th>
            <th width="60">Delete</th>
            <th width="60">Read</th>
        </tr>
        <c:forEach items="${listBooks}" var="book">
            <tr>
                <td>${book.id}</td>
                <td>${book.title}</td>
                <td>${book.description}</td>
                <td>${book.author}</td>
                <td>${book.isbn}</td>
                <td>${book.printYear}</td>
                <td><c:if test="${book.wasRead==true}">
                    <c:out value="Да"/>
                </c:if>
                    <c:if test="${book.wasRead==false}">
                        <c:out value="Нет"/>
                    </c:if>
                    <br>
                    <c:if test="${book.wasRead==false}">
                        <a href="<c:url value='/edit/status/${page}/${book.id}'/>" class="smoll">Изменить на "Да"?</a>
                    </c:if>
                </td>
                <td><a href="<c:url value='/edit/${page}/${book.id}'/>">Edit</a></td>
                <td><a href="<c:url value='/remove/${page}/${book.id}'/>">Delete</a></td>
                <td><a href="/bookread/${book.id}">Read</a></td>
            </tr>
        </c:forEach>
    </table>
</c:if>

<h1>Добавить/Редактировать книгу</h1>
<form:form action="/books/add/${page}" commandName="book">
    <table>
        <c:if test="${!empty book.title}">
            <tr>
                <td>
                    <form:label path="id">
                        <spring:message text="ID"/>
                    </form:label>
                </td>
                <td>
                    <form:input path="id" readonly="true" disabled="true"/>
                    <form:hidden path="id"/>
                </td>
            </tr>
        </c:if>
        <tr>
            <td>
                <form:label path="title">
                    <spring:message text="Название книги"/>
                </form:label>
            </td>
            <td>
                <form:input path="title" size="100" maxlength="100"/>
            </td>
        </tr>
        <tr>
            <td>
                <form:label path="description">
                    <spring:message text="Описание книги"/>
                </form:label>
            </td>
            <td>
                <form:input path="description" size="100" maxlength="255"/>
            </td>
        </tr>
        <tr>
            <td>
                <form:label path="author">
                    <spring:message text="Автор книги"/>
                </form:label>
            </td>
            <td>
                <c:if test="${!empty book.title}">
                    <form:input path="author" readonly="true" disabled="true"/>
                    <form:hidden path="author"/>
                </c:if>
                <c:if test="${empty book.title}">
                    <form:input path="author" size="100" maxlength="100"/>
                </c:if>
            </td>
        </tr>
        <tr>
            <td>
                <form:label path="isbn">
                    <spring:message text="ISBN"/>
                </form:label>
            </td>
            <td>
                <form:input path="isbn" size="100" maxlength="20"/>
            </td>
        </tr>
        <tr>
            <td>
                <form:label path="printYear">
                    <spring:message text="Год издания"/>
                </form:label>
            </td>
            <td>
                <form:input path="printYear" maxlength="4"/>
            </td>
        </tr>
        <tr>
            <td>
                <form:label path="wasRead">
                    <spring:message text="Книга прочитана"/>
                </form:label>
            </td>
            <td>
                <form:checkbox path="wasRead" value="true"/>
            </td>
        </tr>
        <tr>
            <td colspan="2">
                <c:if test="${!empty book.title}">
                    <input type="submit"
                           value="<spring:message text="Заменить издание"/>"/>
                </c:if>
                <c:if test="${empty book.title}">
                    <input type="submit"
                           value="<spring:message text="Добавить книгу"/>"/>
                </c:if>
            </td>
        </tr>
    </table>
</form:form>
<c:if test="${findResult!=true}">
    <div class="pag">
        <c:url value="/books" var="previous">
            <c:param name="page" value="${page-1}"/>
        </c:url>
        <c:if test="${page>1}">
            <a href="<c:out value="${previous}"  />">Предыдущая</a>
        </c:if>
        <c:forEach var="i" begin="1" end="${countPages}">
            <c:choose>
                <c:when test="${page == i}">
                    <span>${i}</span>
                </c:when>
                <c:otherwise>
                    <c:url value="/books" var="currenti">
                        <c:param name="page" value="${i}"/>
                    </c:url>
                    <a href='<c:out value="${currenti}" />'>${i}</a>
                </c:otherwise>
            </c:choose>
        </c:forEach>
        <c:url value="/books" var="next">
            <c:param name="page" value="${page + 1}"/>
        </c:url>
        <c:if test="${page + 1 <= countPages}">
            <a href='<c:out value="${next}" />'>Следущая</a>
        </c:if>
    </div>
</c:if>

</body>
</html>
