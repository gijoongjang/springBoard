<%--
  Created by IntelliJ IDEA.
  User: gijoongjang
  Date: 2022-04-01
  Time: 오전 10:28
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
    <title>회원목록</title>
    <link rel="stylesheet" type="text/css" href="/resources/css/style.css">
    <script>
        function fn_userDelBtn(id) {
            $.ajax({
                url:"/admin/userDelete",
                type:"POST",
                data: { id : id }
            }).then(function(result) {
                if(result.succeed) {
                    alert("탈퇴 처리 완료!")
                    location.reload()
                } else {
                    console.log("탈퇴 처리 실패")
                }
            });
        }
    </script>
</head>
<body>
<%@ include file="bootstrap.jsp" %>
<div class="container">
    <div class="header">
        <c:import url="/WEB-INF/views/header.jsp"></c:import>
    </div>
    <br/>
    <div class="content">
        <h2>회원목록</h2>
        <br/>
        <table class="table table-hover">
            <tr>
                <th>이름</th>
                <th>아이디</th>
                <th>등록일</th>
                <th></th>
            </tr>
            <c:forEach var="userList" items="${userList}">
                <tr>
                    <td>${userList.name}</td>
                    <td>${userList.id}</td>
                    <td><fmt:formatDate value ="${userList.regdate}" pattern="yyyy-MM-dd HH:mm"/></td>
                    <td><button type="button" class="btn btn-danger" onclick="fn_userDelBtn('${userList.id}')">강제탈퇴</button></td>
                </tr>
            </c:forEach>
        </table>
    </div>
    <div id="footer">
        <c:import url="/WEB-INF/views/footer.jsp"></c:import>
    </div>
</div>
</body>
</html>
