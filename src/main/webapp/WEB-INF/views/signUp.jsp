<%--
  Created by IntelliJ IDEA.
  User: gijoongjang
  Date: 2022-03-03
  Time: 오후 3:17
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>회원가입</title>
    <script type="text/javascript">
        function fn_submit(){
            let data = {
                id : $('#id').val(),
                password : $('#password').val(),
                name : $('#name').val()
            }
            $.ajax({
                url:"/signUp",
                type:"POST",
                contentType:"application/json",
                data:JSON.stringify(data)
            }).then(function(result) {
                if(result.message){
                    alert("회원가입 성공!")
                    location.href = "/index"
                } else {
                    alert(result.errMessage.join("\n"));
                }
            });
        }

        function fn_icChk(){
            let id = $('#id').val();

            if(id === "") {
                alert("아이디를 입력하세요.")
                return false;
            }

            $.ajax({
                url:"/idCheck",
                type:"POST",
                contentType:"application/json",
                data:id
            }).then(function(count){
                if(count > 0) {
                    alert("아이디가 이미 사용중입니다.");
                    $("#sign").prop("disabled", true);
                } else {
                    alert("사용가능한 아이디입니다.");
                    $("#sign").prop("disabled", false);
                }
            })
        }
    </script>
</head>
<body>
<%@ include file="bootstrap.jsp" %>
<div class="container">
    <h1>회원가입</h1>
<%--    <form action="/signUp" method="post">--%>
        <div class="form-group">
            <label for="id">아이디</label>
            <input type="text" class="form-control" id="id" name="id">
            <button type="button" class="btn btn-primary" onclick="fn_icChk()">아이디 확인</button>
        </div>
        <div class="form-group">
            <label for="password">비밀번호</label>
            <input type="password" class="form-control" id="password" name="password">
        </div>
        <div class="form-group">
            <label for="name">이름</label>
            <input type="text" class="form-control" id="name" name="name">
        </div>
        <button id="sign" class="btn btn-primary" onclick="fn_submit()">가입</button>
<%--    </form>--%>
</div>
</body>
</html>
