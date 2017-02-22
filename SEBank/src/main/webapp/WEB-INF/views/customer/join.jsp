<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<script type="text/javascript" src="js/customer.js"></script>
<meta charset="UTF-8">
<title>회원가입</title>
</head>
<body>
	<h1>[ 회원가입 ]</h1>
	<div>
		<form action="join" method="post" onsubmit="return formCheck()">
			<table>
				<tr>
					<td class="labels">ID</td>
					<td class="inputs"><input type="text" name="custid"
						id="custid" placeholder="use CheckID first"> <input
						type="button" onclick="goCheck()" value="CheckID"></td>
				</tr>
				<tr>
					<td class="labels" rowspan="2">Password</td>
					<td class="inputs"><input type="password" name="password"
						placeholder="input password" id="password"></td>
				</tr>
				<tr>
					<td class="inputs"><input type="password" name="passwordc"
						placeholder="input password for check" id="passwordc"></td>
				</tr>
				<tr>
					<td class="labels">Name</td>
					<td class="inputs"><input type="text" name="name"
						placeholder="input name" id="name"></td>
				</tr>
				<tr>
					<td class="labels">e-Mail</td>
					<td><input type="text" name="email" placeholder="input e-mail"
						id="email"></td>
				</tr>
				<tr>
					<td class="labels">Division</td>
					<td><input type="radio" name="division" value="private"
						checked="checked" id="division">Private<input type="radio"
						name="division" value="company" id="division">Company</td>
				</tr>
				<tr>
					<td class="labels">ID Number</td>
					<td><input type="text" name="idno" id="idno"
						placeholder="private : social / company : companyNum"></td>
				</tr>
				<tr>
					<td class="labels">Address</td>
					<td><input type="text" id="address" name="address"
						placeholder="input address"></td>
				</tr>

				<tr>
					<td><input type="submit" value="Register"></td>
					<td><input type="reset" value="Cancel"></td>
				</tr>
			</table>
		</form>
	</div>

</body>
</html>