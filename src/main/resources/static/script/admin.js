function go_qtotal(){
	document.qna.key.value="";
	document.qna.action = "adminQnaList";
	document.qna.submit();
}
	

function go_view(qseq){
	 location.href = "adminQnaDetail?qseq=" + qseq;

}

function go_rep(qseq){
   document.frm.action="adminQnaRepsave";
   document.frm.submit();
}


function go_list(){
	location.href="adminQnaList";
}

function go_search_qna(){
	if( document.qna.key.value=="") return;
	var url = "adminQnaList";
	document.qna.action = url;
    document.qna.submit();
}
function go_update() {
	if (document.updateForm.pwd.value == "") {
		alert("비밀번호를 입력해 주세요.");
		document.updateForm.pwd.focus();
	} else if ((document.updateForm.pwd.value != document.updateForm.pwdCheck.value)) {
		alert("비밀번호가 일치하지 않습니다.");
		document.updateForm.pwd.focus();
	} else if (document.updateForm.name.value == "") {
		alert("이름을 입력해 주세요.");
		document.updateForm.name.focus();
	} else if (document.updateForm.email.value == "") {
		alert("이메일을 입력해 주세요.");
		document.updateForm.email.focus();
	} else {
		alert("수정이 완료되었습니다");
		document.updateForm.action = "adminUpdate";
		document.updateForm.submit();
	}
}