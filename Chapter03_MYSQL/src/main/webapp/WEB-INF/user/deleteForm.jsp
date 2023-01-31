<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

<h3><img src="../image/cafe.jpg" style="width:200px;heigth:300px;"alt="카페"
onclick= "location.href='../'" style="cursor:pointer;"/><br>회원정보 수정</h3>
		<p>
			삭제할 이름 입력
			<input type="text" id="searchId" placeholder="아이디를 입력하세요">
			<input type="button" id="searchIdBtn" value="검색">
<div id="resultDiv"></div>
	
	
	
<script type="text/javascript" src="http://code.jquery.com/jquery-3.6.1.min.js"></script>
<script type="text/javascript" src="../js/delete.js"></script>
</body>
</html>