package com.douzone.haru.vo;

//개인 일정 생성, 수정
public class CalendarVo {
	//개인 일정
	private Long scheduleNo; // 일정 no
	private Long userNo; //작성하는 유저no (User 테이블)
	private String scheduleStart; //개인 일정 생성일
	private String scheduleEnd; //개인 일정 마감일
	private String scheduleContents; //개인 일정 내용
	
	@Override
	public String toString() {
		return "CalendarVo [scheduleNo=" + scheduleNo + ", userNo=" + userNo + ", scheduleStart=" + scheduleStart
				+ ", scheduleEnd=" + scheduleEnd + ", scheduleContents=" + scheduleContents + "]";
	}
	public Long getScheduleNo() {
		return scheduleNo;
	}
	public void setScheduleNo(Long scheduleNo) {
		this.scheduleNo = scheduleNo;
	}
	public Long getUserNo() {
		return userNo;
	}
	public void setUserNo(Long userNo) {
		this.userNo = userNo;
	}
	public String getScheduleStart() {
		return scheduleStart;
	}
	public void setScheduleStart(String scheduleStart) {
		this.scheduleStart = scheduleStart;
	}
	public String getScheduleEnd() {
		return scheduleEnd;
	}
	public void setScheduleEnd(String scheduleEnd) {
		this.scheduleEnd = scheduleEnd;
	}
	public String getScheduleContents() {
		return scheduleContents;
	}
	public void setScheduleContents(String scheduleContents) {
		this.scheduleContents = scheduleContents;
	}
	
	
	
}
