<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<jsp:include page="../header.jsp"/>
<script>
	var web_path = "${web_path}";
	var oridx = "${dao.oridx}";
	var psidx = '${dao.psidx}';
</script>
	<div class="wrap content">
		<div id="ex1" class="modal"></div> <!-- 이미지 상세보기  -->
		<div id="ex2" class="modal">
			<form name="scoreFrm" enctype="multipart/form-data">
			<input type = "hidden" name="psidx" value="${dao.psidx}">
			<div class="modal-body">
				<div class="form-group">
					<span>제목</span>
					<input type="text" placeholder="제목이 들어갈 곳" name="title" class="form-control">
				</div>
				<div class="form-gruop">
					<span>내용</span>
					<textarea placeholder="내용이 들어갈 곳" name="cont" class="form-control"></textarea>
				</div>
				<div class="form-gruop">
					<span>별점</span>
					<input type="hidden" name="score" value="1" id="score">
					<div class="input-star">
						<i class="fa fa-2x fa-star"></i><i class="fa fa-2x fa-star-o"></i><i class="fa fa-2x fa-star-o"></i><i class="fa fa-2x fa-star-o"></i><i class="fa fa-2x fa-star-o"></i>
					</div>
				<div class="form-gruop">
					<span>첨부 이미지</span><br>
					<input type = "file" accept="image/png, image/gif, image/jpeg, image/jpg" multiple="multiple" name="files">
				</div>
				</div>
			</div>
			<div class="modal-footer">
				<button class="btn btn-primary btn-sm rounded">작성</button>
				<button class="btn btn-warning btn-sm rounded">수정</button>
				<button class="btn btn-danger btn-sm rounded">삭제</button>
				<button class="btn btn-secondary btn-sm rounded">취소</button>
			</div>
			</form>
		</div>
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
			<%-- <c:if test="${member!=null}">
			<div class="info2">
				<form name="form1" method="get" action="insert.do">
					<input type="hidden" name="psidx" value="${dao.psidx}">
					<input type="button" value="예약현황">
					<input type="button" value="예약하기">
					<input type="submit" value="장바구니 담기">
				</form>
			</div>
			</c:if> --%>
		<hr>
		</div>
		<p><a href="#ex1" rel="modal:open" id="open"></a></p>
		<ul class="cont_tab">
			<li><a href="#anchorRoom">객실안내</a></li>
			<li><a href="#anchorMap">찾아오는 길</a></li>
			<li><a href="#anchorCancel">이용 및 취소안내</a></li>
			<li><a href="#anchorReview">이용후기</a></li>
		</ul>
		<div class="detail">
			<h3 id="anchorRoom" class="my-3">객실안내</h3>
			<div>
				<input type="text" name="daterange" class="text-center" value="${dao.startdate} ~ ${dao.enddate} / ${dao.days}박" id="daterange" />
			</div>
			<ul class="room">
			<c:forEach items="${list}" var="rm">
				<li class="p-2">
					<div class="img_wrap" data-rmidx="${rm.rmidx}"><img src="${web_path}/${rm.oridx}/${rm.rmidx}/main/1.jpg"></div>
					<div>
						<h4 class="h2 text-dark">${rm.rmtitle}</h4>
						<p>
							<c:if test="${rm.days > 1}">
							<span class="float-right m-1 text-info small">${rm.days}박 가능</span>
							</c:if>
							<span class="text-danger h4">${rm.avg}원</span>
							<c:if test="${rm.days > 1}">
							<span class="small">/ 1박</span>
							<span class="small float-right m-1">총 ${rm.tot} 원</span>
							</c:if>
						</p>
						<p><span class="text-dark">${rm.rmpermin}</span> <span class="small">인 기준 최대</span> <span class="text-dark">${rm.rmpermax}</span> <span class="small">인</span></p>
						<hr>
						<a id="reserveBtn" href="reserve?startDate=${dao.startdate}&endDate=${dao.enddate}&psidx=${dao.psidx}&rmidx=${rm.rmidx}">
							<c:if test="${empty member}">
								<button class="bg-secondart disabled" disabled="disabled">로그인 후 이용해주세요</button>
							</c:if>
							<c:if test="${not empty member}">
								<button>예약</button>
							</c:if>
						</a>
					</div>
				</li>
			</c:forEach>
			</ul>
			<h3 id="anchorMap" class="my-3">찾아 오시는길</h3>
			<jsp:include page="map.jsp"/>
			<h3 id="anchorCancel" class="my-3">이용안내</h3>
			<jsp:include page="pensioncancel.jsp"/>
			<h3 id="anchorReview">후기</h3>
			<div class="review">
				<div class="review-overall" id="overall"></div>
				<!-- 이후, 로그인상태, 펜션이용이력, 리뷰를 미작성 상태 -->
				<div class="review-overall"><button id="scoreRegBtn">후기작성</button></div>
				<div class="review-detail"></div>
			</div>
		</div>
	</div>
	<script>
	$('#daterange').dateRangePicker({
		customTopBar: ' ',
		separator: ' ~ ',
		format: 'YYYY-MM-DD',
		autoClose: true,
		singleMonth: true,
		language:'ko',
		applyBtnClass: 'applyBtn',
		hoveringTooltip: function(days) {
			return (days > 1 ? (days-1) : days) + '박';
		},
		startDate : moment().format('YYYY-MM-DD'), 
		endDate : moment().add(1, 'months').format('YYYY-MM-DD')
	})
	.on('datepicker-change', function(event, obj) {
		var start = moment(obj.date1).format('YYYY-MM-DD');
		var end = moment(obj.date2).format('YYYY-MM-DD');
		if (start == end) {
			end = moment(obj.date2).add('days', 1).format('YYYY-MM-DD');
		}
		$("#daterange").val(start + ' ~ ' + end);
		
		location.href = "detail.do?psidx=" + psidx + "&startdate=" + start + "&enddate=" + end + "&scroll=" + $(window).scrollTop();
	});
	
	var href = location.href;
	if(href.indexOf("scroll") != -1) {
		scroll = href.substr(href.indexOf("scroll=") + 7);
		$("body").animate({scrollTop:scroll},300);
	};
	
	showList();
	</script>
<jsp:include page="../footer.jsp"/>
