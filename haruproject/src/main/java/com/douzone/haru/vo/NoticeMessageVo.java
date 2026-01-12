package com.douzone.haru.vo;

public class NoticeMessageVo {
	private Long noticeNo;			// 알림번호
	private String noticeMessage;	// 알림 메세지
	private String noticLink;		// 알림 링크 --> 나중에 사용
	private String noticeDate;		// 알림 날자
	
	
	private String messageCk;		// 알림 체크
	
	
	
	public String getMessageCk() {
		return messageCk;
	}
	public void setMessageCk(String messageCk) {
		this.messageCk = messageCk;
	}
	public Long getNoticeNo() {
		return noticeNo;
	}
	public void setNoticeNo(Long noticeNo) {
		this.noticeNo = noticeNo;
	}
	public String getNoticeMessage() {
		return noticeMessage;
	}
	public void setNoticeMessage(String noticeMessage) {
		this.noticeMessage = noticeMessage;
	}
	public String getNoticLink() {
		return noticLink;
	}
	public void setNoticLink(String noticLink) {
		this.noticLink = noticLink;
	}
	public String getNoticeDate() {
		return noticeDate;
	}
	public void setNoticeDate(String noticeDate) {
		this.noticeDate = noticeDate;
	}
	@Override
	public String toString() {
		return "NoticeMessage [noticeNo=" + noticeNo + ", noticeMessage=" + noticeMessage + ", noticLink=" + noticLink
				+ ", noticeDate=" + noticeDate + "]";
	}
}
