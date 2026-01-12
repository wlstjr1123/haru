package com.douzone.haru.vo;

public class MessageBoxVo {
	private long noticeNo;
	private long userNo;
	private String messageCk;
	public long getNoticeNo() {
		return noticeNo;
	}
	public void setNoticeNo(long noticeNo) {
		this.noticeNo = noticeNo;
	}
	public long getUserNo() {
		return userNo;
	}
	public void setUserNo(long userNo) {
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
		return "MessageBoxVo [noticeNo=" + noticeNo + ", userNo=" + userNo + ", messageCk=" + messageCk + "]";
	}
	
	
}
