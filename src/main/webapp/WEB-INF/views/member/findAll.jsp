<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>findAll</title>
</head>
<body>
<h2>관리자 확인용 회원목록 페이지</h2>
<table>
	<tr>
		<th>아이디</th>
		<th>비밀번호</th>
		<th>이름</th>
		<th>이메일</th>
		<th>전화번호</th>
		<th>프로필 사진</th>
	</tr>
	<c:forEach items="${memberList}" var="member">
	<tr>
		<td>${member.m_id}</td>
		<td>${member.m_password}</td>
		<td>${member.m_name}</td>
		<td>${member.m_email}</td>
		<td>${member.m_phone}</td>
		<td>${member.m_profile}</td>
		<td><a href="/member/adminDelete?m_id=${m_id}">삭제</a></td>
	</tr>
	</c:forEach>
</table>

</body>
</html>