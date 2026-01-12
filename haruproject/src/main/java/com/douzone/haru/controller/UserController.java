package com.douzone.haru.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.douzone.haru.config.auth.PrincipalDetails;
import com.douzone.haru.config.auth.scurity.AuthUser;
import com.douzone.haru.service.UserService;
import com.douzone.haru.service.email.MailService;
import com.douzone.haru.vo.UserVo;

/*
 *  작성자 : 이승현
 *  - user Controller
 *  - 회원가입, 비밀번호 변경 
 *  
 */
@Controller
@RequestMapping({"","/"})
public class UserController {
	

	@Autowired
	UserService userService;
	
	@Autowired
	MailService mailService;
	
	// 콘솔에 로그아웃 확인용
	@GetMapping("/logoutForm")
	public String logout(@AuthUser PrincipalDetails principalDetails) {
		System.out.println("로그 아웃됨 : " + principalDetails);
		return null;
	}
	
	@GetMapping("/loginForm/{key}")
	public String joinForm(
			@PathVariable("key") String key,
			Model model) {
		
		UserVo authUser = userService.findUserBykey(key);
		
		if(authUser != null) {
			userService.updateUserAuth(authUser);
		}
		
		return "redirect:http://127.0.0.1:3000/";
	}
}
