<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>myPage</title>
</head>
<body>
<h2>마이페이지</h2>
아이디 : ${member.m_id} <br>
비밀번호 : ${member.m_password} <br>
이름 : ${member.m_name} <br>
이메일 : ${member.m_email} <br>
전화번호 : ${member.m_phone} <br>
프로필 사진: ${member.m_profile} <br>
<a href="/member/update?m_id=${member.m_id}">수정</a>
</body>
</html>