package com.douzone.haru.vo;

//종윤
public class TagListVo {
  
	private Long taskNo; // 업무번호
	private Long tagNo; // 태그번호
	private String tagName; // 태그이름
	private String tagColor; // 태그색상

	public Long getTagNo() {
		return tagNo;
	}

	public void setTagNo(Long tagNo) {
		this.tagNo = tagNo;
	}

	public String getTagName() {
		return tagName;
	}

	public void setTagName(String tagName) {
		this.tagName = tagName;
	}

	public String getTagColor() {
		return tagColor;
	}

	public void setTagColor(String tagColor) {
		this.tagColor = tagColor;
	}

	public Long getTaskNo() {
		return taskNo;
	}

	public void setTaskNo(Long taskNo) {
		this.taskNo = taskNo;
	}

	@Override
	public String toString() {
		return "TagListVo [tagNo=" + tagNo + ", tagName=" + tagName + ", tagColor=" + tagColor + ", taskNo=" + taskNo
				+ "]";
	}

}
