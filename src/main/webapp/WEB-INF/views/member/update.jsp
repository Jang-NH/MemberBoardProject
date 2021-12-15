<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>update</title>
<script>
/* 비밀번호 입력창에서 비밀번호 입력받고 DB에서 가져온 비밀번호와 비교해서 일치하면 update 처리 일치하지 않으면
 * 비밀번호가 일치하지 않습니다 alert 출력
 */
	function update() {
		// 입력받은 pw
		const pw = document.getElementById('m_password').value; // input에 id 부여해야함.
		// DB에서 가져온 pw	
		const pwDB = '${member.m_password}';
		if(pw == pwDB) {			
			// 이 문장이 아래 form을 전송하는 문장
			// form 태그 전체(name="update_form")를 제출(submit)한다.
			update_form.submit();
		} else {
			alert('비밀번호가 일치하지 않습니다.');
		}
	}
</script>
</head>
<body>
<h2>마이페이지 수정</h2>
	<form action="/member/update" method="post" name="update_form">
		아이디 : <input type="text" name="m_id" value="${member.m_id}" readonly>
		비밀번호 : <input type="password" name="m_password" id="m_password">
		이름 : <input type="text" name="m_name" value="${member.m_name}" readonly>
		이메일 : <input type="text" name="m_email" value="${member.m_email}">
		전화번호 : <input type="text" name="m_phone" value="${member.m_phone}">
		<input type="button" value="수정" onclick="update()">
	</form>
</body>
</html>