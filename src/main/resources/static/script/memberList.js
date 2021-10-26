function go_mtotal(){
	document.frm.key.value="";
	document.frm.action = "adminMemberList";
	document.frm.submit();
}

function go_msearch(){
	if( document.frm.key.value=="") return;
	var url = "adminMemberList";
	document.frm.action = url;
    document.frm.submit();
}