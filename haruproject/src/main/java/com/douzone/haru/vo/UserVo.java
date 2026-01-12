package com.douzone.haru.vo;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

import org.hibernate.validator.constraints.Length;

public class UserVo {
	
	
	private Long userNo;	 	// 회원번호
	@NotEmpty
	private String userName; 	// 이름
	
	@NotEmpty
	@Email
	private String userEmail; 	// 이메일
	
	@NotEmpty
	@Length(min=2 , max=10)
	private String userPassword;// 비밀번호
	
	
	private String updatePassword; // 변경된 비밀번호
	private String userBg; 		// 배경사진
	private String userRegdate; // 가입일
	private String userTitle; 	// 직함
	private String userDept; 	// 부서
	private String userBirth;	// 생년월일
	private String userNumber; 	// 전화번호
	private String userPhoto; 	// 프로필사진
	private String userTimezone;// 시간설정
	private String userKeyState;// 인증확인
	private String userKey; // 회원 인증키
	public Long getUserNo() {
		return userNo;
	}
	public void setUserNo(Long userNo) {
		this.userNo = userNo;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getUserEmail() {
		return userEmail;
	}
	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}
	public String getUserPassword() {
		return userPassword;
	}
	public void setUserPassword(String userPassword) {
		this.userPassword = userPassword;
	}
	public String getUpdatePassword() {
		return updatePassword;
	}
	public void setUpdatePassword(String updatePassword) {
		this.updatePassword = updatePassword;
	}
	public String getUserBg() {
		return userBg;
	}
	public void setUserBg(String userBg) {
		this.userBg = userBg;
	}
	public String getUserRegdate() {
		return userRegdate;
	}
	public void setUserRegdate(String userRegdate) {
		this.userRegdate = userRegdate;
	}
	public String getUserTitle() {
		return userTitle;
	}
	public void setUserTitle(String userTitle) {
		this.userTitle = userTitle;
	}
	public String getUserDept() {
		return userDept;
	}
	public void setUserDept(String userDept) {
		this.userDept = userDept;
	}
	public String getUserBirth() {
		return userBirth;
	}
	public void setUserBirth(String userBirth) {
		this.userBirth = userBirth;
	}
	public String getUserNumber() {
		return userNumber;
	}
	public void setUserNumber(String userNumber) {
		this.userNumber = userNumber;
	}
	public String getUserPhoto() {
		return userPhoto;
	}
	public void setUserPhoto(String userPhoto) {
		this.userPhoto = userPhoto;
	}
	public String getUserTimezone() {
		return userTimezone;
	}
	public void setUserTimezone(String userTimezone) {
		this.userTimezone = userTimezone;
	}
	public String getUserKeyState() {
		return userKeyState;
	}
	public void setUserKeyState(String userKeyState) {
		this.userKeyState = userKeyState;
	}
	public String getUserKey() {
		return userKey;
	}
	public void setUserKey(String userKey) {
		this.userKey = userKey;
	}
	@Override
	public String toString() {
		return "UserVo [userNo=" + userNo + ", userName=" + userName + ", userEmail=" + userEmail + ", userPassword="
				+ userPassword + ", updatePassword=" + updatePassword + ", userBg=" + userBg + ", userRegdate="
				+ userRegdate + ", userTitle=" + userTitle + ", userDept=" + userDept + ", userBirth=" + userBirth
				+ ", userNumber=" + userNumber + ", userPhoto=" + userPhoto + ", userTimezone=" + userTimezone
				+ ", userKeyState=" + userKeyState + ", userKey=" + userKey + "]";
	}
	
	
}
