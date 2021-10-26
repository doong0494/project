<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <%@ include file="../include/menu.jsp" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style type="text/css">
/* CSS Document */
/* 공통 */
body {
	font-size:15px;
	background:black;
	color:white; 
	}
#myVideo{
	margin-top:50px;  position:relative; float:left; border:1px solid gray;
	}
/* 메타정보 */
#dvMeta { width:500px;}
#imgPoster {width:150px; height:200px;}
/* 저작권 */
#dvCRight { 
	width:500px; 
	height:50px; 
	padding-top:20px; 
	background:#CCC; 
	text-align:center; 
	text-align:center;
	cursor: pointer;
}
#dvCRight:hover{
	background:#164976;
}
#wrap{
	left:20px;
	width:500px; 
	height:500px; 
	position:relative; 
	float:left; 
}
#box{width: 1700px;
	height:800px;
/* 	border: 1px dashed white; */
	position: fixed; 
    top:55%; left:53%;
    transform: translate(-50%,-50%);
    text-align:center;} 
#back{
	cursor: pointer;
}
</style>
</head>
<body>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<link href="movie.css" rel="stylesheet" type="text/css" />
<title>movieDetail.jsp</title>
 
</head>
 
<body>
<br>
<!-- 티저 -->
<div id="box">
 <h1 style="text-align:left; ">영화 상세정보</h1>
<video id="myVideo" src="/files/${movieVO.video}" width="950px" controls autoplay></video>
<!-- 메타정보 -->
<div id="wrap" align="left">
<div id="back" align="right" onclick="history.go(-1)"><img src="/files/back.jpg" width="60px"></div>

    <table border="0">
        <tr>
        <td rowspan="4" width="20px"><img src="/files/${movieVO.image}" id="imgPoster"/></td>
       <td>영화 : ${movieVO.title}</td><br/></tr>
       <tr><td>감독 : ${movieVO.director}</td></tr>
        <tr><td>주연 : ${movieVO.actor}</td></tr>
        <tr><td>등급 : ${movieVO.agegrade} 관람가</td></tr>
      	<tr><td colspan="2" >${movieVO.content}</td></tr> 
    </table>
 
<!-- 저작권 -->
<br/>
<div id="dvCRight" onclick="location.href='orderForm?movieno=${movieVO.movieno}'"> <span id="spanCright" >예매하기</span></div>
<!-- 리뷰 -->
<br/><br/>
<!-- <fieldset> 
		<legend>Review</legend>  
	    <label>Title</label><input type="text" name="subject"  size="60" ><br>
		<label>Content</label><textarea rows="8" cols="65" name="content"></textarea>
</fieldset> -->
</div>
</div>
</body>
</html>
</body>
</html>