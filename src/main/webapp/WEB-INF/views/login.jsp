<%--
  Created by IntelliJ IDEA.
  User: gijoongjang
  Date: 2022-03-07
  Time: 오후 4:27
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
    <title>Title</title>
</head>
<body>
<%@ include file="bootstrap.jsp" %>
<div class="container">
    <form action="/loginProcess" method="post">
        <div class="form-group">
            <label for="id">아이디</label>
            <input type="text" id="id" name="username" class="form-control">
        </div>
        <div class="form-group">
            <label for="password">비밀번호</label>
            <input type="password" id="password" name="password" class="form-control">
        </div>
        <span>
            <c:if test="${error}">
                <p id="error" class="alert alert-danger">
                    ${exception}
                </p>
            </c:if>
        </span>
        <button type="submit" class="btn btn-primary">로그인</button>
        <button type="button" class="btn btn-primary" onclick="location.href='/signUpForm'">회원가입</button>
    </form>
</div>
</body>
</html>
