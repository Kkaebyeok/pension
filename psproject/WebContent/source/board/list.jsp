<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<jsp:include page="../header.jsp"/>
<div class="wrap">
	<div class="board-wrapper">
		<h1>공지사항</h1>
		<table class="board-table content">
			<tr>
				<th>번호</th>
				<th>제목</th>
				<th>작성자</th>
				<th>작성일</th>
			</tr>
			<c:forEach items="${list}" var="board">
			<tr>
				<td>${board.idx}</td>
				<td class="text-overflow"><a href="boardView?idx=${board.idx}">${board.title}</a></td>
				<td>${fn:substringBefore(board.email,'@')}</td>
				<td>${board.regdate}</td>
			</tr>
			</c:forEach>
			<c:if test="${member.rating =='2'}">
			<tr>
				<td colspan="4"><a href="boardWrite"><button class="width100">작성</button></a></td>
			</tr>
			</c:if>
		</table>
	</div>
</div>
<jsp:include page="../footer.jsp"/>
