<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>BoardForm</title>
</head>
<body>
<h2>글작성 페이지</h2>
	<form action="/board/boardForm" method="post" enctype="multipart/form-data">
		작성자 : <input type="text" value="${board.b_writer}" readonly> <br>
		제목 : <input type="text" name="b_title"> <br>
		내용 : <textarea name="b_contents" rows="5"></textarea> <br>
		첨부파일 : <input type="file" name="b_file">
		<input type="submit" value="작성">
	</form>
</body>
</html>