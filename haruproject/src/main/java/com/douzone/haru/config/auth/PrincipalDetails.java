package com.douzone.haru.config.auth;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.douzone.haru.vo.UserVo;

@SuppressWarnings("serial")
public class PrincipalDetails implements UserDetails { // UserDetails를 implement를 하면 PrincipalDetails가 UserDetails 와 같은
														// 타입이 되고 Authentication 객체에 담을
														// 수 있게 된다
	private UserVo userVo; // 콤포지션

	// 생성자를 만들어서
	public PrincipalDetails(UserVo userVo) {
		this.userVo = userVo;
	}
	
	// 해당 유저의 권한을 리턴하는 곳
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		//user.getUsername(); --> 이타입은 스트링 타입이기 때문에 리턴할수가 없다 시큐리티에서 객체타입을 정해놓았기 때문이다
		Collection<GrantedAuthority> collect = new ArrayList<>(); // 해당 객체의 어레이 리스트를 만들어서
		collect.add(new GrantedAuthority(){
			@Override
			public String getAuthority() {
				return null;
			}
		});
		
		return null;
	}
	

	public UserVo getUserVo() {
		return userVo;
	}
	public String getUserPhoto() {
		return userVo.getUserPhoto();
	}
	
	public Long getUserNo() {
		return userVo.getUserNo();
	}
	
	// 인증 유저 이름 넣어주기
	// 유저 no
	public String getUserName() {
		return userVo.getUserName();
	}
	
	// 비밀번호 셋팅
	@Override
	public String getPassword() {
		return userVo.getUserPassword();
	}

	// 사용자 이름 셋팅
	@Override
	public String getUsername() {
		return userVo.getUserEmail();
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	
	// 계정 만료 셋팅
	@Override
	public boolean isAccountNonLocked() {
		return true;
	}
	
	
	// 계정 활성화여부 비밀번호의 기간이 언제인지 확인 하는것
	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		
		// 우리 사이트에사 1냔동안 로그인을 안하면 휴먼 계정으로 하기로함
		// 현제시간-러긴시간
		
		return true;
	}
	// UserDetails 객체의 설정이 끝이 났다 Authentication 객체 설정을 하러가자
}
