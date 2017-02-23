/**
 * 
 */

var isEditing = false;

function pagingForSubmit() {
}

function formCheck() {
	var title = document.getElementById("title");
	var content = document.getElementById("content");

	if (title.value.length < 5) {
		alert('Title must be longer then 5 words');
		return false;
	}
	if (content.value.length < 1) {
		alert('Please Insert Content');
		return false;

	}
	return true;
}

function deleteCheck(boardnum) {
	var answer = confirm('confirm delete?');
	if (answer) {
		location.href = "boardDelete?boardnum=" + boardnum;
	}
}

function replyDelete(replynum, boardnum) {
	var answer = confirm('confirm delete?');
	if (answer) {
		location.href = "replyDelete?replynum=" + replynum + "&boardnum=" + boardnum;
	}
}


function replyUpdate(replynum) {
	isEditing = true;
	alert("replyText" + replynum + "  isEditing? " + isEditing);
	var replyText = document.getElementById("replyText" + replynum);
	var replyButton = document.getElementById("replyButton" + replynum);
	replyText.innerHTML = "<form><input type='text' value='${reply.text} name='text' id=''text' /></form>"
	replyButton.innerHTML = "<input	type='button' value='edit' onclick="" /> <input
								type='button' value='delete'onclick='' />'"
}

function replyUpdateOrder(){
	
}