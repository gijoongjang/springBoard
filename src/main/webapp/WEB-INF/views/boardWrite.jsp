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
    <script src="//code.jquery.com/jquery.min.js"></script>
    <style>
        .delBtn {
            float:right;
        }
    </style>
    <script type="text/javascript">
        let limitedFileCnt = 1;

        $(function () {
            $('#fileAdd').on('click', function (){
                if(limitedFileCnt >= 3){
                    alert("파일 업로드 최대 개수는 3개입니다.")
                    return;
                } else {
                    $('.add').append('<li><input type="file" name="file_path" class="files"><button type="button" class="delBtn" onclick="fn_addDel(this);">삭제</button></li>')
                    limitedFileCnt++;
                }
            });
        });

        function fn_addDel(e) {
            $(e).closest('li').remove();
            limitedFileCnt--;
        }

        function fn_Submit(){
            debugger;
            let formData = new FormData();
            let fileInput = $('.files');
            let jsonObj = {
                "writer" : $('#writer').val(),
                "title" : $('#title').val(),
                "content" : $("textarea#content").val()
            }

            //파일 다중 업로드 위해
            for(let i = 0; i < fileInput.length; i++) {
                if(fileInput[i].files.length > 0) {
                    for(let j = 0; j < fileInput[i].files.length; j++) {
                        formData.append('uploadFiles', $('.files')[i].files[j]);
                    }
                }
            }

            formData.append("data", new Blob([JSON.stringify(jsonObj)], {type : "application/json"}));

            $.ajax({
                contentType: false,
                processData: false,
                url: '/boardWrite',
                data: formData,
                type: 'POST',
            }).then(function(result) {
                if (result.succeed) {
                    alert("작성이 완료되었습니다")
                    location.href = "/boardList"
                } else {
                    alert(result.errMessage.join("\n"));
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
    <br/><br/>
    <div class="content">
        <h2>게시글 작성</h2>
        <br/>
<%--        <form action="/boardWrite" method="post" enctype="multipart/form-data">--%>
            <div class="form-group">
                <label for="writer">작성자</label>
                <input type="text" class="form-control" id="writer" name="writer" value="<sec:authentication property="principal.name"/>" readonly>
<%--                <span class="textColor">${valid_writer}</span>--%>
            </div>
            <div class="form-group">
                <label for="title">제목</label>
                <input type="text" class="form-control" id="title" name="title" value="${boardVO.title}" placeholder="제목을 입력하세요.">
<%--                <span class="textColor">${valid_title}</span>--%>
            </div>
            <div class="form-group">
                <label for="content">내용</label>
                <textarea class="form-control" id="content" name="content" rows="3">${boardVO.content}</textarea>
<%--                <span class="textColor">${valid_content}</span>--%>
            </div>
            <div class="form-group">
                <ul class="add">
                    <li>
                        <input type="file" name="file_path" class="files">
                    </li>
                </ul>
            </div>
            <button type="button" id="fileAdd" class="btn btn-primary">파일추가</button>
            <button type="submit" id="write" class="btn btn-primary" onclick="fn_Submit()">작성</button>
            <button type="button" class="btn btn-info" onclick="history.back()">뒤로가기</button>
<%--        </form>--%>
    </div>
    <div id="footer">
        <c:import url="/WEB-INF/views/footer.jsp"></c:import>
    </div>
</div>
</body>
</html>
