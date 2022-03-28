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
    <title>title</title>
    <link rel="stylesheet" type="text/css" href="/resources/css/style.css">
    <script src="//code.jquery.com/jquery.min.js"></script>
    <script type="text/javascript">
        let message = '${message}';
        if(message === "success") {
            alert("삭제완료!")
            location.href="/boardList";
        } else if(message === "fail") {
            console.log("삭제실패!!");
        }

        $(function () {
            let data = JSON.parse('${files}');
            let str = "";

            data.forEach(function (e, i){
                console.log(e)

                if(e.filetype.toUpperCase().includes('PNG') || e.filetype.toUpperCase().includes('JPG')) {
                    str += "<li>"
                    str +=    "<img src = '/imageDisplay?filePath=" + encodeURI(e.upload_path.replace(/--/g, "\\")) + "' class='image'>" + e.original_filename
                    str += "</li>";
                } else {
                    str += "<li>"
                    str +=    "<a href='/fileDownload?filePath="+ encodeURI(e.upload_path.replace(/--/g, "\\")) +"'>"
                    str +=    "<img src = '/resources/img/file.png'>" + e.filename
                    str +=    "</a>"
                    str += "</li>"
                }
            })

            $('.fileList ul').append(str);

            $('.image').on("click", function (e){
                let newTab = window.open();
                let data = e.target.src;
                setTimeout(function(){
                    newTab.document.body.innerHTML = "<img src = '"+ data +"'>";
                }, 500);
            });
        });
    </script>
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
        <div class="fileList">
            <ul>

            </ul>
        </div>
        <br/>
        <button type="button" class="btn btn-info" onclick="location.href='/boardList'">뒤로가기</button>
        <button type="button" id="modify" class="btn btn-success" onclick="location.href='boardModifyForm?no=${boardVO.no}'">수정하기</button>
        <button type="button" id="delete" class="btn btn-danger" onclick="location.href='boardDelete?no=${boardVO.no}'">삭제하기</button>
    </div>
</body>
</html>
