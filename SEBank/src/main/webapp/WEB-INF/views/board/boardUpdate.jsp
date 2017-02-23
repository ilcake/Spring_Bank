<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>${board.title}</title>
<script type="text/javascript" src="js/board.js"></script>
</head>
<body>
	<div>
		<table>
			<tr>
				<td>Title</td>
				<td><input type="text" id="title" name="title"
					value="${board.title}"></td>
			</tr>
			<tr>
				<td>ID</td>
				<td>${board.custid}</td>
			</tr>
			<tr>
				<td>Date</td>
				<td>${board.inputdate}</td>
			</tr>
			<tr>
				<td>File</td>
				<td>${board.savedfile}</td>
			</tr>
			<tr>
				<td colspan="2"><textarea name="content" id="content" cols="40"
						rows="7">${board.content}</textarea></td>
			</tr>
		</table>
		<div class="menu">
			<input type="button" value="Delete"
				onclick="deleteCheck(${board.boardnum});"> <input
				type="button" value="Return" onclick="location.href='listBoard';">
		</div>
	</div>
</body>
</html>