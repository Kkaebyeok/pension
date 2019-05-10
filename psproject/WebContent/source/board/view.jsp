<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<!doctype html>
<html lang="ko">
<head>
<jsp:include page="common/board_header.jsp"/>
</head>
<body>
	<jsp:include page="../header.jsp"/>
<div class="wrap">
	<div class="position">
		<div class="wrapper">
			<h1>공지사항</h1>
			<table class="board-view-table content">
				<tr>
					<th>제목</th><td colspan="3">${vo.title}</td>
				</tr>
				<tr>
					<th>작성자</th><td>${vo.email}</td>
					<th>작성일</th><td>${vo.regdate}</td>
				</tr>
				<tr>
					<td colspan="4">${vo.cont}</td>
				</tr>
				<tr>
					<td colspan="4">
					<c:if test="${member.rating =='2'}">
						<a href="boardModify?idx=${vo.idx}"><button class="btn btn-warning width100">수정</button></a>
						<a href="boardRemove?idx=${vo.idx}"><button class="btn btn-danger width100">삭제</button></a>
					</c:if>
						<a href="boardList"><button class="btn width100">뒤로가기</button></a>
					</td>
				</tr>
			</table>
		</div>
	</div>
</div>
	<jsp:include page="../footer.jsp"/>
</body>
</html>