<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<!doctype html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="_csrf_parameter" content="${_csrf.parameterName}" />
<meta name="_csrf_header" content="${_csrf.headerName}" />
<meta name="_csrf" content="${_csrf.token}" />
<title>JBlog</title>
<Link rel="stylesheet" href="${pageContext.request.contextPath }/assets/css/jblog.css">
<!-- 제이쿼리 함수 소환 -->
<script src="${pageContext.request.contextPath }/assets/js/jquery/jquery-1.9.0.js"></script>
<script>
$(function () {
    var token = $("meta[name='_csrf']").attr("content");
    var header = $("meta[name='_csrf_header']").attr("content");
    $(document).ajaxSend(function(e, xhr, options) {
        if(token && header) {
            xhr.setRequestHeader(header, token);
        }
    });
});

$(function(){
	// 아이디 중복 확인 이벤트
	$("#btn-check-id").click(function() {
		
		var token = $("meta[name='_csrf']").attr("content");
		var header = $("meta[name='_csrf_header']").attr("content");
           
	
		var useremail = $("#userEmail").val();
		if(useremail == '') {
			return;
		}
	

		
		$.ajax({
			url: "/haru/api/user/checkemail?email="+useremail,
			/* data : {"id": id}, */
			type: "post",
			dataType: "json",
			contentType : 'application/json',
			error:function(xhr, status, e){
				console.log(status, e);
			},
			success: function(response) {
				console.log(response);
				if(response.result !== "success"){
					console.error(response.message);
					return;
				}
				if(response.data) {
					alert("존재하는 이메일입니다. 다른 이메일을 사용하세요.");
					$("#userEmail")
						.val("")
						.focus();
					return;
				}
				
				$("#btn-check-id").hide();
				$("#img-check-id").show();
			}
		});		
	});
	
	// 서밋 버튼 눌렀을때 이메일 보내기
});
</script>
</head>
<body>
	<div class="center-content">
		<c:import url="/WEB-INF/views/includes/header.jsp"/>
		<form:form 
			modelAttribute="userVo"
			class="join-form" 
			id="join-form" 
			method="post" 
			action="${pageContext.request.contextPath }/join">
			<label class="block-label" for="userEmail">이메일</label>
			<form:input path="userEmail"/>
				 <p style="text-align:left; padding-left:0; color: #f00 "> 
					<spring:hasBindErrors name="userVo">
						<!-- name 이라는 필드에 에러가 있다면 오류내용 출력 -->
						<c:if test="${errors.hasFieldErrors('userEmail') }">
							<spring:message code="${errors.getFieldError('userEmail').codes[0] }" />
						</c:if>
					</spring:hasBindErrors>
				</p> 
			
			<input id="btn-check-id" type="button" value="중복체크">
			<img id="img-check-id" style="display: none;" src="${pageContext.request.contextPath }/assets/images/check.png">
			
			<label class="block-label" for="userName">이름</label>
			<form:input path="userName"/>
				 <p style="text-align:left; padding-left:0; color: #f00 "> 
					<spring:hasBindErrors name="userVo">
						<!-- name 이라는 필드에 에러가 있다면 오류내용 출력 -->
						<c:if test="${errors.hasFieldErrors('userName') }">
							<spring:message code="${errors.getFieldError('userName').codes[0] }" />
						</c:if>
					</spring:hasBindErrors>
				</p> 
			
			<label class="block-label" for="userPassword">패스워드</label>
			<form:input type="password" path="userPassword"/>
				 <p style="text-align:left; padding-left:0; color: #f00 "> 
					<spring:hasBindErrors name="userVo">
						<!-- name 이라는 필드에 에러가 있다면 오류내용 출력 -->
						<c:if test="${errors.hasFieldErrors('userPassword') }">
							<spring:message code="${errors.getFieldError('userPassword').codes[0] }" />
						</c:if>
					</spring:hasBindErrors>
				</p> 
			<br /><br />
			<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
			<input type="submit" value="가입하기" id="add-User" />
		</form:form>
		
	</div>
<!-- 	<div id="dialog-delete-form" title="메세지 삭제" style="display: none">
			<p class="validateTips normal">해당 카테고리의 포스트가 모두 삭제될수 있습니다.</p>
			<input type="hidden" id="hidden-no" value=""> 
		</div>
		<div id="dialog-message" title="새글작성" style="display: none">
			<p></p>
	</div> -->
</body>
</html>
