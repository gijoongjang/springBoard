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
    <link rel="stylesheet" type="text/css" href="/resources/css/style.css">
</head>
<body>
<%@ include file="bootstrap.jsp" %>
    <div class="header">
        <c:import url="/WEB-INF/views/header.jsp"></c:import>
    </div>
    <div id="navigation">
        <c:import url="/WEB-INF/views/navigation.jsp"></c:import>
    </div>
    <div id="content">

    </div>
    <div id="footer">
        <c:import url="/WEB-INF/views/footer.jsp"></c:import>
    </div>
</body>
</html>
