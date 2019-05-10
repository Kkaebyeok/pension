<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions"  prefix="fn"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>NolDaGa &mdash; 놀다가</title>
	<script src="https://code.jquery.com/jquery-3.3.1.js" integrity="sha256-2Kok7MbOyxpgUVvAk/HJ2jigOSYS2auK4Pfzbm7uH60=" crossorigin="anonymous"></script>
	<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
	<link href="https://fonts.googleapis.com/css?family=Josefin+Sans:300, 400,700|Inconsolata:400,700" rel="stylesheet">
	<link href="images/icon/favicon.ico" rel="icon">
	<link rel="stylesheet" href="css/bootstrap.css">
	<link rel="stylesheet" href="css/animate.css">
	<link rel="stylesheet" href="css/owl.carousel.min.css">
	<link rel="stylesheet" href="css/search.css">
	<link rel="stylesheet" href="fonts/ionicons/css/ionicons.min.css">
	<link rel="stylesheet" href="fonts/jua/css/jua.css">
	<link rel="stylesheet" href="fonts/fontawesome/css/font-awesome.min.css">
	<link rel="stylesheet" href="fonts/flaticon/font/flaticon.css">
	<link rel="stylesheet" href="fonts/jua/css/jua.css">
	<!-- Theme Style -->
	<link rel="stylesheet" href="css/style.css">
	<link rel="stylesheet" href="css/index.css">
	<link rel="stylesheet" href="css/pension.css">
</head>
<body>
<div class="wrap">
<jsp:include page="../header.jsp"/>
	<div class="position">
	<c:if test="${type != 5}">
	<h5 id="formsearch1">경기도 전체 추천펜션</h5>
	<hr>
	<div class="row">
		<c:forEach var="i" items="${list}">
			<div class="grid col-md-3">
				<figure class="effect-marley">
					<img src="${web_path}/${i.oridx}/thumb.jpg" alt="${i.pstitle}">
					<div class="blog-content-body">
						<figcaption class="post-meta">
							<h5><span>${i.pstitle}</span></h5>
							<p>${i.preaddr}</p>
							<a href="detail.do?psidx=${i.psidx}&startdate=2019-04-22">View more</a>
						</figcaption>
					</div>
				</figure>
			</div>
		</c:forEach>
	</div>
	<hr>
	<form name="form1" method="get" action="list.do" class="formsearch">
		<div>
			<h4>지역검색</h4>
			<select>
				<option value="경기">경기</option>
			</select> 
			<select name="search">
				<option value="가평" >가평군</option>
				<option> 춘천</option>
           		<option>강화도</option>
           		<option>광주시</option>
           		<option>남양주시</option>
           		<option>대부도</option>
           		<option>안성시</option>
           		<option>양평군</option>
           		<option>포천시</option>
           		<option>용인시</option>
           		<option>고양시</option>
           		<option>파주시</option>
           		<option>선재도</option>
           		<option>여주시</option>
           		<option>영흥도</option>
           		<option>이천시</option>
           		<option>을왕리</option>
           		<option>연천군</option>
           		<option>양주시</option>
           		<option>안양시</option>
           		<option>안산시</option>
           		<option>웅진군</option>
           		<option>화성시(제부도)</option>
			</select> 
			<input name="search" type="submit">
		</div>
	</form>
	<hr>
	</c:if>
	<div class="row">
		<div class="col-md-12">
			<h3 id="formsearch1">[${search}] 검색 결과</h3>
			<h5>총 ${num}개의 펜션이 있습니다.</h5>
		</div>
	</div>
	<hr>
	<div class="row blog-entries">
			<div class="col-md-12 col-lg-12 main-content">
				<div class="row">
					<c:forEach var="i" items="${items}">
						<div class="grid col-md-3">
							<figure class="effect-marley">
								<img src="${web_path}/${i.oridx}/thumb.jpg" alt="${i.pstitle}">
								<div class="blog-content-body">
									<figcaption class="post-meta">
										<h5><span>${i.pstitle}</span></h5>
										<p>${i.preaddr}<br><span class="price">최저가 : ${i.lowPrice}원</span></p>
										<a href="detail.do?psidx=${i.psidx}&startdate=2019-04-22">View more</a>
									</figcaption>
								</div>
							</figure>
						</div>
					</c:forEach>
					<!-- grid 개수 채우기 -->
					<c:forEach begin="1" end="${3-fn:length(items) mod 3}">
						<div class="grid col-md-3">
							<figure class="effect-marley"></figure>
						</div>
					</c:forEach>
					<c:if test="${num==0}">
						<div class="grid col-md-12">
							<div class="tbox">
								<h2><span class="tcolor">[${search}]</span>에 대한 <span class="tcolor">검색결과</span>가 없습니다.</h2>
								<p>다시 한 번 확인하시고 검색하여 주십시오.</p>
							</div>
						</div>
					</c:if>
					<c:if test="${page.currPage > page.pageCnt || page.currPage < 1}">
						<div class="grid col-md-12">
							<div class="tbox">
								<h2><span class="tcolor">페이지</span>의 <span class="tcolor">검색범위</span>가 유효하지 않습니다.</h2>
								<p>다시 한 번 확인하시고 검색하여 주십시오.</p>
							</div>
						</div>
					</c:if>
				</div>
				<div class="row mt-5">
                	<div class="col-md-12 text-center">
                		<nav aria-label="Page navigation" class="text-center">
                			<ul class="pagination">
                			<c:if test="${page.fromPage != 1}">
                				<li class="page-item  active"><a class="page-link" href="list.do?search=${search}&page=${page.fromPage-1}&type=${type}">&lt;&lt;</a></li>
                			</c:if>
                			<c:if test="${page.currPage > 1}">
                				<li class="page-item  active"><a class="page-link" href="list.do?search=${search}&page=${page.currPage-1}&type=${type}">&lt;</a></li>
                			</c:if>
                			<c:forEach begin="${page.fromPage}" end="${page.toPage}" step="1" var="i">
		        				<li class="page-item"><a class="page-link ${i == page.currPage ? 'is-curr-page' : ''}" href="list.do?search=${search}&page=${i}&type=${type}">${i}</a></li>
                			</c:forEach>
                			<c:if test="${page.currPage < page.pageCnt}">
                				<li class="page-item  active"><a class="page-link" href="list.do?search=${search}&page=${page.currPage+1}&type=${type}">&gt;</a></li>
                			</c:if>
                			<c:if test="${page.toPage != page.pageCnt}">
                				<li class="page-item  active"><a class="page-link" href="list.do?search=${search}&page=${page.toPage+1}&type=${type}">&gt;&gt;</a></li>
                			</c:if>
                			</ul>
                		</nav>
                	</div>
            	</div>
			</div>
		</div>
	</div>
</div>
<jsp:include page="../footer.jsp"/>
</body>
</html>