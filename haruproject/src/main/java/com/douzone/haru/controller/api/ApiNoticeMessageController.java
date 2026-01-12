package com.douzone.haru.controller.api;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.douzone.haru.dto.JsonResult;
import com.douzone.haru.service.NoticeMessageService;
import com.douzone.haru.vo.NoticeMessageVo;

@RestController
@RequestMapping("/api/notice")
public class ApiNoticeMessageController {

	@Autowired
	NoticeMessageService noticeMessageService;

	
	@PostMapping("/getMyNotice")
	public JsonResult myNoticeSelect(@RequestBody long userNo) {
		List<NoticeMessageVo> list = noticeMessageService.myNoticeSelect(userNo);
		
		return JsonResult.success(list);
	}
	
	@PostMapping("noticeCheck")
	public JsonResult noticeCheck(@RequestBody Map<String, Object> map) {
		long result = noticeMessageService.noticeCheck(map);
		
		if (result > 0) {
			return JsonResult.success(result);
		} else {
			return JsonResult.fail("알림 체크 실패");
		}
	}
	
	@PostMapping("noticeAllCheck")
	public JsonResult noticeAllCheck(@RequestBody long UserNo) {
		long result = noticeMessageService.noticeAllCheck(UserNo);
		
		if (result > 0) {
			return JsonResult.success(result);
		} else {
			return JsonResult.fail("알림 체크 실패");
		}
	}
	
	@PostMapping("allRemove")
	public JsonResult allDelete(@RequestBody long userNo) {
		long result = noticeMessageService.allDelete(userNo);
		
		if (result > 0) {
			return JsonResult.success(result);
		} else {
			return JsonResult.fail("알림 삭제 실패");
		}
	}
	
	
}
