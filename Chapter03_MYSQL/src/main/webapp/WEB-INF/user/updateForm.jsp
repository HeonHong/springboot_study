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
onclick= "location.href='../'" style="cursor:pointer;"/><br>아이디 수정</h3>
		<p>
			수정할 이름 입력
			<input type="text" id="searchId" placeholder="아이디를 입력하세요">
			<input type="button" id="searchIdBtn" value="검색">
			<div id="resultDiv"></div>
	
		</p>
	<br>
	<br>
	<div id="updateDiv">
		<form id="updateForm">
	<table border="1px">
	<tr>
		<td>이름</td>
		<td>
			<input type="text" name = "name" id="name">
			
		</td>
	</tr>
	<tr>
		<td>아이디</td>
		<td>
			<input type="text" name = "id" id="id" readonly/>
		</td>
	</tr>
	<tr>
		<td>비밀번호</td>
		<td>
			<input type = "password" name = "pwd" id="pwd"/>
			<div id="pwdDiv"></div>
		</td>
	</tr>
	<tr>
		<td colspan="2" align="center">
			<input type ="button" value="수정" id="updateBtn">
			<input type="reset" value="취소" id="resetBtn" >
			<!-- 먼저 발생되고 이벤트 발생 -->
			<!-- onclick="location.reload();" -->
		</td>
	</tr>
	</table>
</form>
	</div>
	
<script type="text/javascript" src="http://code.jquery.com/jquery-3.6.1.min.js"></script>
<script type="text/javascript" src="../js/update.js"></script>
</body>
</html>