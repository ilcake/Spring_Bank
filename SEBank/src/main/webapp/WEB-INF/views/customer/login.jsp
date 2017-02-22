<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>로그인</title>
</head>
<body>
	<c:if test="${not empty errorMsg }">
		<script>alert('${ errorMsg }');
		</script>
	</c:if>

	<h1>[ 로그인 ]</h1>
	<div>
		<form action="login" method="post">
			<table>
				<tr>
					<td>ID</td>
					<td><input type="text" name="custid"></td>
				</tr>
				<tr>
					<td>Password</td>
					<td><input type="password" name="password"></td>
				</tr>
				<tr>
					<td><input type="submit" value="Login"></td>
					<td><input type="reset" value="Cancle"></td>
				</tr>
			</table>
		</form>
	</div>

</body>
</html>