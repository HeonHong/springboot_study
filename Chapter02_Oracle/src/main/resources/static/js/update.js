//아이디 존재여부 체크
$(function(){
	$('#updateDiv').hide();


//아이디 찾기
$('#searchIdBtn').click(function(){
	$('#resultDiv').empty();
	$.ajax({
		type:'post',
		url:'/user/getUser',
		data:'id='+$('#searchId').val(),
		//dataType:'json', 데이터 타입을 빼버리면 UserController에서 주는대로 받는다.
		//서버에서 받아오는 데이터 타입은 text html xml json 형식을 지정할 수 있다.
		//getUser에서 id를 찾으면 JSON으로 오지만 id가 없으면 JSON으로 오지를 못한다.
		//그래서 dataType을 생략해야한다.(dataType을 생략하면 자료에 맞게 자동으로 형식이 지정된다.)
		success:function(data){
			//alert(JSON.stringify(data));
			if(data==''){
				$('#updateDiv').hide();
				$('#resultDiv').text('찾고자 하는 아이디가 없습니다.');
				$('#resultDiv').css('color','red');
				$('#resultDiv').css('font-weight','bold');
			}else{
				$('#updateDiv').show();
				$('#name').val(data.name);
				$('#id').val(data.id);
				//$('#pwd').val(data.pwd);
			}
			
		
		},
		error:function(err){
			console.log(err);
		}
		
	})//$.ajax
});

})


//취소버튼
$('#resetBtn').click(function(){
//강제로 이벤트 발생
	$('#searchIdBtn').trigger('click'); //클릭버튼을 누르는 효과를 강제로 주는 이벤트
});

$('#updateBtn').click(function(){
	$('#nameDiv').empty();
	$('#pwdDiv').empty();
	
	if(	$('#name').val()==""){
		$('#nameDiv').text("이름 내놔");
		$('#name').focus();
	}else if(	$('#pwd').val()==""){
		$('#pwdDiv').text("비번 내놔");
		$('#pwd').focus();
	}else{
	
		$.ajax({
			type:'post',
			url:'/user/update',
			data:$('#updateForm').serialize(),
			success:function(){
					alert("회원정보 수정 완료.");
					location.href='/user/list';
			},
			error:function(err){
				console.log(err);
			}
		});//ajax
	
	}

})




/*
$('#result').focusout(function(){

	$('#resultDiv').empty();
	if(	$('#result').val()==""){
		$('#resultDiv').text("필수정보입니다.");
		$('#resultDiv').css('color','red');
		$('#result').focus();
	}else{
		$.ajax({
			type:'post',
			url:'/chapter06_SpringWebMaven/user/checkId',
			data:'id='+$('#result').val(),
			dataType:'text',
			success:function(data){
			alert(data);
				if(data=='exist'){
					$('#resultDiv').text('아이디 존재합니다.');
					$('#resultDiv').css('color','blue');
				}else if(data=='non_exist'){
					$('#resultDiv').text('아이디가 없습니다.');
					$('#resultDiv').css('color','red');
				}
			},
			error:function(err){
				console.log(err);
			}
		})
	
	}
	
});
*/