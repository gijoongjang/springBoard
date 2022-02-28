<%--
  Created by IntelliJ IDEA.
  User: gijoongjang
  Date: 2022-02-18
  Time: 오전 11:00
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
    <title>Title</title>
    <script type="text/javascript">
        const fixUrl = "${pageContext.request.contextPath}/boardList";

        function fn_prev(page) {
            let url = fixUrl + "?pageNum="+page;
            location.href = url;
        }

        function fn_curPage(page) {
            let url = fixUrl + "?pageNum="+page;
            location.href = url;
        }

        function fn_next(page) {
            let url = fixUrl + "?pageNum="+page;
            location.href = url;
        }
    </script>
</head>
<body>
<%@ include file="bootstrap.jsp" %>
<div class="container">
    <h2>게시글 목록</h2>
    <br/>
    <button class="btn btn-primary" onclick="location.href='/boardWrite'">글쓰기</button>
    <br/>
    <table class="table table-hover">
        <tr>
            <th>번호</th>
            <th>제목</th>
            <th>저자</th>
            <th>등록일</th>
        </tr>
        <c:forEach var="list" items="${boardList}">
            <tr onclick="location.href='/boardDetail?no=${list.no}'">
                <td>${list.no}</td>
                <td>${list.title}</td>
                <td>${list.writer}</td>
                <td><fmt:formatDate value ="${list.regdate}" pattern="yyyy-MM-dd HH:mm"/></td>
            </tr>
        </c:forEach>
    </table>
    <div id="paginationBox">
        <ul class="pagination">
            <c:if test="${pageInfo.previous}">
                <li class="previous_button">
                    <a href="#" onclick="fn_prev('${pageInfo.startPage - 1}')">이전</a>
                </li>
            </c:if>
            <c:forEach begin="${pageInfo.startPage}" end="${pageInfo.endPage}" var="idx">
                <li class="page_button ${pageInfo.criteria.pageNum == idx ? "active" :""}">
                    <a href="#" onclick="fn_curPage('${idx}')">${idx}</a>
                </li>
            </c:forEach>
            <c:if test="${pageInfo.next}">
                <li class="next_button">
                    <a href="#" onclick="fn_next('${pageInfo.endPage + 1}')">다음</a>
                </li>
            </c:if>
        </ul>
    </div>
</div>
</body>
</html>
