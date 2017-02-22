/**
 * 
 */
function idcheckForm() {
	if (document.getElementById('searchId').value.length < 3) {
		alert('검색할 ID를 3자 이상 입력하세요.');
		return false;
	}
	return true;
}

function goCheck() {
	window.open("idcheck", "ID Check", "top=200, left=400, width=350, height=200, resizagle=no");
}


function idSelect(id) {
	opener.document.getElementById('custid').value = id;
	this.close();
}

function formCheck() {
	var custid = document.getElementById('custid').value;
	var password = document.getElementById('password').value;
	var passwordc = document.getElementById('passwordc').value;
	var name = document.getElementById('name').value;
	var email = document.getElementById('email').value;
	var idno = document.getElementById('idno').value;
	var address = document.getElementById('address').value;

	if (custid == '') {
		alert('아이디를 입력하십시오');
		return false;
	}
	if (password == '') {
		alert('비밀번호를 입력하십시오');
		return false;
	}

	if (password != passwordc) {
		alert('비밀번호가 일치하지 않습니다.');
		return false;
	}

	if (name == '') {
		alert('이름을 입력하십시오');
		return false;
	}

	if (email == '') {
		alert('이메일을 입력하십시오');
		return false;
	}

	if (idno == '') {
		alert('식별번호를 입력하십시오');
		return false;
	}

	if (address == '') {
		alert('주소를 입력하십시오');
		return false;
	}

	return true;
}