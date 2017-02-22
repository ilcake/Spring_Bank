<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<script type="text/javascript" src="js/customer.js"></script>
<meta charset="UTF-8">
<title>회원정보 수정</title>
</head>
<body>
	<h1>[ 회원정보 수정 ]</h1>
	<div>
		<form action="update" method="post">
			<table>
				<tr>
					<td class="labels">ID</td>
					<td class="inputs"><input type="text" name="custid"
						id="custid" value="${ customer.custid }"></td>
				</tr>
				<tr>
					<td class="labels" rowspan="2">Password</td>
					<td class="inputs"><input type="password" name="password"
						placeholder="input password"></td>
				</tr>
				<tr>
					<td class="inputs"><input type="password" name="passwordc"
						placeholder="input password for check"></td>
				</tr>
				<tr>
					<td class="labels">Name</td>
					<td class="inputs"><input type="text" name="name"
						value="${ customer.name }"></td>
				</tr>
				<tr>
					<td class="labels">e-Mail</td>
					<td><input type="text" name="email"
						value="${ customer.email }"></td>
				</tr>
				<tr>
					<td class="labels">Division</td>
					<td><input type="radio" name="division" value="private"
						${ customer.division=='private' ? 'checked' : '' } />Private<input
						type="radio" name="division" value="company"
						${ customer.division=='company' ? 'checked' : '' } />Company</td>

				</tr>
				<tr>
					<td class="labels">ID Number</td>
					<td><input type="text" name="idno"
						placeholder="private : social / company : companyNum"
						value="${ customer.idno }"></td>
				</tr>
				<tr>
					<td class="labels">Address</td>
					<td><input type="text" name="address"
						value="${ customer.address }"></td>
				</tr>

				<tr>
					<td><input type="submit" value="Update"></td>
					<td><input type="reset" value="Cancel"></td>
				</tr>
			</table>
		</form>
	</div>

</body>
</html>