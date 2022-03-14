<%--
  Created by IntelliJ IDEA.
  User: gijoongjang
  Date: 2022-02-16
  Time: 오후 4:27
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>게시글작성</title>
    <link rel="stylesheet" type="text/css" href="/resources/css/style.css">
    <script src="http://code.jquery.com/jquery-3.5.1.min.js"></script>
    <script src="/resources/js/boardWrite.js" type="text/javascript"></script>
</head>
<body>
<%@ include file="bootstrap.jsp" %>
<div class="container">
    <div class="header">
        <c:import url="/WEB-INF/views/header.jsp"></c:import>
    </div>
    <br/><br/>
    <div class="content">
        <h2>게시글 작성</h2>
        <br/>
        <form action="/boardWrite" method="post">
            <div class="form-group">
                <label for="writer">작성자</label>
                <input type="text" class="form-control" id="writer" name="writer" value="<sec:authentication property="principal.name"/>" readonly>
<%--                <span class="textColor">${valid_writer}</span>--%>
            </div>
            <div class="form-group">
                <label for="title">제목</label>
                <input type="text" class="form-control" id="title" name="title" value="${boardVO.title}" placeholder="제목을 입력하세요.">
                <span class="textColor">${valid_title}</span>
            </div>
            <div class="form-group">
                <label for="content">내용</label>
                <textarea class="form-control" id="content" name="content" rows="3">${boardVO.content}</textarea>
                <span class="textColor">${valid_content}</span>
            </div>
            <button type="submit" id="write" class="btn btn-primary">작성</button>
            <button type="button" class="btn btn-info" onclick="history.back()">뒤로가기</button>
        </form>
    </div>
    <div id="footer">
        <c:import url="/WEB-INF/views/footer.jsp"></c:import>
    </div>
</div>
</body>
</html>
