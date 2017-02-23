<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Write</title>
<script type="text/javascript" src="js/board.js"></script>
</head>
<body>
	<div>
		<h2>[ Write ]</h2>
		<form action="write" method="POST" onsubmit="return formCheck();">
			<table>
				<tr>
					<td>Title</td>
					<td><input type="text" name="title" id="title" /></td>
				</tr>
				<tr>
					<td>Content</td>
					<td><textArea name="content" id="content"
							style="width: 400px; height: 200px;"></textArea></td>
				</tr>
				<!-- 
				<tr>
					<td>File</td>
					<td><input type="file" name="upload" id="upload" /></td>
				</tr> 
				-->
				<tr>
					<td colspan="2"><input type="submit" value="Write" /></td>
				</tr>
			</table>
		</form>
	</div>
</body>
</html>