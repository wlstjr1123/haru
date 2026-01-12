package com.douzone.haru.vo;

public class NoticeSetVo {
	private Long no;
	private String noticesetAssing;
	private String noticesetComment;
	public Long getNo() {
		return no;
	}
	public void setNo(Long no) {
		this.no = no;
	}
	public String getNoticesetAssing() {
		return noticesetAssing;
	}
	public void setNoticesetAssing(String noticesetAssing) {
		this.noticesetAssing = noticesetAssing;
	}
	public String getNoticesetComment() {
		return noticesetComment;
	}
	public void setNoticesetComment(String noticesetComment) {
		this.noticesetComment = noticesetComment;
	}
	@Override
	public String toString() {
		return "NoticeSetVo [no=" + no + ", noticesetAssing=" + noticesetAssing + ", noticesetComment="
				+ noticesetComment + "]";
	}
}
