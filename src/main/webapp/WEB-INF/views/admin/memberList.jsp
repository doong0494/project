<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../include/menu.jsp" %>

<link href="/css/admin.css" rel="stylesheet">  
<script src ="/script/memberList.js"></script>
</head>
<body>
<br><br>
<div id="wrap">
<article>
<h1>회원 리스트</h1>
<form name="frm" method="post">
<table  style="float:right;" >
	<tr style="text-align:right;"><td width="642">회원검색&nbsp;<input type="text" name="key" value="${key}">
		<input id="button2" type="button" name="btn_search" value="검색" 
		onClick="go_msearch();">
		<input id="button2" type="button" name="btn_total" value="전체보기 "
		onClick="go_mtotal();">
</table>

<div id="table" align="center">
<table id="movieList" >
	<tr style=" border-bottom: 2px solid #333;"><th>번호</th><th style=" padding-left:50px;">아이디</th><th>Email</th><th>phone</th>
	<c:forEach items="${memberList}" var="memberList" varStatus="status">
		<tr><td height="23" align="center" >${paging.totalCount-((paging.page-1)*10+status.index)}</td>
			<td style=" padding-left:50px;">
				<a${memberList.id}style="color:white;">
					&nbsp;${memberList.id}</a></td>
			<td> ${memberList.email}</td>
			<td> ${memberList.phone}</td>
		</tr>
	</c:forEach>
</table><br>
<jsp:include page="../include/paging/paging.jsp">
    <jsp:param value="${paging.page}" name="page"/>
    <jsp:param value="${paging.beginPage}" name="beginPage"/>
    <jsp:param value="${paging.endPage}" name="endPage"/>
    <jsp:param value="${paging.prev}" name="prev"/>
    <jsp:param value="${paging.next}" name="next"/>
    <jsp:param value="adminMemberList" name="command"/>
</jsp:include>
</div>
</form>
</article>
</div>
