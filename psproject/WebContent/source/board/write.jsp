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
			<form method="post">
				<input type="hidden" name="idx" value="${vo.idx}">
				<table class="board-view-table content">
					<tr class="inputRow">
						<th>제목</th>
						<td colspan="3">
							<input type="text" name="title" value="${vo.title}">
						</td>
					</tr>
					<tr>
						<td colspan="4"><textarea name="cont">${vo.cont}</textarea></td>
					</tr>
					<tr>
						<td colspan="4">
							<button class="btn width100">글 반영</button>
						</td>
					</tr>
				</table>
			</form>
		</div>
	</div>
</div>
	<jsp:include page="../footer.jsp"/>
</body>
</html>