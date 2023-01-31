<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style>
#img{
	visibility:hidden;
}

</style>

</head>
<body> 
<%-- html주석 걸면 읽히기 때문에 jsp주석으로 해놔야 한다.
<!-- 1. 단순 Submit -->
<form method="post" enctype="multipart/form-data" action="/chapter06_SpringWebMaven/user/upload">
<!-- 기본형 enctype="application/x-www-form-urlencoded"은 업로드가 되지 않는다 -->
	<!-- 한 개만 보내기<input type="file" name="img"></input> -->

	
	<!-- 파일이 여러개 들어오게 -->
	
	각각 여러개 파일
	<br>
	<input type="file" name="img"></input>
	<input type="file" name="img"></input>	
		<!-- 같은 이름으로 설정 -->
	<br>
	<br>
	

	한 번에 여러개 파일 선택
	<br>
	<img alt="카메라" id = "camera" src="../image/icon.png" width="100px" height="100px">
	<input type="file" name="img[]" multiple>
	<br>
	<br>
	
	<input type="submit" id="uploadBtn" value="이미지 등록">
	
</form>

<script type="text/javascript" src="http://code.jquery.com/jquery-3.6.1.min.js"></script>
<script type="text/javascript">
$('#camera').click(function(){
	$('#img').trigger('click');
	
})
</script>
--%>	
	
<!-- 2. Ajax통신  -->
<form id="uploadForm">
	<div id="imgDiv"></div>
	<img id="showImg" width="300px"><br><!--업로드 버튼 누르기 전에 카메라 아이콘을 통해서 선택한 이미지가 맞는지 확인하기 위해 -->
	<img alt="카메라" id = "camera" src="../image/icon.png" width="100px" height="100px"><br>
	
	<input type="file" name="img" id="img">
	<br><br>
	
	
	<input type="button" id="uploadBtn" value="이미지 등록"><!-- storage에 업로드 하기위한 버튼 -->
</form>

<script type="text/javascript" src="http://code.jquery.com/jquery-3.6.1.min.js"></script>
<script type="text/javascript">
$('#camera').click(function(){
	$('#img').trigger('click');
})

$('#img').on('change',function(){
	readURL(this);
	
});

function readURL(input){
	if(input.files[0]){
		var reader =new FileReader();
		reader.onload = function(e){//e.target:이벤트가 발생하는 요소를 반환해준다.
			$('#showImg').attr("src",e.target.result);
		}
		reader.readAsDataURL(input.files[0]);
	}//if
	
}
</script>
<script type ="text/javascript" src="../js/upload.js"></script>

</body>
</html>

<!--
FileReader란?
FileReader는 type이 file인 input 태그 또는 API 요청과 같은 인터페이스를 통해 
File 또는 Blob 객체를 편리하게 처리할수있는 방법을 제공하는 객체이며
abort, load, error와 같은 이벤트에서 발생한프로세스를 처리하는데 주로 사용되며,  
File 또는 Blob 객체를 읽어서 result 속성에 저장한다.

FileReader도 비동기로동작한다.

FileReader.onload()
load 이벤트의 핸들러. 이 이벤트는 읽기 동작이 성공적으로 완료되었을 때마다 발생한다.


  -->


<!-- 
processData
 - 기본값은 true
 - 기본적으로 Query String으로 변환해서 보내진다('변수=값&변수=값')
 - 파일 전송시에는 반드시 false로 해야 한다.(formData를 문자열로 변환하지 않는다)
 
contentType
  - 기본적으로 "application/x-www-form-urlencoded; charset=UTF-8"
  - 파일 전송시에는 'multipart/form-data'로 전송이 될 수 있도록 false로 설정한다
 -->
