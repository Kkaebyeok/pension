<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html >
<html lang="ko">
<head>
	<jsp:include page="common/member_header.jsp"/>
	<c:if test="${param.message=='fail'}">
		<script>
			alert("로그인에 실패했습니다")
			location.href="login";
		</script>
	</c:if>
	<script>
	// 유효성 검증
	$(() => {
		$(document.frm).submit(function(e) {
			$(".err-msg").msgHide();
			
			var emailRegExp = /^[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*@[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*.[a-zA-Z]{2,3}$/i;
			
			if ($(this.email).val() == '') {
				$(this.email).next().msgShow("이메일을 입력하세요.");
				e.preventDefault();
			} 
			else if (!emailRegExp.test($(this.email).val())) {
				$(this.email).next().msgShow("유효한 이메일을 입력하세요.");
				e.preventDefault();
			}
			
			if ($(this.pw).val() == '') {
				$(this.pw).next().msgShow("비밀번호를 입력하세요.");
				e.preventDefault();
			}
			else if ($(this.pw).val().length < 4) {
				$(this.pw).next().msgShow("비밀번호를 4글자 이상 입력하세요.");
				e.preventDefault();
			}
		})
	});
	</script>
</head>
<body>
<div id="wrapper">
	<jsp:include page="../header.jsp"/>
		<section>
	        <div class="login_main title404">	
				<form method="post" name="frm">
			    	<h1 class="logo-text">NolDaGa</h1>
					<table class="login_table">
						<tr>
							<td>
								<input type="text" name="email" placeholder="이메일을 입력하세요." autofocus="autofocus" id="email">
								<p class="err-msg" data-target="email">여기에 텍스트</p>
							</td>
						</tr>
						<tr>
							<td>
								<input type="password" name="pw" placeholder="4글자 이상의 비밀번호를 입력하세요." id="pw">
								<p class="err-msg" data-target="pw">여기에 텍스트</p>
							</td>
						</tr>
						<tr>
							<td>
								<button class="login_button">LOGIN</button>
							</td>
						</tr>
					</table>
				</form>
	    	</div>
		</section>
	<jsp:include page="../footer.jsp"/>
</div>
</body>
</html>