package com.douzone.haru.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.AuthenticationEntryPoint;

import com.douzone.haru.config.auth.handler.AuthFailureHendler;
import com.douzone.haru.config.auth.handler.AuthSuccessHandler;

/*
 *  작성자 : 이승현
 *   - 스프링 시큐리티 컨피그 파일 작성
 *   - login 성공/실패 헨들러 등록 
 */


@Configuration 
@EnableWebSecurity 
public class SecurityConfig extends WebSecurityConfigurerAdapter{
	
	@Autowired
	private UserDetailsService userDetailsService;
	
	@Autowired
	private AuthFailureHendler authFailureHendler;
	
	@Autowired
	private AuthSuccessHandler authSuccessHandler;
	
	@Autowired
	private AuthenticationEntryPoint AuthenticationEntryPointHandler;
	
	@Autowired
	private CorsConfig corsConfig; 
	
	@Bean
	public BCryptPasswordEncoder encodePwd() {
		return new BCryptPasswordEncoder();
	}
	
	// resources/static 디렉터리 하위 파일 목록은 인증 무시(항상 통과) 언젠가 쓰일것을 대비
	@Override
    public void configure(WebSecurity web)throws Exception{
        web.ignoring().antMatchers("/css/**", "/js/**", "/img/**", "/lib/**", "/assets/upUserimages/**");
    }
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {

		http.csrf().disable();// Spring Security에서 제공하는 CSRF protection 기능을 일단 정지
		http.addFilter(corsConfig.corsFilter());
		http.authorizeRequests()
			.antMatchers("/**/api/**").authenticated()
			.antMatchers("/**/notice/getMyNotice").authenticated()
			.antMatchers("/pages/events").authenticated()
			.antMatchers("/haru/api/project/").authenticated()
			.antMatchers("/user/test/**").authenticated()
			.antMatchers("/user/ChangeProfile/**").authenticated()		// antMatchers 이 url은 인증이 필요하다는뜻
			.antMatchers("/user/findUserProfile").authenticated()		// antMatchers 이 url은 인증이 필요하다는뜻
			.antMatchers("/user/uploadfile/**").authenticated()		// antMatchers 이 url은 인증이 필요하다는뜻
			.anyRequest().permitAll()						// 위 세가지의 요청이외에는 모두 권한을 허가하게 만듬
			.and()
			.formLogin()
				.usernameParameter("userEmail")
				.passwordParameter("userPassword")
				.loginProcessingUrl("/api/login")			// login 주소가 호출이 되면 시큐리티가 낙아채서 대신 로그인을 진행 해준다
				.failureHandler(authFailureHendler)
				.successHandler(authSuccessHandler)
				.permitAll()
			.and()
			.logout()
				.logoutUrl("/api/logout")
				.logoutSuccessUrl("/logoutForm")
				.permitAll()
			.and()
			.exceptionHandling()
			.authenticationEntryPoint(AuthenticationEntryPointHandler)
			.and()
				.cors();
	}
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception{
		auth
			.userDetailsService(userDetailsService)
			.passwordEncoder(encodePwd());
	
	}	
}
