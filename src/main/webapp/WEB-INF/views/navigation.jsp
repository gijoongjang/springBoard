<%--
  Created by IntelliJ IDEA.
  User: gijoongjang
  Date: 2022-03-10
  Time: 오후 2:40
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<html>
<head>
    <title>nav</title>
</head>
<body>
<%@ include file="bootstrap.jsp" %>
<sec:authorize access="isAuthenticated() and hasRole('ROLE_USER')">
    <div id="navigation">
        <ul class="nav nav-tabs">
            <li>
                <a href="#" onclick="location.href='/boardWriteForm'">게시글 작성</a>
            </li>
            <li>
                <a href="#" onclick="location.href='/boardList'">게시글 목록</a>
            </li>
        </ul>
    </div>
</sec:authorize>
<sec:authorize access="hasRole('ROLE_ADMIN')">
    <div id="navigation">
        <ul class="nav nav-tabs">
            <li>
                <a href="#" onclick="location.href='/admin/userList'">회원목록</a>
            </li>
        </ul>
    </div>
</sec:authorize>
</body>
</html>
