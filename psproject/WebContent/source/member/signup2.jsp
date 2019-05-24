<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<jsp:include page="../header.jsp"/>
<div class="wrap">
	<div class="content">
		<div class="join-wrapper">
			<div>
				<h1>회원가입</h1>
				<ul class="join_step">
					<li>
						<p>01</p>
						<p>약관동의</p>
					</li>
					<li class="join_on">
						<p>02</p>
						<p>회원유형</p>
					</li>
					<li>
						<p>03</p>
						<p>정보입력</p>
					</li>
					<li>
						<p>04</p>
						<p>가입완료</p>
					</li>
				</ul>
				<div class="join_choice">
					<div>
						<h3>일반회원 가입</h3>
						<p>개인가입을 원하시면 선택해주세요.</p>
						<a href="signup3?rating=0"><button>가입하기</button></a>
					</div><div>
						<h3>기업회원 가입</h3>
						<p>기업가입을 원하시면 선택해주세요.</p>
						<a href="signup3?rating=1"><button>가입하기</button></a>
					</div>
				</div>
			</div>
		</div>
	</div>
</div>
<jsp:include page="../footer.jsp"/>
