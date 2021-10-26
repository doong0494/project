<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../../include/menu.jsp" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<style>
body{
   background:black;
   color:white;
}
#wrap{
   width: 971px;
   height:800px;
/*    border: 1px dashed white; */
   position: fixed; 
    top:50%; left:50%;
    transform: translate(-50%,-50%);
    text-align:center;
}

th, td{ 
 padding: 8px 5px;
 
}

table#qnaList {
 border-collapse: collapse; /* border 사이의 간격 없앰 */
 border-top: 2px solid #333;
 border-bottom: 1px solid #333;
 width: 100%; /* 전체 테이블 길이 설정 */
 text-align:center;
}

#button2{
   padding : 7px 15px;
   border:none;
   border-radius : 5px 5px 5px 5px;
   background:#a0a0a0;
   color:white;
   font-size:93%;
   
}
</style>
<script src="/script/admin.js"></script>
<br>
<div id="wrap">
<article>
<h1>Q&amp;A 게시글 리스트</h1>
<form name="qna" method="post"> 
<table  style="float:right;" >
	<tr style="text-align:right;"><td width="642">회원검색&nbsp;<input type="text" name="key" value="${key}">
		<input id="button2" type="button" name="btn_search" value="검색" onClick="go_search_qna()">
		<input id="button2" type="button" name="btn_total" value="전체보기 "onClick="go_qtotal()">
</table>
<div id="table" align="center">
<table id="qnaList">
   <tr style=" border-bottom: 2px solid #333;" ><th>번호(답변여부)</th><th>제목</th><th>작성자</th>
      <th>작성일</th></tr>
   <c:forEach items="${qnaList}" var="qnaVO">
      <tr><td>${qnaVO.qseq}
         <c:choose>
            <c:when test='${qnaVO.rep=="1"}'>(미처리)</c:when>
            <c:otherwise>(답변처리완료)</c:otherwise>
         </c:choose></td>
         <td><a href="#" onclick="javascript:go_view('${qnaVO.qseq}')" style="color:white;">
                                             ${qnaVO.subject}</a></td>
         <td> ${qnaVO.id}</td>
         <td><fmt:formatDate value="${qnaVO.indate}"/></td>
      </tr>
   </c:forEach>
</table><br>
<br>
</div>
</form>
<jsp:include page="../../include/paging/paging.jsp">
    <jsp:param value="${paging.page}" name="page"/>
    <jsp:param value="${paging.beginPage}" name="beginPage"/>
    <jsp:param value="${paging.endPage}" name="endPage"/>
    <jsp:param value="${paging.prev}" name="prev"/>
    <jsp:param value="${paging.next}" name="next"/>
    <jsp:param value="adminQnaList" name="command"/>
</jsp:include>
</article>
</div>