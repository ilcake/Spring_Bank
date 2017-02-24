<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>${board.title}</title>
<script type="text/javascript" src="js/board.js"></script>
<script type="text/javascript">

function deleteFile(savedfile){
	location.href="deleteFile?savedfile="+savedfile;	
}

</script>
</head>
<body>
	<div>
		<table>
			<tr>
				<td>Title</td>
				<td>${board.title}</td>
			</tr>
			<tr>
				<td>ID</td>
				<td>${board.custid}</td>
			</tr>
			<tr>
				<td>Date</td>
				<td>${board.inputdate}</td>
			</tr>
			<c:if test="${not empty board.originalfile}">
				<tr>
					<td>File</td>
					<td><img width=100 height=100
						src="download?boardnum=${board.boardnum}" />
						${board.originalfile}
						<button style="width: 43px; height: 22px;"
							onclick="location.href='download?boardnum=${board.boardnum}';">다운</button>
						<c:if test="${loginId eq board.custid}">
							<button onclick="deleteFile('${board.savedfile}')">삭제</button>
						</c:if></td>
				</tr>
			</c:if>
			<tr>
				<td colspan="2"><textarea cols="40" rows="7"
						readonly="readonly">${board.content}</textarea></td>
			</tr>
		</table>
		<div class="menu">

			<c:if test="${loginId eq board.custid}">
				<input type="button" value="Edit"
					onclick="location.href='boardUpdate(${board.boardnum});'">
				<input type="button" value="Delete"
					onclick="deleteCheck(${board.boardnum});">
			</c:if>
			<input type="button" value="Return"
				onclick="location.href='listBoard';">
		</div>

		<!-- 댓글달기 영역 -->
		<div>
			<form action="replyWrite" method="POST">
				<table>
					<tr>
						<td><input type="text" name="text" id="text" /></td>
						<td><input type="submit" value="Reply" /></td>
					</tr>
				</table>
				<!-- 현재 게시글 번호 -->
				<input type="hidden" name="boardnum" value="${board.boardnum}" />
			</form>
		</div>

		<!-- 이 게시글에 달린 댓글을 전체출력 -->
		<c:forEach var="reply" items="${replyList}">
			<table style="width: 400px;">
				<tr>
					<td>${ reply.custid }</td>
					<td><span id="replyText${reply.replynum}">${ reply.text }</span></td>
					<td>${ reply.inputdate }</td>
					<c:if test="${ reply.custid eq loginId }">
						<td><span id="replyButton${reply.replynum}"><input
								type="button" value="edit"
								onclick="replyUpdate(${reply.replynum});" /> <input
								type="button" value="delete"
								onclick="replyDelete(${reply.replynum}, ${board.boardnum});" /></span></td>
					</c:if>
			</table>

		</c:forEach>
	</div>
</body>
</html>