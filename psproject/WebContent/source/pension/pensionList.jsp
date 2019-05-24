<%@page import="java.util.Date"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions"  prefix="fn"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<jsp:include page="../header.jsp"/>
<div class="wrap">
<fmt:formatDate var="now" pattern="yyyy-MM-dd" value="<%=new Date()%>"/>
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
							<a href="detail.do?psidx=${i.psidx}&startdate=${now}">View more</a>
						</figcaption>
					</div>
				</figure>
			</div>
		</c:forEach>
	</div>
	<hr>
	<form name="form1" method="get" action="list" class="formsearch">
		<div>
			<h4>지역검색</h4>
			<select>
				<option value="경기">경기</option>
			</select> 
			<select name="search">
				<option value="가평">가평군</option>
				<option>춘천</option>
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
			<input type="submit">
		</div>
	</form>
	<hr>
	</c:if>
	<div class="row">
		<div class="col-md-12">
		${search}
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
									<a href="detail.do?psidx=${i.psidx}&startdate=${now}">View more</a>
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
               				<li class="page-item  active"><a class="page-link" href="list?search=${search}&page=${page.fromPage-1}&type=${type}">&lt;&lt;</a></li>
               			</c:if>
               			<c:if test="${page.currPage > 1}">
               				<li class="page-item  active"><a class="page-link" href="list?search=${search}&page=${page.currPage-1}&type=${type}">&lt;</a></li>
               			</c:if>
               			<c:forEach begin="${page.fromPage}" end="${page.toPage}" step="1" var="i">
	        				<li class="page-item"><a class="page-link ${i == page.currPage ? 'is-curr-page' : ''}" href="list?search=${search}&page=${i}&type=${type}">${i}</a></li>
               			</c:forEach>
               			<c:if test="${page.currPage < page.pageCnt}">
               				<li class="page-item  active"><a class="page-link" href="list?search=${search}&page=${page.currPage+1}&type=${type}">&gt;</a></li>
               			</c:if>
               			<c:if test="${page.toPage != page.pageCnt}">
               				<li class="page-item  active"><a class="page-link" href="list?search=${search}&page=${page.toPage+1}&type=${type}">&gt;&gt;</a></li>
               			</c:if>
               			</ul>
               		</nav>
               	</div>
           	</div>
		</div>
	</div>
</div>
<jsp:include page="../footer.jsp"/>
