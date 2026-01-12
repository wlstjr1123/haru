package com.douzone.haru.config.auth.handler;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.http.server.ServletServerHttpResponse;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.WebAttributes;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.savedrequest.HttpSessionRequestCache;
import org.springframework.security.web.savedrequest.RequestCache;
import org.springframework.security.web.savedrequest.SavedRequest;

import com.douzone.haru.config.auth.PrincipalDetails;
import com.douzone.haru.dto.JsonResult;
import com.douzone.haru.vo.UserVo;

@Configuration
public class AuthFailureHendler implements AuthenticationFailureHandler {

	private RequestCache requestCache = new HttpSessionRequestCache();

	@Override
	public void onAuthenticationFailure(
			HttpServletRequest request, 
			HttpServletResponse response,
			AuthenticationException exception) throws IOException, ServletException {
		
		System.out.println(" [req,res ] " + request + " : " + response );
		
		SavedRequest savedRequest = requestCache.getRequest(request, response);

		System.out.println(" 로그인 실패 savedRequest : " + savedRequest);

		if (savedRequest != null) {
			requestCache.removeRequest(request, response);
			clearAuthenticationAttributes(request);
		}

		// 응답 해더 타입
		String accept = request.getHeader("accept");
		System.out.println(" 로그인 실패 accept : " + accept);

		UserDetails userDetails = new PrincipalDetails(null);

		if (SecurityContextHolder.getContext().getAuthentication() != null) {
			Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
			if (principal != null && principal instanceof UserDetails) {
				userDetails = (UserDetails) principal;
				System.out.println("로그인 실패 userDetails : " + userDetails);
			}
		}

		MappingJackson2HttpMessageConverter jsonConverter = new MappingJackson2HttpMessageConverter();
		MediaType jsonMimeType = MediaType.APPLICATION_JSON;

		System.out.println(" 로그인 실패 jsonMineType : " + jsonMimeType);

		JsonResult jsonResult = JsonResult.fail("이메일 또는 비밀번호가 틀렸습니다");
		
		System.out.println("로그인 실패 jsonResult : " + jsonResult);

		if (jsonConverter.canWrite(jsonResult.getClass(), jsonMimeType)) {
			jsonConverter.write(jsonResult, jsonMimeType, new ServletServerHttpResponse(response));
		}
		
		System.out.println("로그인 실패!!");;

	}

	// 스프링 시큐리티가 로그인 관련 로그인 실페후 세션에 남긴 에러를 지워주는 메소드
	private void clearAuthenticationAttributes(HttpServletRequest request) {
		HttpSession session = request.getSession(false);


		if (session == null) {
			System.out.println("[ session ]" + session);
			return;
		}

		//
		session.removeAttribute(WebAttributes.AUTHENTICATION_EXCEPTION);
	}

	public void setRequestCache(RequestCache requestCache) {
		System.out.println("[requestCache]" + requestCache);
		this.requestCache = requestCache;
	}

}
