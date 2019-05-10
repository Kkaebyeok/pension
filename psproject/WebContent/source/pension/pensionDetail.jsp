<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>${list[0].pstitle}</title>
<link href="https://fonts.googleapis.com/css?family=Josefin+Sans:300, 400,700|Inconsolata:400,700" rel="stylesheet">
<link href="images/icon/favicon.ico" rel="icon">
<link rel="stylesheet" href="css/bootstrap.css">
<link rel="stylesheet" href="css/animate.css">
<link rel="stylesheet" href="css/owl.carousel.min.css">
<link rel="stylesheet" href="css/search.css">
<link rel="stylesheet" href="fonts/ionicons/css/ionicons.min.css">
<link rel="stylesheet" href="fonts/fontawesome/css/font-awesome.min.css">
<link rel="stylesheet" href="fonts/flaticon/font/flaticon.css">
<link rel="stylesheet" href="fonts/jua/css/jua.css">
<!-- Theme Style -->
<link rel="stylesheet" href="https://cdn.jsdelivr.net/bxslider/4.2.12/jquery.bxslider.css">
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/jquery-modal/0.9.1/jquery.modal.min.css" />
<link rel="stylesheet" href="css/style.css">
<link rel="stylesheet" href="css/index.css">
<link rel="stylesheet" href="css/pension.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<script src="https://cdn.jsdelivr.net/bxslider/4.2.12/jquery.bxslider.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery-modal/0.9.1/jquery.modal.min.js"></script>
<script src="js/pension.js"></script>
<script>
	var web_path = "${web_path}";
	var oridx = "${dao.oridx}";
</script>
</head>
<body>
	<jsp:include page="../header.jsp"/>
	<div class="wrap">
		<div id="ex1" class="modal"></div>
		<div class="position">
			<div class="pensioninfo">
				<img src="${web_path}/${dao.oridx}/thumb.jpg" width="500px" height="350px">
				<ul class="psinfo">
					<li><h3>${dao.pstitle}</h3></li>
					<li><h4>상세주소</h4></li>
					<li>신주소 : ${dao.preaddr}</li>
					<li>구주소 : ${dao.curaddr}</li>
					<li><h4>입퇴실시간</h4>입실시간 : 14시 이후, 퇴실시간 : 11시 이전</li>
					<li><h4>픽업</h4></li>
					<li>${dao.pickup}</li>
					<li><h4>예약문의</h4></li>
					<li>${dao.calltel}</li>
				</ul>
				<c:if test="${member!=null}">
				<div class="info2">
					<form name="form1" method="get" action="insert.do">
						<input type="hidden" name="psidx" value="${dao.psidx}">
						<input type="button" value="예약현황">
						<input type="button" value="예약하기">
						<input type="submit" value="장바구니 담기">
					</form>
				</div>
				</c:if>
			</div>
			<hr>
			<p><a href="#ex1" rel="modal:open" id="open"></a></p>
			<ul class="cont_tab">
				<li><a href="#anchorRoom">객실안내</a></li>
				<li><a href="#anchorMap">찾아오는 길</a></li>
				<li><a href="#anchorCancel">이용 및 취소안내</a></li>
				<li><a href="#anchorReview">이용후기</a></li>
			</ul>
			<div class="detail">
				<h3 id="anchorRoom">객실안내</h3>
				<ul class="room">
				<c:forEach items="${list}" var="rm">
					<li>
						<div class="img_wrap" data-rmidx="${rm.rmidx}"><img src="${web_path}/${rm.oridx}/${rm.rmidx}/main/1.jpg"></div>
						<div>
							<h4>${rm.rmtitle}</h4>
							<p>가격 : ${rm.price}원</p>
							<p>${rm.rmpermin}인 기준 최대 ${rm.rmpermax}인</p>
							<hr>
							<button>예약</button>
						</div>
					</li>
				</c:forEach>
				</ul>
				<h3 id="anchorMap">찾아 오시는길</h3>
				<jsp:include page="map.jsp"/>
				<h3 id="anchorCancel">이용안내</h3>
				<jsp:include page="pensioncancel.jsp"/>
				<h3 id="anchorReview">후기</h3>
				<div class="review">
					<div class="review-overall">
					</div>
					<h5 class="review-cnt"></h5>
					<div class="review-detail"></div>
				</div>
				<script>
				var psidx = '${dao.psidx}';
				function makeStarHtml(number) {
					var span = $("<span>");
					for (var i = 0; i < 5; i++) {
						span.append($("<i>").addClass("fa fa-lg fa-star").css("color", number >= i+1 ? "#fc0" : "#ccc"));
					}
					return span.html();
				}
				
				function showList(){
					$.ajax({
						url : 'reviewOverall?psidx=' + psidx,
						success : function(data){
							console.log(data);
							var str = "<h4>";
							var avg = data.avg;
							for(var i = 0; i < 5; i++){
								if(avg > 1){
									s = 'fa-star'
								}
								else if(avg > 0){
									s = 'fa-star-half-o'
								}
								else{
									s = 'fa-star-o'
								}
								avg--;
								str += "<i class='fa fa-lg " + s + "'></i>";
							}
							str += "<br>" + data.avg + "</h4>";
							$(".review-overall").html(str);
							
							str = "";
							for(var i = 0; i <5; i++) {
								str += "<div class='clearfix'><div class='float-left'>";
								for(var j = 0; j < 5; j++) {
									if(i + j <= 4) {
										str += '<i class="fa fa-lg fa-star" style="color:#fc0"></i>';			
									}
									else {
										str += '<i class="fa fa-lg fa-star" style="color:#ccc"></i>';
									}
								}
								str += "</div><div class='progress float-right'>";
								str += "<div class='progress-bar' style ='width:";
								str += data.score[i] / data.cnt * 100  + "%'>" + (data.score[i] == 0? '' : data.score[i]);
								str += "</div></div></div>";
							}
							$(".review-overall").html(function(i,html) {
								return html + str;
							})
							var h = "총 리뷰 " +data.cnt;
							$(".review-cnt").html(h);
						}
					}).done(function(){
						$.ajax({
							url : 'listReview?psidx=' + psidx,
							success : function(data) {
								var str = "<ul>";
								for(var i in data) {
									str += "<li>";
									str += "<div><h5>" + data[i].title + "</h5>";
									str += "<p>" + data[i].cont + "</p>";
									str += "<p class='small'>" + data[i].email + " | " + data[i].regdate + " | " ;
									str += makeStarHtml(data[i].score);
									str += "</p></div>";
									str += "<div><img src='' alt='이미지들어갈곳'></div>";
									str += "</li>";
								}
								str += "</ul>"
								$('.review-detail').html(str);
							}
						})
					})
				}
				$(function() {
					showList();
				})
				</script>
			</div>
		</div>
	</div>
<jsp:include page="../footer.jsp"/>
</body>
</html>