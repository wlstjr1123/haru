package com.douzone.haru.vo;
/*
 * 작성자 : 이승현
 * - 이메일 전송시 필요한 vo
 * - DB 테이블 필요없음
 */
public class MailVo {
	private String message;
	private String address;
	private String title;
	@Override
	public String toString() {
		return "MailVo [address=" + address + ", title=" + title + ", message=" + message + "]";
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
}
