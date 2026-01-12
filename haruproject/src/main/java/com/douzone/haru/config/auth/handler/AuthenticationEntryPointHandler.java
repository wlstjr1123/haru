package com.douzone.haru.config.auth.handler;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

// 스프링 시큐리티 컨텍스트 내에서 관리하기 위한 컨포넌트 빈등록
@Component
public class AuthenticationEntryPointHandler implements AuthenticationEntryPoint {
	
	@Override
	public void commence(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse,
			AuthenticationException authException) throws IOException, ServletException {
		
		
		System.out.println(httpServletRequest);
		// 쿼리를 제외한 프로토콜+도메인+포트번호+컨텍스트 경로+서블릿 경로를 구하고
		String request = httpServletRequest.getRequestURI();

		//httpServletResponse.sendRedirect("http://34.64.174.225:3000/");
		httpServletResponse.sendRedirect("http://127.0.0.1:3000/");
	}

}
