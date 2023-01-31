$('#writeBtn').click(function(){
	$('#nameDiv').empty();
	$('#idDiv').empty();
	$('#pwdDiv').empty();
	
	if(	$('#name').val()==""){
		$('#nameDiv').text("이름 내놔");
		$('#name').focus();
	}else if(	$('#id').val()==""){
	$('#idDiv').text("아이디 내놔");
		$('#id').focus();
	}else if(	$('#pwd').val()==""){
	$('#pwdDiv').text("비번 내놔");
		$('#pwd').focus();
	}else{
		$.ajax({
			type:"post",
			url:'/user/write',
			data:'name='+$('#name').val()+'&id='+$('#id').val()+'&pwd='+$('#pwd').val(),
			success:function(){
				alert("가입을 축하합니다.");
				location.href='/user/list';
			},
			error:function(err){
				console.log(err);
			}
			
		});
	
	}
	
	
})


//아이디 중복체크
$('#id').focusout(function(){

	$('#idDiv').empty();
	if(	$('#id').val()==""){
		$('#idDiv').text("필수정보입니다.");
		$('#id').focus();
	}else{
		$.ajax({
			type:'post',
			url:'/chapter06_SpringWebMaven/user/checkId',
			data:'id='+$('#id').val(),
			dataType:'text',
			success:function(data){
			alert(data);
				if(data=='exist'){
					$('#idDiv').text('사용 불가능');
				}else if(data=='non_exist'){
					$('#idDiv').text('사용 가능');
					$('#idDiv').css('color','blue');
				}
			},
			error:function(err){
				console.log(err);
			}
		})
	
	}
	
	
	
	
});

/*
{
'name':${'name'}
}

$('#writeForm').serialize()
*/

//아이디 중복체크 추가

/*
$.ajax({
			type:'post',
			url:"/chapter06_SpringWebMaven/user/exist",
			data:"id="+$(id),
			dataType:'text',
			success:function(data){
			
				
				
				if(data=='true'){
				alert("true옴");
				
				$('#idDiv').text("아이디가 중복됩니다.");
				$('#id').focus();
				}else if(data=='false'){
				
				alert("false옴");
				$('#idDiv').text("사용가능한 아이디 입니다.");
				$('#pwd').focus();
				}
				
				
			},
			error:function(err){
				console.log(err)
			}
			
	});

	
	*/
