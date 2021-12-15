<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>BoardFindAll</title>
<script>
	function logout() {
		location.href="logout";
	}
</script>
</head>
<body>
<h2>글목록</h2>
<button onclick="logout()">로그아웃</button>

<a href="/member/myPage?m_id=${member.m_id}">마이페이지</a>
<a href="/board/boardForm">게시글 작성</a>
<c:if test="${sessionScope.loginId eq 'admin'}">
<a href="/member/admin">관리자 페이지</a>
</c:if>
	
	
	<form action="/board/search" method="get">
	<select name="searchtype">
		<option value="b_title">제목</option>
		<option value="b_writer">작성자</option>
	</select>
	<input type="text" name="keyword" placeholder="검색어..">
	<input type="submit" value="검색">
	</form>

	<table>
		<tr>
			<th>글번호</th>
			<th>작성자</th>
			<th>제목</th>
			<th>날짜</th>
			<th>조회수</th>
			<th>첨부파일</th>
		</tr>
		<c:forEach items="${boardList}" var="board">
		<tr>
			<td>${board.b_number}</td>
			<td>${board.b_writer}</td>
			<td><a href="/board/detail?b_number=${board.b_number}&page=${paging.page}">${board.b_title}</a></td>
			<td>${board.b_date}</td>
			<td>${board.b_hits}</td>
			<td><img alt="" src="/resources/upload"${board.b_filename}></td>
		</tr>
		</c:forEach>
	</table>
	
	<div>
		<c:choose>
			<c:when test="${paging.page<=1}">
				[이전]&nbsp;
			</c:when>
			<c:otherwise>
			<%-- 현재 페이지에서 1감소한 페이지 요청하는 링크 --%>
				<a href="/board/paging?page=${paging.page-1}">[이전]</a>&nbsp;
			</c:otherwise>
		</c:choose>
		
		<!-- for(int i = startPage; i <= endPage; i++) -->
		<c:forEach begin="${paging.startPage}" end="${paging.endPage}" var="i" step="1">
			<c:choose>
			<%-- i값이 현재페이지(page) 값과 같다면 --%>
				<c:when test="${i eq paging.page}">
					${i}
				</c:when>
				<c:otherwise>
					<a href="/board/paging?page=${i}">${i}</a>
				</c:otherwise>
			</c:choose>
		</c:forEach>
	
		<c:choose>
			<c:when test="${paging.page>=paging.maxPage}">
				[다음]
			</c:when>
			<c:otherwise>
				<a href="/board/paging?page=${paging.page+1}">[다음]</a>
			</c:otherwise>
		</c:choose>
	</div>
</body>
</html>