<%--
  Created by IntelliJ IDEA.
  User: gijoongjang
  Date: 2022-03-02
  Time: 오전 9:59
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>게시글 수정</title>
    <script type="text/javascript">
        function fn_click() {
            let data = {
                title : $('#title').val(),
                content : $('#content').val()
            }

            $.ajax({
                type:"POST",
                contentType:"application/json; charset=UTF-8",
                url:"/boardModify",
                data:JSON.stringify(data),
                success : function () {
                    alert("수정이 완료되었습니다.");
                    location.href="/boardList";
                },
                error : function (xhr, status, err) {
                    console.log("수정 실패!!\n" + "error :" + status + err);
                }
            })
        }
    </script>
</head>
<body>
<%@ include file="bootstrap.jsp" %>
<div class="container">
    <h2>게시글 수정</h2>
    <br/>
    <form action="/boardModify" method="post">
        <div class="form-group">
            <label for="no">번호</label>
            <input type="text" class="form-control" id="no" name="no" value="${modifyData.no}" readonly>
        </div>
        <div class="form-group">
            <label for="writer">작성자</label>
            <input type="text" class="form-control" id="writer" name="writer" value="${modifyData.writer}" readonly>
        </div>
        <div class="form-group">
            <label for="title">제목</label>
            <input type="text" class="form-control" id="title" name="title" value="${modifyData.title}">
        </div>
        <div class="form-group">
            <label for="content">내용</label>
            <textarea class="form-control" id="content" name="content" rows="3">${modifyData.content}</textarea>
        </div>
        <input type="hidden" name="no" value="${modifyData.no}"/>
        <button type="submit" class="btn btn-primary" onclick="fn_click()">수정</button>
    </form>
</div>
</body>
</html>
