<%--
  Created by IntelliJ IDEA.
  User: gijoongjang
  Date: 2022-03-30
  Time: 오전 9:45
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>deniedPage</title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no" />
    <link rel="stylesheet" type="text/css" href="/resources/css/errorPageStyle.css">
</head>
<body>
<%
    String errorMessage = (String) request.getAttribute("error");
%>
<div class="icon-box animation" >
    <div class="icon-unit animation-target">
        <div class="circle ">
            <div class="exclamation-mark">
                <div class="exclamation-mark-1"></div>
                <div class="exclamation-mark-2"></div>
                <div class="exclamation-mark-3"></div>
                <div class="exclamation-mark-4"></div>
            </div>
        </div>
    </div>
</div>
<div id="container">
    <h1>ERROR : <%= errorMessage %></h1>
    <a href="/">메인으로</a>
</div>
</body>
</html>
