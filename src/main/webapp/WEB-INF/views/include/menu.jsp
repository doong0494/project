<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
 
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge"> 
    <title>Document</title>
<script type="text/javascript">
function logoutCheck(){
	alert("로그아웃되었습니다");
}
</script>
</head>
<body>
<div id="header"></div>
 <div class="logo" style="left:46.5%; position:absolute;" onclick="location.href='/'"><img src="/images/logo.png" width="130" height="100"></div> 
    <div id="menu-container">
        <div id="menu-wrapper">
            <div id="hamburger-menu"><span></span><span></span><span></span></div>
            <!-- hamburger-menu -->
        </div>
        <!-- menu-wrapper -->
        <ul class="menu-list accordion">
            <li id="nav1" class="toggle accordion-toggle"> 
                <span class="icon-plus"></span> 
                <c:choose>
                <c:when test="${!empty loginUser}">
                <a class="menu-link" href="#">${loginUser.name}(${loginUser.id})</a>
                </c:when>
                <c:when test="${!empty adminUser}">
                <a class="menu-link" href="#">${adminUser.name}(${adminUser.id})</a>
                </c:when>
                <c:otherwise>
       		<a class="menu-link" href="#">Login</a>
        </c:otherwise>
         </c:choose>
        </li>
            <ul class="menu-submenu accordion-content">
                	<c:choose>
					<c:when test="${!empty loginUser}">
						<li><a href="memberEditForm">EDIT</a></li>
						<li><a href="myPage">MYPAGE</a></li>
						<li><a href="qnaList" >Q&amp;A (1:1)</a></li>				
						<li><a href="logout" onclick= logoutCheck() >LOGOUT</a></li>	
					</c:when>
					<c:when test="${!empty adminUser}">
						<li><a href="adminEdit">EDIT</a></li>
						<li><a href="adminMovieList">MOVIELIST</a>
						<li><a href="adminMemberList">MEMBERLIST</a>
						<li><a href="adminQnaList" >Q&amp;A (1:1)</a></li>				
						<li><a href="adminLogout" onclick= logoutCheck() >LOGOUT</a></li>
					</c:when>
					<c:otherwise>
						<li><a href="loginForm">LOGIN</a></li>
						<li><a href="joinForm">JOIN</a></li>
						<li><a href="loginManagerForm">MANAGER</a></li>
					</c:otherwise>
				</c:choose>
            </ul> 
            <li id="nav2" class="toggle accordion-toggle"> 
                <span class="icon-plus"></span>
                <a class="menu-link" href="#">Movie</a>
            </li>
                <ul class="menu-submenu accordion-content">
                    <li><a class="head" href="movieInformation">Movie Information</a></li>
                    <li><a class="head" href="Cinfo">About the cinema</a></li>
                </ul>
            <li id="nav3" class="toggle accordion-toggle"> 
                <span class="icon-plus"></span>
                <a class="menu-link" href="#">Theaters</a>
            </li>
                <ul class="menu-submenu accordion-content">
                     <li><a class="head" href="th_2D">2D</a></li>
                    <li><a class="head" href="th_4DX">4DX</a></li>
                    <li><a class="head" href="th_starium">starium</a></li>
               	 	<li><a class="head" href="th_Imax">Imax</a></li>
                    <li><a class="head" href="th_premium">premium</a></li>
                    <li><a class="head" href="th_goldclass">goldclass</a></li>
                </ul>
        </ul>
        <!-- menu-list accordion-->
    </div>
    <link rel="stylesheet" href="/css/menu.css">
    <script type="text/javascript" src="http://code.jquery.com/jquery-1.11.3.min.js"></script>
    <script type="text/javascript" src="/script/menu.js"></script>

</body>
</html>