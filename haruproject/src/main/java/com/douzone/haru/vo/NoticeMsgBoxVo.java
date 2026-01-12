package com.douzone.haru.vo;

public class NoticeMsgBoxVo {
	private Long noticeNo; 		// notice_no 알림번호
	private Long userNo;   		// user_no 회원번호
	private String messageCk;	// message_ck ? 'Y' : 'N' 메세지 확인유무
	public Long getNoticeNo() {
		return noticeNo;
	}
	public void setNoticeNo(Long noticeNo) {
		this.noticeNo = noticeNo;
	}
	public Long getUserNo() {
		return userNo;
	}
	public void setUserNo(Long userNo) {
		this.userNo = userNo;
	}
	public String getMessageCk() {
		return messageCk;
	}
	public void setMessageCk(String messageCk) {
		this.messageCk = messageCk;
	}
	@Override
	public String toString() {
		return "NoticeMsgBox [noticeNo=" + noticeNo + ", userNo=" + userNo + ", messageCk=" + messageCk + "]";
	}
	
	
}
