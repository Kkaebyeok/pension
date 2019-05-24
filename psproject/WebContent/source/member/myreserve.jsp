<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<style>
#accordion li {padding: 0;}
#accordion .ui-accordion-content {padding: 8px 20px;}
</style>
<c:if test="${empty member}">
<script>
	alert("세션정보가 만료되었습니다. 다시 로그인 진행해주세요.");
	location.href = "login";
</script>
</c:if>
<style>
ul.list-group {list-style: none;}
</style>
<jsp:include page="../header.jsp"/>
<div class="wrap clearfix p-5">
	<div class="float-left w-25 p-2">
		<h2 class="text-center text-danger">MY PAGE</h2>
		<div class="user_img">
			<img src="images/member/userimg.png" alt="회원이미지" class="w-100 border">
		</div>
		<ul class="list-group">
			<li>
				<a href="mypage" class="mt-2 btn btn-block list-group-item text-danger">정보수정 ></a>
			</li>
			<li>
				<a href="myreserve" class="mt-2 btn btn-block btn-light bg-danger list-group-item text-light">예약정보 ></a>
			</li>
		</ul>
	</div>
	<div class="float-right w-75 p-2 pl-5">
		<h3 class="text-center text-danger">내 예약 정보</h3>
		<ul id="accordion" class="list-group">
			<c:forEach items="${list}" var="reserve">
			<li data-rsidx="${reserve.rsidx}" class="list-group-item">
				<div class="clearfix btn btn-light btn-block" role="button">
					<p class="float-left mb-0"><span class="text-primary">${reserve.pstitle}</span> / ${reserve.rmtitle}</p>
					<p class="float-right mb-0"><span class="text-warning">${reserve.days}</span> 박</p>
				</div>
				<div>
					<div class="clearfix">
						<p class="float-left mb-0">예약자이름 : <span class="text-info">${reserve.name}</span></p>
						<p class="float-right mb-0">예약자연락처 : <span class="text-info">${reserve.tel}</span></p>
					</div>
					<div class="clearfix">
						<p class="float-left mb-0">기간 : <span class="text-dark small">${reserve.startdate} ~ ${reserve.enddate}</span></p>
						<p class="float-right mb-0">가격 : <span class="text-danger h3"><fmt:formatNumber type="number" value="${reserve.money}"/></span>원 <span class="text-dark small">(<fmt:formatNumber type="number" value="${reserve.moneyunit}"/>원 / 1박)</span></p>
					</div>
					<div class="clearfix">
						<a href="detail.do?psidx=${reserve.psidx}" class="btn btn-outline-danger rounded w-50 py-1">펜션 보러가기</a>
						<a href="reserveCancel?rsidx=${reserve.rsidx}" onclick="return confirm('정말 취소하시겠습니까?')" class="btn btn-outline-secondary rounded small float-right py-1">예약취소</a>
					</div>
				</div>
			</li>
			</c:forEach>
		</ul>
	</div>
</div>
<script src="js/jquery-ui-1.11.4/jquery-ui.min.js"></script>
<script>
$(function() {
	$("#accordion").accordion();
})
</script>
<jsp:include page="../footer.jsp"/>
