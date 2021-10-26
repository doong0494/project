<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %> 
<%@ include file="../../include/menu.jsp" %>

<link href="/css/admin.css" rel="stylesheet">  
<script src ="/script/movielist.js"></script>
</head>
<body>
<br>
<div id="wrap">
<article>
<h1>영화 리스트</h1>
<form name="frm" method="post">
<table  style="float:right;" >
	<tr style="text-align:right;"><td width="642">영화제목&nbsp;<input type="text" name="key" value="${key}">
		<input id="button2" type="button" name="btn_search" value="검색"
		onClick="go_search();">
		<input id="button2" type="button" name="btn_total" value="전체보기 "
		onClick="go_total();">
		<input id="button2" type="button" name="btn_write" value="영화등록"  
			onClick="go_wrt();"></td></tr>
</table>

<div id="table" align="center">
<table id="movieList" >
	<tr style=" border-bottom: 2px solid #333;"><th>번호</th><th>영화제목</th><th>연령대</th><th>개봉일</th>
		<th>사용유무</th></tr>
	<c:forEach items="${Movielist}" var="MovieVO" varStatus="status">
		<tr><td height="23" align="center" >${paging.totalCount-((paging.page-1)*10+status.index)}</td>
			<td style="text-align:left; padding-left:100px;">
				<a href="#" onClick="go_detail('${MovieVO.movieno}')" style="color:white;">
					${MovieVO.title}</a></td>
			<td> ${MovieVO.agegrade}</td>
			<td> ${MovieVO.opendate}</td>
			<td><c:choose>
      			<c:when test='${MovieVO.useyn=="n"}'>미사용</c:when>
   	 			<c:otherwise>사용</c:otherwise> 
				</c:choose></td>
		</tr>
	</c:forEach>
</table><br>
<jsp:include page="../../include/paging/paging.jsp">
    <jsp:param value="${paging.page}" name="page"/>
    <jsp:param value="${paging.beginPage}" name="beginPage"/>
    <jsp:param value="${paging.endPage}" name="endPage"/>
    <jsp:param value="${paging.prev}" name="prev"/>
    <jsp:param value="${paging.next}" name="next"/>
    <jsp:param value="adminMovieList" name="command"/>
</jsp:include>
<br>
</div>
</form>
</article>
</div>
