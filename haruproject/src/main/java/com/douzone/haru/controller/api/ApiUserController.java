package com.douzone.haru.controller.api;

import java.io.UnsupportedEncodingException;

import javax.mail.MessagingException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.douzone.haru.config.auth.PrincipalDetails;
import com.douzone.haru.config.auth.scurity.AuthUser;
import com.douzone.haru.dto.JsonResult;
import com.douzone.haru.exception.ImgUploadServiceException;
import com.douzone.haru.service.UserService;
import com.douzone.haru.service.email.MailService;
import com.douzone.haru.util.TempKey;
import com.douzone.haru.vo.UserVo;

/*
 * 작성자 : 이승현
 *  - 회원가입, 아이디 중복 체크, 
 */
@RestController
@RequestMapping("/user")
public class ApiUserController {

	@Autowired
	private UserService userService;

	@Autowired
	private MailService mailService;

	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;

	// 세션에 담긴 사용자 테스트 컨트롤러
	@GetMapping("/test")
	public @ResponseBody JsonResult test(@AuthUser PrincipalDetails principalDetails) {
		return JsonResult.success(principalDetails.getUserVo());
	}

	@PostMapping("/join")
	public JsonResult join(@RequestBody UserVo userVo) {
		System.out.println("회원가입 누름");
		// 비밀번호 해싱
		userVo.setUserPassword(bCryptPasswordEncoder.encode(userVo.getUserPassword()));

		// 이메일 보내기
		try {
			String key = new TempKey().getKey(50, false);
			userVo.setUserKey(key);
			userService.addUser(userVo);
			try {
				mailService.mailSend(userVo.getUserEmail(), key);
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}
		} catch (MessagingException e) {
			e.printStackTrace();
		}

		return JsonResult.success(userVo != null);
	}

	@PostMapping("/checkPassword")
	public JsonResult checkPassword(@RequestBody UserVo userVo, @AuthUser PrincipalDetails principalDetails) {

		System.out.println("[변경할 비밀번호] : " + userVo.getUpdatePassword());

		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

		boolean data = false;

		if (encoder.matches(userVo.getUserPassword(), principalDetails.getPassword())) {
			System.out.println("두개의 비밀번호가 동일함");

			UserVo vo = new UserVo();

			vo.setUserNo(principalDetails.getUserNo());
			vo.setUserPassword(encoder.encode(userVo.getUpdatePassword()));
			vo.setUserEmail(principalDetails.getUsername());

			data = userService.updatePassword(vo);
			System.out.println(data);
		}
		return JsonResult.success(data);

	}

	// 유저삭제 컨트롤저 상태만 F 로만들어서 로그인 못하게하기
	@PostMapping("/deleteUser")
	public JsonResult deleteUser(@RequestBody UserVo userVo, @AuthUser PrincipalDetails principalDetails) {
		
		
		System.out.println("[계정삭제 하러 들어온 정보] " +userVo);
		boolean data = false;

		if (bCryptPasswordEncoder.matches(userVo.getUserPassword(), principalDetails.getPassword())) {
			System.out.println("두개의 비밀번호가 동일함");

			UserVo vo = new UserVo();

			vo.setUserNo(principalDetails.getUserNo());
			vo.setUserEmail(principalDetails.getUsername());
			vo.setUserPassword(principalDetails.getPassword());
			data = userService.deleteUser(vo);
		}

		return JsonResult.success(data);
	}

	@PostMapping("/checkemail")
	public JsonResult checkid(@RequestBody UserVo vo) {
		UserVo userVo = userService.findByUsername(vo.getUserEmail());
		return JsonResult.success(userVo != null);

	}

	// 유저정보 찾는 핸들러
	@PostMapping("/findUserProfile")
	public JsonResult findUserProfile(@RequestBody UserVo vo) {
		UserVo userVo = new UserVo();
		userVo = userService.findUserProfile(vo.getUserEmail());
		return JsonResult.success(userVo);
	}
	
//	@PostMapping("/find/findUserProfile")
//	public JsonResult firstfindUserProfile(@RequestBody UserVo vo) {
//		UserVo userVo = new UserVo();
//		userVo = userService.findUserProfile(vo.getUserEmail());
//		return JsonResult.success(userVo);
//	}

	// 프로필 수정 핸들러
	@PostMapping("/ChangeProfile")
	public JsonResult ChangeProfile(@RequestBody UserVo vo) {
		System.out.println("프로필 수정중...");
		boolean data = userService.updateProfile(vo);
		return JsonResult.success(data);
	}

	@PostMapping("/uploadfile")
	public JsonResult uploadingImf(@AuthUser PrincipalDetails vo,
			@RequestParam(value = "userfile", required = false) MultipartFile multipartFile) {

		UserVo userVo = new UserVo();
		userVo.setUserEmail(vo.getUsername());
		try {
			String uploadImg = userService.ImageUpload(multipartFile);
			userVo.setUserPhoto(uploadImg);
		} catch (ImgUploadServiceException e) {
			System.out.println("이미지가 업로드 되지 않았습니다");
		}

		boolean result = userService.updateProfileImg(userVo);
		;

		return JsonResult.success(result);
	}

	// 비밀번호 찾기 헨들러
	@PostMapping("/findPassword")
	public JsonResult findPassword(@RequestBody String email) {

		System.out.println("이메일 보내는중...");
		UserVo vo = userService.findEmailById(email);
		System.out.println("[ 이메일이 유효하기 않음] :"+ vo );
		String date = "";

		if (vo != null) {
			// 존제하지 않는 이메일 이면
			// 존제하는 이메일일 경우 해당 이메일로 이메일 보내기
			try {
				// 이메일 서비스로 메일 보내기
				mailService.mailSendPassword(vo.getUserEmail(), vo.getUserName());
			} catch (UnsupportedEncodingException | MessagingException e) {
				e.printStackTrace();
			}
			date = vo.getUserEmail();
		} else {
			date = "fail";
		}

		return JsonResult.success(date);
	}
}
