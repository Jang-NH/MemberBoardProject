<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>save</title>
<script src="https://code.jquery.com/jquery-3.6.0.js"></script>
<script type="text/javascript">
	function idDuplicate() {
		const id = document.getElementById('m_id').value;
		const idCheckResult = document.getElementById('id-dup-check');
		$.ajax({
			type: 'post',
			url: '/member/idDuplicate',
			data: {'m_id' : id},
			dataType: 'text',
			success : function(result) {
				if(result == "ok") {
					idCheckResult.style.color = 'green';
					idCheckResult.innerHTML = '멋진 아이디네요!';
				} else {
					idCheckResult.style.color = 'red';
					idCheckResult.innerHTML = '이미 사용중인 아이디입니다.';
				}
			},
			error : function() {
				
			}
		});
	}
</script>
</head>
<body>
<h2>회원가입 페이지</h2>
<form action="save" method="post" enctype="multipart/form-data">
	아이디 : <input type="text" name="m_id" id="m_id" onkeyup="idDuplicate()"> <br>
	<span id="id-dup-check"></span> <br>
	비밀번호 : <input type="password" name="m_password"> <br>
	이름 : <input type="text" name="m_name"> <br>
	이메일 : <input type="text" name="m_email"> <br>
	전화번호 : <input type="text" name="m_phone"> <br>
	프로필 사진 : <input type="file" name="m_profile"> <br>
	<input type="submit" value="회원가입">
</form>

</body>
</html>