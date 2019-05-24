<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
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
				<a href="mypage" class="mt-2 btn btn-block btn-light bg-danger list-group-item text-light">정보수정 ></a>
			</li>
			<li>
				<a href="myreserve" class="mt-2 btn btn-block list-group-item text-danger">예약정보 ></a>
			</li>
		</ul>
	</div>
	<div class="float-right w-75 p-2 pl-5">
		<h3 class="text-center text-danger">회원정보 수정</h3>
		<form method="post" name="frm">
			<div class="mypage_input">
				<input type="hidden" name="email" value="${member.email}">
				<table class="w-100">
					<tr>
						<th class="w-25 p-3 text-center h4">성명</th>
						<td class="w-75 p-3"><input type="text" class="form-control" name="name" value="${member.name}" placeholder="이름을 입력해주세요"></td>
					</tr>
					<tr>
						<th class="w-25 p-3 text-center h4">주소지</th>
						<td class="w-75 p-3"><input type="text" class="form-control" name="address" value="${member.address}"></td>
					</tr>
					<tr>
						<th class="w-25 p-3 text-center h4">연락처</th>
						<td class="w-75 p-3"><input type="text" class="form-control" name="tel" value="${member.tel}"></td>
					</tr>
					<tr>
						<td colspan="2" class="p-3">
						<button class="btn btn-block btn-danger rounded" onclick="return confirm('정말 수정하시겠습니까?')">정보 수정</button>
						</td>
					</tr>
					<tr>
						<td colspan="3" class="p-3">
							<a href="resign"><button type="button" class="btn btn-block btn-secondary rounded" onclick="return confirm('정말 탈퇴하시겠습니까?')">회원 탈퇴</button></a>
						</td>
					</tr>
				</table>
			</div>
		</form>
	</div>
</div>
<jsp:include page="../footer.jsp"/>
