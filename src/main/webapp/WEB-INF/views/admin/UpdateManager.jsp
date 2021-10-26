<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../include/menu.jsp" %>     
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>updateManager.jsp</title>
<link href="/css/member.css" rel="stylesheet">  
<script src="/script/admin.js"></script>
<body>
<div class="wrap">
<article>

<form method="post" name="updateForm" action="adminUpdate">
	<h1 style="color: white;" id="logo">관리자정보 수정</h1>
	<hr><br><br>
		<div class="title">아이디</div>
		<div class="text"><input type="text" name="id" class="id1"  size="50" value="${adminUser.id}" readonly></div><br>
		<div class="title">비밀번호</div>
		<div class="text"><input type="password" class="id1" name="pwd" id="pw"
						placeholder="비밀번호(8~32자리)" size="50" onchange="check_pw()"></div><br>
       <div class="text"><input type="password" class="id1" name="pwdCheck"
						placeholder="비밀번호 확인" size="50" onchange="check_pw()">&nbsp;<span
						id="check"></span></div><br>
        <div class="title">이름</div>
        	<div class="text"><input type="text" name="name"  value="${adminUser.name}" class="id1" size="50"><span id="checkid"></span></div><br> 
        <div class="title">이메일</div>
        	<div class="text"><input type="text" name="email" value="${adminUser.email}"  class="id1" size="50"></div><br>
	    <div class="title">전화번호</div>
	    	<div class="text">
					<select class="id1">
						<option>+82</option>
						<option>+1 Canada</option>
						<option>+63 Philippines</option>
						<option>+55 Brazil</option>
					</select>&nbsp;&nbsp;<input type="text" class="id1"
						name="phone" value="${adminUser.phone}" size="30">
				</div>
				<br><br><br>
		<div align="center">
		<input type="button" id="button2" value="정보수정" class="submit" onclick="go_update();"> 
		<input type="reset" id="button2" value="취소" class="cancel">
	</div>
</form>
</article>
</div>
</body>
</html>