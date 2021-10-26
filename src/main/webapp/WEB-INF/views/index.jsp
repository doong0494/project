<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="include/menu.jsp" %>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8" />
<title>main.jsp</title>
<meta name="viewport" content="width=device-width, initial-scale=1" />
<!-- Link Swiper's CSS -->
<link rel="stylesheet" href="https://unpkg.com/swiper/swiper-bundle.min.css"/>
<!-- Demo styles -->
<link href="/css/main.css" rel="stylesheet">  
</head>
<body>
	<!-- Swiper -->
	<br><br>
	<div class="swiper-container">
	  <div class="swiper-wrapper">
	    <div class="swiper-slide"><img src="/files/9.PNG" width="50%" height="40%" 
	    onclick="location.href='movieDetail?movieno=9'"><div class="rank">9. 미드나이트</div>
	    <div class="btn"><input type="button" id="order" value="예매하기" onclick="location.href='orderForm?movieno=9'"></div></div>
	    
	    <div class="swiper-slide"><img src="/files/1.PNG" width="50%" height="40%" 
	    onclick="location.href='movieDetail?movieno=1'"><div class="rank">1. 블랙위도우</div>
	    <div class="btn"><input type="button" id="order" value="예매하기" onclick="location.href='orderForm?movieno=1'"></div></div>
	    
	    <div class="swiper-slide"><img src="/files/2.PNG" width="50%" height="40%" 
	    onclick="location.href='movieDetail?movieno=2'"><div class="rank">2. 루카</div>
	    <div class="btn"><input type="button" id="order" value="예매하기" onclick="location.href='orderForm?movieno=2'"></div></div>
	    
	    <div class="swiper-slide"><img src="/files/3.PNG" width="50%" height="40%"
	    onclick="location.href='movieDetail?movieno=3'"><div class="rank">3. 크레센도</div>
	    <div class="btn"><input type="button" id="order" value="예매하기" onclick="location.href='orderForm?movieno=3'"></div></div>
	    
	    <div class="swiper-slide"><img src="files/4.PNG" width="50%" height="40%"
	    onclick="location.href='movieDetail?movieno=4'"><div class="rank">4. 크루엘라</div>
	    <div class="btn"><input type="button" id="order" value="예매하기" onclick="location.href='orderForm?movieno=4'"></div></div>
	    
	    <div class="swiper-slide"><img src="/files/5.PNG" width="50%" height="40%"
	    onclick="location.href='movieDetail?movieno=5'"><div class="rank">5. 빛나는순간</div>
	    <div class="btn"><input type="button" id="order" value="예매하기" onclick="location.href='orderForm?movieno=5'"></div></div>
	    
	    <div class="swiper-slide"><img src="/files/6.PNG" width="50%" height="40%"
	    onclick="location.href='movieDetail?movieno=6'"><div class="rank">6. 저승보다낯선</div>
	    <div class="btn"><input type="button" id="order" value="예매하기" onclick="location.href='orderForm?movieno=6'"></div></div>
	    
	    <div class="swiper-slide"><img src="/files/7.PNG" width="50%" height="40%"
	    onclick="location.href='movieDetail?movieno=7'"><div class="rank">7. 발신제한</div>
	    <div class="btn"><input type="button" id="order" value="예매하기" onclick="location.href='orderForm?movieno=7'"></div></div>
	    
	    <div class="swiper-slide"><img src="/files/8.PNG" width="50%" height="40%"
	    onclick="location.href='movieDetail?movieno=8'"><div class="rank">8. 블라이스 스피릿</div>
	    <div class="btn"><input type="button" id="order" value="예매하기" onclick="location.href='orderForm?movieno=8'"></div></div>
	  </div>
	  <div class="swiper-button-next" style="color: white;"></div>
	<div class="swiper-button-prev" style="color: white;"></div>
	</div>
	<!-- Swiper JS -->
<script src="https://unpkg.com/swiper/swiper-bundle.min.js"></script>
	<!-- Initialize Swiper -->
<script>
	var swiper = new Swiper('.swiper-container', {
	  loop:true,
	  loopAdditionalSildes:1,
	  slidesPerView: 3,
	  direction: getDirection(),
	  navigation: {
	    nextEl: '.swiper-button-next',
	    prevEl: '.swiper-button-prev',
	  },
	  on: {
	    resize: function () {
	      swiper.changeDirection(getDirection());
	    },
	  },
	});
	
	function getDirection() {
	  var windowWidth = window.innerWidth;
	  var direction = window.innerWidth <= 760 ? 'vertical' : 'horizontal';
	  return direction;
}
</script>
</body>
</html>

