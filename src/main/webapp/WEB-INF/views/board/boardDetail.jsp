<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>BoardDetail</title>
<link href="/resources/css/test.css" rel="stylesheet">
<script src="https://code.jquery.com/jquery-3.6.0.js"></script>
</head>
<body>
<h2>상세조회 페이지</h2>
	글번호 : ${board.b_number} <br>
	작성자 : ${board.b_writer} <br>
	조회수 : ${board.b_hits}<br>
	날짜 : ${board.b_date}<br>
	제목 : ${board.b_title}<br>
	내용 : ${board.b_contents}<br>
	파일 : <img alt="" src="/resources/upload"${board.b_filename}>
	<c:if test="${sessionScope.loginId}">
	<a href="/board/update?b_number=${board.b_number}&page=${page}">수정</a>
	</c:if>
	<c:if test="${sessionScope.loginId} || ${sessionScope.loginId eq 'admin'}">
	<a href="/board/delete?b_number=${board.b_number}}">삭제</a>
	</c:if>
	<a href="/board/paging?page=${page}">글목록으로</a>
	
	<!-- 댓글 작성 -->
	<div id="comment-write">
		<input type="text" id="c_writer" value="${comment.c_writer}" readonly>
		<input type="text" id="c_contents" placeholder="댓글내용">
		<button id="comment-write-btn">댓글등록</button>
	</div>
	
	<!-- 댓글 목록 출력 -->
	<div id="comment-list">
		<table class="table">
			<tr>
				<th>댓글번호</th>
				<th>작성자</th>
				<th>내용</th>
				<th>작성시간</th>
			</tr>
			<c:forEach items="${commentList}" var="comment">
				<tr>
					<td>${comment.c_number}</td>
					<td>${comment.c_writer}</td>
					<td>${comment.c_contents}</td>
					<td>${comment.c_date}</td>
				</tr>
			</c:forEach>
		</table>
	</div>
	<script>
		/* const commentBtn = document.getElementById("comment-write-btn");
		commentBtn.addEventListener("click", function() {
			console.log("댓글등록버튼 클릭");
		}); */
		
		// jquery
		$("#comment-write-btn").click(function(){
			console.log("댓글등록버튼 클릭");
			
			// javascript
//			const commmentWriter = document.getElementById("c_writer").value;
//			const commmentContents = document.getElementById("c_contents").value;

			//jquery
			const commmentWriter = $("#c_writer").val();
			const commmentContents = $("#c_contents").val();
			const boardNumber = '${board.b_number}';

			$.ajax({
				type: 'post',
				url: '/comment/save',
				data: {
					'c_writer' : commentWriter,
					'c_contents' : commentContents,
					'b_number' : boardNumber},
				dataType: 'json',
				success: function(result) {
					console.log(result);
					let output = "<table class='table'>";
					output += "<tr><th>댓글번호</th>";
					output += "<th>작성자</th>";
					output += "<th>내용</th>";
					output += "<th>작성시간</th></tr>";
					for(let i in result){
						output += "<tr>";
						output += "<td>"+result[i].c_number+"</td>";
						output += "<td>"+result[i].c_writer+"</td>";
						output += "<td>"+result[i].c_contents+"</td>";
						output += "<td>"+result[i].c_date+"</td>";
						output += "</tr>";
					}
					output += "</table>";
					// id가 comment-list인 div에 출력
					document.getElementById('comment-list').innerHTML = output;
					// 댓글 입력창을 비움. 
					document.getElementById('c_writer').value='';
					document.getElementById('c_contents').value='';
				},
				error: function() {
					console.log("문제야 문제");
				}
			});
			
		});
	</script>

</body>
</html>