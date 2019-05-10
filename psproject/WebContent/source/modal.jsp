<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="UTF-8">
<title>404-20 :: 2019. 4. 24.</title>
<!-- Remember to include jQuery :) -->
<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.0.0/jquery.min.js"></script>

<!-- jQuery Modal -->
<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery-modal/0.9.1/jquery.modal.min.js"></script>
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/jquery-modal/0.9.1/jquery.modal.min.css" />
<link rel="stylesheet" href="https://cdn.jsdelivr.net/bxslider/4.2.12/jquery.bxslider.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
<script src="https://cdn.jsdelivr.net/bxslider/4.2.12/jquery.bxslider.min.js"></script>
<style>
	.modal {max-width: 900px;}
	.bx-wrapper {margin: 0 auto 15px;}
	.bx-wrapper ul {padding: 0}
	.bx-wrapper li {margin-top: -16px;}
	.bx-wrapper .slider img {width: 100%;}
	.bx-wrapper .sliderPager li {padding: 2px;}
	.bx-wrapper .sliderPager img {width: 100%; opacity: .3; transition: all .2s;}
	.bx-wrapper .sliderPager img:hover, .slidePagerHover {opacity: 1; transform:scale(1.05);}
</style>
<script>
	$(function() {
		$("#open").click(function() {
			$(".modal").show();
			$(".slider").bxSlider({
				preloadImages: 'all',
				pagerCustom : '.sliderPager',
				mode:'fade'
			});
		
			$(".sliderPager").bxSlider({
				touchEnabled : false,
				pager : false,
				slideWidth : 180,
				slideHeight : 162,
				minSlides : 5,
				maxSlides : 5
			});
		})
	});
</script>
</head>
<body>
	<!-- Modal HTML embedded directly into document -->
	<!-- <div id="ex1" class="modal">
		<ul class="slider">
			<li><img
				src='http://noldaga.shop/psproject/pension_img//w0101114/309/main/1.jpg'></li>
			<li><img
				src='http://noldaga.shop/psproject/pension_img//w0101114/309/main/2.jpg'></li>
			<li><img
				src='http://noldaga.shop/psproject/pension_img//w0101114/309/main/3.jpg'></li>
			<li><img
				src='http://noldaga.shop/psproject/pension_img//w0101114/309/main/4.jpg'></li>
			<li><img
				src='http://noldaga.shop/psproject/pension_img//w0101114/309/main/5.jpg'></li>
			<li><img
				src='http://noldaga.shop/psproject/pension_img//w0101114/309/main/6.jpg'></li>
			<li><img
				src='http://noldaga.shop/psproject/pension_img//w0101114/309/main/7.jpg'></li>
			<li><img
				src='http://noldaga.shop/psproject/pension_img//w0101114/309/main/8.jpg'></li>
			<li><img
				src='http://noldaga.shop/psproject/pension_img//w0101114/309/main/9.jpg'></li>
		</ul>
		<ul class="sliderPager">
			<li><a href="#" data-slide-index="0"><img
				src='http://noldaga.shop/psproject/pension_img//w0101114/309/thumb/1.jpg'></a></li>
			<li><a href="#" data-slide-index="1"><img
				src='http://noldaga.shop/psproject/pension_img//w0101114/309/thumb/2.jpg'></a></li>
			<li><a href="#" data-slide-index="2"><img
				src='http://noldaga.shop/psproject/pension_img//w0101114/309/thumb/3.jpg'></a></li>
			<li><a href="#" data-slide-index="3"><img
				src='http://noldaga.shop/psproject/pension_img//w0101114/309/thumb/4.jpg'></a></li>
			<li><a href="#" data-slide-index="4"><img
				src='http://noldaga.shop/psproject/pension_img//w0101114/309/thumb/5.jpg'></a></li>
			<li><a href="#" data-slide-index="5"><img
				src='http://noldaga.shop/psproject/pension_img//w0101114/309/thumb/6.jpg'></a></li>
			<li><a href="#" data-slide-index="6"><img
				src='http://noldaga.shop/psproject/pension_img//w0101114/309/thumb/7.jpg'></a></li>
			<li><a href="#" data-slide-index="7"><img
				src='http://noldaga.shop/psproject/pension_img//w0101114/309/thumb/8.jpg'></a></li>
			<li><a href="#" data-slide-index="8"><img
				src='http://noldaga.shop/psproject/pension_img//w0101114/309/thumb/9.jpg'></a></li>
		</ul>
	</div> -->
	<div id="ex1" class="modal"><ul class="slider"><li><img src='http://noldaga.shop/psproject/pension_img//w0101376/324/main/1.jpg'></li><li><img src='http://noldaga.shop/psproject/pension_img//w0101376/324/main/2.jpg'></li><li><img src='http://noldaga.shop/psproject/pension_img//w0101376/324/main/3.jpg'></li><li><img src='http://noldaga.shop/psproject/pension_img//w0101376/324/main/4.jpg'></li><li><img src='http://noldaga.shop/psproject/pension_img//w0101376/324/main/5.jpg'></li><li><img src='http://noldaga.shop/psproject/pension_img//w0101376/324/main/6.jpg'></li><li><img src='http://noldaga.shop/psproject/pension_img//w0101376/324/main/7.jpg'></li></ul><ul class="sliderPager"><li><a href='#' data-slide-index='0'><img src='http://noldaga.shop/psproject/pension_img//w0101376/324/thumb/1.jpg'></a></li><li><a href='#' data-slide-index='1'><img src='http://noldaga.shop/psproject/pension_img//w0101376/324/thumb/2.jpg'></a></li><li><a href='#' data-slide-index='2'><img src='http://noldaga.shop/psproject/pension_img//w0101376/324/thumb/3.jpg'></a></li><li><a href='#' data-slide-index='3'><img src='http://noldaga.shop/psproject/pension_img//w0101376/324/thumb/4.jpg'></a></li><li><a href='#' data-slide-index='4'><img src='http://noldaga.shop/psproject/pension_img//w0101376/324/thumb/5.jpg'></a></li><li><a href='#' data-slide-index='5'><img src='http://noldaga.shop/psproject/pension_img//w0101376/324/thumb/6.jpg'></a></li><li><a href='#' data-slide-index='6'><img src='http://noldaga.shop/psproject/pension_img//w0101376/324/thumb/7.jpg'></a></li></ul></div>
	<!-- Link to open the modal -->
	<p><a href="#ex1" rel="modal:open" id="open">Open Modal</a></p>
</body>
</html>