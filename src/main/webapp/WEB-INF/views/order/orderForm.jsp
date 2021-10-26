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
	font-size:93%;
}
.select{
	width:160px; height:35px; border:2px 2px 2px 2px; font-size:95%;
}
</style>
</head>
<script src="script/seatSelect.js"></script>
<script type="text/javascript">
// 적용되지 않음
/*
function moviedate(){ 
	var start_year="2015";// 시작할 년도 
	var today = new Date(); 
	var today_year= today.getFullYear(); 
	var index=0; for(var y=start_year; 
	y<=today_year; y++){ //start_year ~ 현재 년도 
		document.getElementById('select_year').options[index] = new Option(y, y); 
		index++; 
	} 
	index=0; 
	for(var m=1; m<=12; m++){ 
		document.getElementById('select_month').options[index] = new Option(m, m); 
		index++; 
	}
	
	lastday(); 
	function lastday(){ //년과 월에 따라 마지막 일 구하기 
		var Year=document.getElementById('select_year').value; 
		var Month=document.getElementById('select_month').value; 
		var day=new Date(new Date(Year,Month,1)-86400000).getDate(); 
		 = new Date(new Date(Year,Month,0)).getDate();  
		var dayindex_len=document.getElementById('select_day').length; 
		if(day>dayindex_len){ 
			for(var i=(dayindex_len+1); i<=day; i++){
				document.getElementById('select_day').options[i-1] = new Option(i, i); 
				} 
			} else if(day<dayindex_len){ 
				for(var i=dayindex_len; i>=day; i--){ 
					document.getElementById('select_day').options[i]=null; 
					} 
				}
		}
}
*/
</script>
<body>
<br>
<div id="wrap" align="center">
<h1 style="border-bottom:2px dashed white; width:900px; height:60px;">영화 예매</h1><br>
<input type="hidden" id="seat" value="${movie.movieno }">
<table>
   <tr><td>
      <img src="/files/${movie.image}" width="320" height="420">
      </td>
      <td>
         <table id="table2">
            <tr><th> 영화제목 </th><td>${movie.title}</td></tr>
            <tr><th> 날짜 </th>
               <td><select id="date" class="select">
               <option>2021-07-22</option>
               <option>2021-07-23</option>
               <option>2021-07-24</option>
               <option>2021-07-25</option>
               <option>2021-07-26</option>
               <option>2021-07-27</option>
               </select></td></tr>
            <tr><th> 시작시간 </th>
               <td><select id="time" class="select">
               <option>11:30</option>
               <option>14:50</option>
               <option>17:40</option>
               <option>21:00</option>
               </select></td></tr>
            <tr><th>상영관</th>
               <td>
               <select id="cinemas"  class="select" items="${theaterList}"  var = "theaterList" onchange="handleOnChange(this)">
               <c:forEach items="${theaterList}"  var = "theaterList" >
               <option name="cinemas" value="${theaterList.cinemas }">${theaterList.cinemas }</option>
               </c:forEach>
               </select>
               </td></tr>
               <tr><th>성인</th>
                  <td>
                     <select id="quantity1"  class="select">
		               <c:forEach var = "i" begin="0" end="10">
		               <option name="quantity1" value="${i}">${i }명</option>
		   	           </c:forEach>
                    </select></td></tr>
               <tr><th>소인</th>
                  <td>
                  	<select id="quantity2"  class="select">
		               <c:forEach var = "i" begin="0" end="10">
		               <option name="quantity2" value="${i}">${i }명</option>
		   	           </c:forEach>
                    </select></td></tr>
               <tr ><th colspan="2" >
               <input type="button" id="button2" style="background:gray; opacity:0.9; width:170px;" value="좌석 선택하기" class="submit"
                onClick="seatSelect();"></th>
         </table>
         </td></tr></table>
      </div>
</body>
</html>