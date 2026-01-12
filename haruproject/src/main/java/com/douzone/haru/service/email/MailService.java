package com.douzone.haru.service.email;

import java.io.UnsupportedEncodingException;

import javax.mail.MessagingException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.douzone.haru.repository.UserRepository;
import com.douzone.haru.util.TempKey;
import com.douzone.haru.vo.UserVo;

/*
 * 작성자 : 이승현
 * 이메일 보낸는 서비스
 */
@Service
public class MailService {
	@Autowired
	private MailHandler sendMail;

	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;
	
	@Autowired
	private UserRepository userRepository;
	
	private static final String FROM_ADDRESS = "ghksxk1006@gmail.com";
	//private final String PATH_AND_PORT = "http://localhost:8080/haru/loginForm";
	private final String PATH_AND_PORT = "http://127.0.0.1:8080/haru/loginForm";
	
	// 신규 가입자 인증 메일 보내는 메소드
	public void mailSend(String emailAddress, String key) throws MessagingException, UnsupportedEncodingException {
		
		System.out.println("이메일 보내는중...신규가입자");
		
		String html = 
				"<table style='width:700px; margin-left:20px;'>"
				+"<tr>"
				+"</tr><tr><td><span>안녕하세요. <strong>HARU 프로젝트 회원가입 인증 이메일</strong> 입니다.<br>저희 사이트를 방문해 주셔서 감사드립니다.<br><br><strong>"
				+"</strong> 다음 url로 접속하면 인증이 완료됩니다. 감사합니다</span></td>" 
				+"</tr>"
				+"<tr><td><table style='margin-top:20px;'><col width='100px'><col width='200px'><tr style='border:1px solid gray; '>"
				+"<td style='background-color: #f5f6f5; color:#80878d'><a href='"+PATH_AND_PORT+"/"+key+"'>로그인 하러가기</a></td>"
				+"</tr>";

		sendMail.setSubject("[이메일 인증] HARU 프로젝트 인증메일 입니다.");
		sendMail.setText(html);
		sendMail.setFrom(FROM_ADDRESS, "하루 프로젝트");
		sendMail.setTo(emailAddress);
		sendMail.send(sendMail);
		System.out.println("신규가입자 이메일 전송됨");
	}
	
	// 비밀번호 변경 이메일 
	public void mailSendPassword(String emailAddress, String userName) throws MessagingException, UnsupportedEncodingException {
		
		// 임시비밀번호를 생성
		String key = new TempKey().getKey(20, false);
		UserVo vo = new UserVo();
		
		vo.setUserPassword(bCryptPasswordEncoder.encode(key));
		vo.setUserEmail(emailAddress);
		vo.setUserName(userName);
		
		System.out.println("새로 셋팅된 유저 객체 : "+vo);
		// 해당 유저의 id를 임시 비밀번호로 업데이트
		userRepository.updateUserPassword(vo);
		
		// 임시비밀번호를 보내주는 아이디 생성
		String html = 
				"<table style='width:700px; margin-left:20px;'>"
						+"<tr><td><span>안녕하세요. <strong>HARU 프로젝트</strong>입니다.<br>저희 사이트를 방문해 주셔서 감사드립니다.<br><br><strong>"
						+"</strong> HARU 에서 보낸 임시비밀번호는 다음과 같습니다.</span></td>" 
						+"</tr>"
						+"<tr><td><table style='margin-top:20px;'><col width='100px'><col width='200px'><tr style='border:1px solid gray; '>"
						+"<td style='background-color: #f5f6f5; color:#80878d'>임시 비밀번호</td><td>"+key+"</td>"
						+"</tr></table></td></tr><tr><td><span><br>변경된 비밀번호로 로그인하여 주세요.</td>"
						+"</tr></table>";

		System.out.println("[넘어온 이메일 내용]"+emailAddress);
		sendMail.setSubject("[이메일 인증] HARU 프로젝트 비밀번호 찾기 이메일 입니다.");
		sendMail.setText(html);
		sendMail.setFrom(FROM_ADDRESS, "하루 프로젝트");
		sendMail.setTo(emailAddress);
		sendMail.send(sendMail);
		System.err.println("이메일 전송됨");
	}
	
	// 신규 가입자 인증 메일 보내는 메소드
	public void projectInviteMailSend(String emailAddress, String key) throws MessagingException, UnsupportedEncodingException {
			
			
		String html = 
				"<table style='width:700px; margin-left:20px;'>"
				+"<tr>"
				+"</tr><tr><td><span>안녕하세요. <strong>" + key + " 프로젝트 초대 이메일</strong> 입니다.<br>저희 사이트를 방문해 주셔서 감사드립니다.<br><br><strong>"
				+"</strong> 하루 홈페이지가서 확인해주세요. 감사합니다</span></td>" 
				+"</tr>";

		sendMail.setSubject("[초대 이메일] HARU 프로젝트 초대메일 입니다.");
		sendMail.setText(html);
		sendMail.setFrom(FROM_ADDRESS, "하루 프로젝트");
		sendMail.setTo(emailAddress);
		sendMail.send(sendMail);
	}

}
