<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>${board.title}</title>
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
			<tr>
				<td>File</td>
				<td>${board.savedfile}</td>
			</tr>
			<tr>
				<td colspan="2"><textarea cols="20" rows="5">${board.content}</textarea></td>
			</tr>
			<tr>
				<td colspan="2">
					<table>
						<tr>
							<td><input type="button" value="Edit"></td>
							<td><input type="button" value="Delete"></td>
							<td><input type="button" value="Return"
								onclick="history.go(-1);"></td>
						</tr>
					</table>
				</td>
			</tr>
		</table>
	</div>
</body>
</html>