<%--
  Created by IntelliJ IDEA.
  User: gijoongjang
  Date: 2022-02-18
  Time: 오전 11:00
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<%@ include file="bootstrap.jsp" %>
<div class="container">
    <h2>게시글 목록</h2>
    <br/>
    <button class="btn btn-primary" onclick="location.href='/boardWrite'">글쓰기</button>
    <br/>
    <table class="table table-hover">
        <tr>
            <th>번호</th>
            <th>제목</th>
            <th>저자</th>
            <th>등록일</th>
        </tr>
        <c:forEach var="list" items="${boardList}">
            <tr>
                <td>${list.no}</td>
                <td>${list.title}</td>
                <td>${list.writer}</td>
                <td><fmt:formatDate value ="${list.regdate}" pattern="yyyy-MM-dd HH:mm"/></td>
            </tr>
        </c:forEach>
    </table>
</div>
</body>
</html>
