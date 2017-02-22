<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<script>
	function pagingForSubmit(currentPage) {
		var form = document.getElementById("pagingForm");
		var page = document.getElementById("page");

		page.value = currentPage;
		form.submit();
	}
</script>
<meta charset="UTF-8">
<style type="text/css">
a {
	text-decoration: none;
}

a:hover {
	color: green;
}
</style>
<title>게시판</title>
</head>
<body>
	<div class="wrapper">

		<h2>[ Board List ]</h2>


		<span>전체 글 수 : ${ navi.totalRecordsCount }</span>&nbsp; <a
			href="board/write">Write</a>

		<!-- 서버측으로 검색 데이터 전송 -->
		<div>
			<form id="pagingForm" action="listBoard" method="get">
				<input type="hidden" id="page" name="page"
					value="${navi.currentPage}" /> <select name="searchTitle">
					<option value="title" ${searchTitle == 'title' ? 'checked' : ''}>제목</option>
					<option value="custid" ${searchTitle == 'custid' ? 'checked' : ''}>작성자</option>
					<option value="content"
						${searchTitle == 'content' ? 'checked' : ''}>내용</option>
				</select> <input type="text" name="searchText" value="${searchText}" /> <input
					type="button" onclick="pagingForSubmit(1)" value="Search" />
			</form>
		</div>


		<!-- 게시판 -->
		<table class="table" border="1">
			<tr>
				<td>No</td>
				<td>Title</td>
				<td>Writer</td>
				<td>Dates</td>
				<td>hits</td>
			</tr>
			<c:forEach var="board" items="${ listBoard }">
				<tr>
					<td>${board.boardnum}</td>
					<td><a href="board?boardnum=${board.boardnum}">${board.title}</a></td>
					<td>${board.custid}</td>
					<td>${board.inputdate}</td>
					<td>${board.hits}</td>
				</tr>
			</c:forEach>
		</table>

		<%-- <!-- 페이징 -->
		<div>
			<c:if test="${ navi.currentPage != 1  }">
				<a href="listBoard?page=${ navi.currentPage-1 }">&lt;</a>
			</c:if>
			<c:forEach var="pgNum" begin="${ navi.startPageGroup }"
				end="${ navi.endPageGroup }" step="1">
				<c:if test="${ pgNum == navi.currentPage }">
					<span style="color: red;">${ pgNum }</span>
				</c:if>
				<c:if test="${ pgNum != navi.currentPage }">
					<a href="listBoard?page=${ pgNum }">${ pgNum }</a>
				</c:if>
			</c:forEach>
			<c:if test="${ navi.currentPage < navi.totalPageCount  }">
				<a href="listBoard?page=${ navi.currentPage+1 }">&gt;</a>
			</c:if>
		</div> --%>

		<!-- 페이지 이동 -->
		<!-- 앞페이지 이동 -->
		<a
			href="javascript:pagingForSubmit(${navi.currentPage-navi.pagePerGroup});">
			◁◁ </a>&nbsp; <a
			href="javascript:pagingForSubmit(${navi.currentPage-1});"> ◀ </a>&nbsp;
		<!-- 페이지 수만큼 출력 -->
		<c:forEach var="counter" begin="${navi.startPageGroup}"
			end="${navi.endPageGroup}">
			<c:choose>
				<c:when test="${ counter == navi.currentPage}">
					<span style="color: red;">${counter}</span>
				</c:when>
				<c:otherwise>
					<a href="javascript:pagingForSubmit(${ counter })">${counter}</a>
				</c:otherwise>
			</c:choose>
		</c:forEach>
		<!-- 뒷페이지 이동 -->
		<a href="javascript:pagingForSubmit(${navi.currentPage+1});"> ▶ </a>&nbsp;
		<a
			href="javascript:pagingForSubmit(${navi.currentPage+navi.pagePerGroup});">
			▷▷ </a>
	</div>
	<!-- end .wrapper -->

</body>
</html>