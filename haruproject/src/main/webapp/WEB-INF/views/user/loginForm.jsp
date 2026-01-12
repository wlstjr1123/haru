<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<!doctype html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>JBlog</title>
<link rel="stylesheet" href="https://code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
<Link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/jblog.css">
<script type="text/javascript" src="${pageContext.request.contextPath}/assets/js/jquery/jquery-1.9.0.js"></script>
<script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script> <!-- dialog -->
</head>
<script>

var messageBox = function(title, message, callback) {

	$('#dialog-message').attr('title', title);
	$('#dialog-message p').text(message);

	$('#dialog-message').dialog({
		modal : true,
		buttons : {
			"환인" : function() {
				$(this).dialog("close");
			}

		},
		close : callback
	});
}

$(function(){
	// 삭제 다이알로그 객체 만들기
	var dialogEmail = $('#dialog-email-form')
			.dialog({
				autoOpen : false, // 바로 안뜨게 만들기
				modal : true,
				buttons : {
					"확인" : function() {
						// 삭제 ajax 실행
						var vo = {};
						
						/* vo.userid = ${'#inputid'}.val(); */
						email = $('#user-email').val();
						console.log(vo);
						// 때ㅔ려야될 url
						var url = "${pageContext.request.contextPath}/api/user/findPassword/";
							$.ajax({
									url : url,
									type : 'post',
									dataType : 'json',
									data : "email="+email,
									success : function(response) {
										console.log(response)
										
										if (response.data == "fail") {
											$('.validateTips.error').show();
											//$('#user-email').val('').focus();
											$('#user-email').val('').focus();
											return;
										}
										
										
										dialogEmail.dialog('close');
									}
								});
							},
							"닫기" : function() {
								//$('#user-email').val('');
								$('#user-email').val('');
								$(".validateTips.error").hide();
								$(this).dialog("close");
							}
						}
					});
	// 글삭제 버튼	
	$(document).on('click', '.login-form a', function(event) {
		event.preventDefault();
		console.log(this);

		dialogEmail.dialog('open');
	});
});
</script>
<body>
	<div class="center-content">
		<c:import url="/WEB-INF/views/includes/header.jsp"/>
		<form class="login-form" method="post" action="${pageContext.request.contextPath }/login">
		
    		<label class="block-label">아이디</label>
			<input id="id" name="userEmail" type="text" value="" placeholder="이메일">
			<label class="block-label" >패스워드</label>
			<input id="password" name="password" type="password" value="" placeholder="비밀번호">
			<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
			<a> 비밀번호가 기억나지 않나요?</a>
			<c:if test="${result == 'fail' }">
				<p>
					로그인에 실패 하였습니다.
				</p>
			</c:if>
      		<input type="submit" value="로그인">
		</form>
	</div>
	<div id="dialog-email-form" title="비밀번호 찾기" style="display: none">
		<p class="validateTips normal">이메일을 입력해주세요 <br /> 회원가입시 입력했던 이메일로<br />임시 비밀번호가 전송됩니다.</p>
		<br />
		<p class="validateTips error" style="display: none">존제하지 않는 이메일 입니다.</p>
		<hr />
		<br/>
		<form >
		<input type="text" id="user-email" value="" placeholder="아이디를 입력하세요"> 
		</form>
	</div>
</body>
</html>
