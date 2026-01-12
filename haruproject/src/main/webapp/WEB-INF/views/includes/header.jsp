<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
		<ul class="menu">
			<c:if test="${authUser == null }">
				<li><a href="${pageContext.request.contextPath }/loginForm">로그인</a></li>
			</c:if>
				<li><a href="${pageContext.request.contextPath }/joinForm">회원가입</a></li>		
		</ul>
		<c:if test="${authUser != null }">
			<form action="${pageContext.request.contextPath }/logout" method="post">
				<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
				<input type="submit" value="로그아웃"/>
			</form>
		</c:if>
