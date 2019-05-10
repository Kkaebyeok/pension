<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="ko">
<head>
   <title>NolDaGa &mdash; 놀다가</title>
   <meta charset="utf-8">
   <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
   <link href="images/icon/favicon.ico" rel="icon">
   <link rel="stylesheet" href="css/bootstrap.css">
   <link rel="stylesheet" href="css/animate.css">
   <link rel="stylesheet" href="css/owl.carousel.min.css">
   <link rel="stylesheet" href="css/search.css">
   <link rel="stylesheet" href="fonts/ionicons/css/ionicons.min.css">
   <link rel="stylesheet" href="fonts/jua/css/jua.css">
   <link rel="stylesheet" href="fonts/fontawesome/css/font-awesome.min.css">
   <!-- Theme Style -->
   <link rel="stylesheet" href="css/style.css">
   <link rel="stylesheet" href="css/index.css">
   <link rel="stylesheet" href="css/common.css">
<script src="https://code.jquery.com/jquery-1.11.1.js"></script>
<style>
	.site-footer {top:0;}
</style>
</head>
<body>
<jsp:include page="header.jsp"/>
<div class="wrap">
	<div class="center title404">
		<h1 class="logo-text">NolDaGa</h1>
	<c:choose>
		<c:when test="${!empty notAuth}">
			<h2>인증이 완료되지 않은 회원입니다.</h2>
			<div class="progress-icon-wrap">
				<img class="progress-icon" src="images/icon/progress-icon-gif-1.jpg">
				<a href="#" id="sendMail">메일 재발송</a><br>
			</div>
			<a href="login">로그인</a>
			<script>
				$("#sendMail").click(function() {
					var $this = $(this);
					var email = '${email}';
					$.ajax({
						url: 'sendMail?email=' + email,
						beforeSend : function() {
							$this.hide();
							$(".progress-icon").show();
						},
						success : function(data) {
							if (data== 0) {
								alert("비정상적인 경로로 접속되었습니다.");
							}
							else if (data == 1) {
								alert("인증메일이 재발송 되었습니다.");
							}
						},
						error : function() {
							
						},
						complete : function() {
							$this.show();
							$(".progress-icon").hide();
						}
					})
				})
			</script>
		</c:when>
		<c:when test="${empty auth}">
			<h2>페이지를 찾을수 없습니다.</h2>
		</c:when>
		<c:when test="${auth == 'success'}">
				<h2>인증이 완료되었습니다.</h2>
				<a href="login">로그인</a>
		</c:when>
		<c:when test="${auth = 'null' || auth == 'error'}">
			<h2>잘못된 접근 방법입니다.</h2>
		</c:when>
	</c:choose>
	</div>
</div>
</body>
<jsp:include page="footer.jsp"/>
</html>