<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link href="styles/index.css" rel="stylesheet">
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/animate.css/3.5.2/animate.min.css">
<title>SEBank</title>
</head>
<body>
	<h2>SEBank Project</h2>

	<nav class="navbar navbar-default navbar-fixed-top">
		<div class="container">
			<!--  
	   loginId : 세션에 저장된 변수명 (로그인된 회원의 아이디)
	   loginName : 세션에 저장된 변수명 (로그인된 회원의 이름)
	   -->
			<c:if test="${ not empty loginId }">
				<h4>${ loginName }(${ loginId })님로그인중</h4>
			</c:if>
			<ul class="pagination">
				<c:choose>
					<c:when test="${ empty loginId }">
						<li><a href="join">회원가입</a></li>
						<li><a href="login">로그인</a></li>
					</c:when>
					<c:otherwise>
						<li><a href="logout">로그아웃</a></li>
						<li><a href="update">개인정보 수정</a></li>
					</c:otherwise>
				</c:choose>
				<li><a href="listBoard">게시판</a></li>
			</ul>
		</div>
	</nav>
</body>
</html>