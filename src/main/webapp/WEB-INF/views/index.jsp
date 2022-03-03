<%--
  Created by IntelliJ IDEA.
  User: gijoongjang
  Date: 2022-02-16
  Time: 오후 4:22
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
    <title>main</title>
</head>
<body>
<%@ include file="bootstrap.jsp" %>
<c:if test="${user == null}">
    <div class="container">
        <form action="/index" method="post">
            <div class="form-group">
                <label for="id">아이디</label>
                <input type="text" id="id" class="form-control">
            </div>
            <div class="form-group">
                <label for="password">비밀번호</label>
                <input type="text" id="password" class="form-control">
            </div>
            <button type="submit" class="btn btn-primary">로그인</button>
            <button type="button" class="btn btn-primary" onclick="location.href='/signUpForm'">회원가입</button>
        </form>
    </div>
</c:if>
<c:if test="${user != null}">
    <button type="button" onclick="location.href='/boardList'">게시판목록</button>
</c:if>
</body>
</html>
