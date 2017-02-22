<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
<script type="text/javascript" src="js/customer.js">
</script>
<meta charset="UTF-8">
<title>IDCHECK</title>
</head>
<body>
	<c:if test="${ pageContext.request.method == 'GET' }">
	</c:if>
	<div>
		<form action="idcheck" method="post" onsubmit="return idcheckForm();">
			<table>
				<tr>
					<td>ID</td>
					<td><input type="text" name="searchId" id="searchId"
						placeholder="input ID for check" value="${ searchId }"></td>
				</tr>
				<tr>
					<td><input type="submit" value="CHECK"></td>
				</tr>
			</table>

			<div>
				<!--  검색한 후에만 출력 /  변수명만 적어도 기능한다. ex) if test="${ search }" -->
				<c:if test="${ not empty searchId }">
					<c:if test="${ empty searchResult }">
						<p>${ searchId }:사용할 수 있는 ID입니다.</p>
						<p>
							<input type="button" value="ID사용하기"
								onclick="idSelect('${ searchId }')">
						</p>
					</c:if>
					<c:if test="${ not empty searchResult }">
						<p>${ searchId }:이미사용중인 ID입니다</p>
					</c:if>
					<!-- 검색된 결과가 있는 경우 -->
				</c:if>
			</div>
		</form>
	</div>

</body>
</html>