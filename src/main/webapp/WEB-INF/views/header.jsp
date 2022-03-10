<%--
  Created by IntelliJ IDEA.
  User: gijoongjang
  Date: 2022-03-08
  Time: 오후 2:55
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<div id="header">
    <ul>
        <sec:authorize access="isAuthenticated()">
            <li>
                <a href="/logout">로그아웃</a>
            </li>
            <li>
                <sec:authentication property="principal.name"/>님 환영합니다.
            </li>
        </sec:authorize>
        <sec:authorize access="!isAuthenticated()">
            <li>
                <a href="/login">로그인</a>
            </li>
            <li>
                <a href="/signUpForm">회원가입</a>
            </li>
        </sec:authorize>
    </ul>
</div>
