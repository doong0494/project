<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../include/menu.jsp" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<style type="text/css">
body{
   background:black;
   color:white;
}
/*  tr, th, td{
   border:1px dashed white;
}  */ 
th, td{
   height:50px;
   text-align:left;
}
#table2 th{
	width:100px;
}
#table2{
	width:400px;
	font-size:110%;
	padding:5px 40px;
}
#wrap{
	position: fixed; 
    top:50%; left:50%;
    transform: translate(-50%,-50%);
}
#button2{
	padding : 7px 15px;
	border:none;
	border-radius : 5px 5px 5px 5px;
	background:#a0a0a0;
	color:white;
	font-size:95%;
	}
	
</style>
</head>
<body>
<br>
<div id="wrap" align="center">
<h1 style="border-bottom:2px dashed white; width:900px; height:60px;">예매 완료</h1><br>
<input type="hidden" id="seat" value="${orderVO.movieno }">
<table>
   <tr><td>
      <img src="/files/${movieVO.image}" width="320" height="420">
      </td>
      <td>
         <table id="table2">
            <tr><th> 영화제목 </th><td>${movieVO.title}</td></tr>
            <tr><th> 날짜 </th><td>${orderVO.moviedate }</td></tr>
            <tr><th> 시작시간 </th><td>${orderVO.movietime}</td></tr>
            <tr><th>상영관</th><td>${orderVO.cinemas }</td></tr>
            <tr><th>성인</th><td>${orderVO.quantity1}명</td></tr>
            <tr><th>소인</th><td>${orderVO.quantity2}명</td></tr>
            <%-- <tr><th>금액</th><td><fmt:formatNumber type="currency"></fmt:formatNumber></td></tr> --%>
            <tr><th>좌석</th><td>${orderVO.seat}</td></tr>
              <tr ><td >
               <input type="button" id="button2" style="background:gray; opacity:0.9; " value="홈으로 이동" class="submit"
                onClick="go_main();"></td>
                <td >
               <input type="button" id="button2" style="background:gray; opacity:0.9; " value="mypage로 이동" class="submit"
                onClick="go_mypage();"></td>
         </table>
      </td>
   </tr>
</table>
</div>
</body>
<script type="text/javascript">
function go_main(){
   location.href="/";
}
function go_mypage(){
	location.href="myPage";
}
</script>
</html>
