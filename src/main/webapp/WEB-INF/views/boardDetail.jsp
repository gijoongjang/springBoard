<%--
  Created by IntelliJ IDEA.
  User: gijoongjang
  Date: 2022-02-25
  Time: 오후 1:21
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<%@ include file="bootstrap.jsp" %>
    <div class="container">
        <h2>게시글 내용</h2>
        <br/>
        <div class="mb-3">
            <label class="form-label">제목</label>
            <input type="text" class="form-control" value="${boardVO.title}" readonly>
        </div>
        <div class="mb-3">
            <label class="form-label">작성자</label>
            <input type="text" class="form-control" value="${boardVO.writer}" readonly>
        </div>
        <div class="mb-3">
            <label class="form-label">내용</label>
            <input type="text" class="form-control" value="${boardVO.content}" readonly>
        </div>
        <div class="mb-3">
            <label class="form-label">등록일</label>
            <input type="text" class="form-control" value="<fmt:formatDate value ="${boardVO.regdate}" pattern="yyyy-MM-dd HH:mm"/>" readonly>
        </div>
        <div class="mb-3">
            <label class="form-label">조회 수</label>
            <input type="text" class="form-control" value="${boardVO.viewno}" readonly>
        </div>
        <br/>
        <button type="button" class="btn btn-info" onclick="location.href='/boardList'">뒤로가기</button>
        <button type="button" class="btn btn-success">수정하기</button>
        <button type="button" class="btn btn-danger">삭제하기</button>
    </div>
</body>
</html>
