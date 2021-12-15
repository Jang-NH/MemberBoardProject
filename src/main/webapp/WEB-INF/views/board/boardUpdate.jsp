<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>BoardUpdate</title>
<script>
	function update() {
		update_form.submit();
	}
</script>
</head>
<body>
<h2>글수정</h2>
	<form action="/board/update" method="post" name="update_form">
		<input type="hidden" name="page" value="${page}"> <br>
		<input type="hidden" name="b_number" value="${board.b_number}">
		작성자 : <input type="text" name="b_writer" value="${board.b_writer}" readonly> <br>
		제목 : <input type="text" name="b_title" value="${board.b_title}"> <br>
		내용 : <textarea name="b_contents" rows="5">${board.b_contents}</textarea> <br>
		<input type="button" value="수정" onclick="update()">
		
	</form>
</body>
</html>