package com.douzone.haru.controller.api;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.douzone.haru.config.auth.PrincipalDetails;
import com.douzone.haru.config.auth.scurity.AuthUser;
import com.douzone.haru.dto.JsonResult;
import com.douzone.haru.service.NoticeMessageService;
import com.douzone.haru.service.ProjectService;
import com.douzone.haru.service.TaskService;
import com.douzone.haru.vo.MessageBoxVo;
import com.douzone.haru.vo.NoticeMessageVo;
import com.douzone.haru.vo.TaskListVo;
import com.douzone.haru.vo.TaskVo;
import com.douzone.haru.vo.UserVo;

@CrossOrigin(origins = { "http://localhost:8080" })
@RestController
public class ApiTaskController {
	@Autowired
	TaskService taskService;

	@Autowired
	private ProjectService projectService;

	@Autowired
	private ApiNoticeSocket apiNoticeSocket;

	@Autowired
	private NoticeMessageService noticeMessageService;

	@PostMapping("/api/task/dropTask")
	public JsonResult dropTask(@RequestBody TaskListVo vo, @AuthUser PrincipalDetails principa) {

		long result = taskService.taskDropUpdate(vo);

		System.out.println("sadsaddsa" + result);
		System.out.println("sadsaddsa" + vo);

		if (result > 0) {
			List<UserVo> member = projectService.proejctmemberAlllistselect(vo.getProjectNo());

			if (member == null || member.size() <= 1) {
				return JsonResult.success(result);
			}

			apiNoticeSocket.taskMoveSend(vo, member, principa.getUserNo());

			return JsonResult.success(result);
		} else {
			return JsonResult.fail("데이터 업데이트 실패");
		}

	}

	@PostMapping("/api/task/add")
	@Transactional
	public JsonResult taskAdd(@RequestBody TaskVo vo, @AuthUser PrincipalDetails principalDetails) {
		long result = taskService.insertTask(vo);

		Map<String, Object> map = new HashMap<>();
		map.put("userNo", principalDetails.getUserNo());
		map.put("taskNo", result);
		System.out.println(result);
		System.out.println(result);
		
		long result2 = taskService.insertTaskUser(map);
		if (result > 0) {
			return JsonResult.success(result);
		} else {
			return JsonResult.fail("데이터 추가 실패");
		}
	}

	@PostMapping("/api/task/delete")
	public JsonResult taskDelete(@RequestBody Map<String, Object> map, @AuthUser PrincipalDetails principalDetails) {
		long result = taskService.taskDelete((Integer)map.get("taskCardItemId"));
		
		// 맴버 있는지 체크
		List<UserVo> member = projectService.proejctmemberAlllistselect((Integer) map.get("projectNo"));
		
		// 알림 추가
		if (member == null || member.size() <= 1) {
			return JsonResult.success(result);
		}
		
		String message = (principalDetails.getUserName()+"님이" 
						+ (String) map.get("projectTitle")) 
						+ "에 테스크를 삭제시켰습니다.";
		NoticeMessageVo messageVo = new NoticeMessageVo();
		messageVo.setNoticeMessage(message);
		messageVo = noticeMessageService.noticeInsert(messageVo);
		
		for (UserVo userVo : member) {
			if (userVo.getUserNo() != (principalDetails.getUserNo())) {
				MessageBoxVo mbVo = new MessageBoxVo();
				mbVo.setUserNo(userVo.getUserNo());
				mbVo.setNoticeNo(messageVo.getNoticeNo());

				noticeMessageService.noticeBoxInsert(mbVo);
			}
		}
		
		Map<String, Object> resultSend = new HashMap<>();
		resultSend.put("bellNo",messageVo.getNoticeNo());
		resultSend.put("bell", message);
		resultSend.put("data", (Integer)map.get("taskCardItemId"));
		resultSend.put("projectNo", (Integer) map.get("projectNo"));
		
		apiNoticeSocket.taskDeleteSend(resultSend, member,principalDetails.getUserNo());
		
		if (result > 0) {
			return JsonResult.success(result);
		} else {
			return JsonResult.fail("데이터 삭제 실패");
		}
	}

	@MessageMapping("/task/add")
	public void taskAddSend(Map<String, Object> socketData) {
		try {
			
			System.out.println("socketData"+socketData);
			
			// 맴버 있는지 체크
			List<UserVo> member = projectService.proejctmemberAlllistselect((Integer) socketData.get("projectNo"));

			// 알림 추가
			String message = ((String) socketData.get("userName")) + "님이" + ((String) socketData.get("projectTitle")) + "에 테스크를 추가시켰습니다.";
			NoticeMessageVo messageVo = new NoticeMessageVo();
			messageVo.setNoticeMessage(message);
			messageVo = noticeMessageService.noticeInsert(messageVo);

			// 맴버들에게 알림
			for (UserVo userVo : member) {
				if (userVo.getUserNo() != Integer.parseInt((String) socketData.get("userNo"))) {
					MessageBoxVo mbVo = new MessageBoxVo();
					mbVo.setUserNo(userVo.getUserNo());
					mbVo.setNoticeNo(messageVo.getNoticeNo());

					noticeMessageService.noticeBoxInsert(mbVo);
				}
			}

			if (member == null || member.size() <= 1) {
				return;
			}
			
			socketData.put("bellNo", messageVo.getNoticeNo());
			socketData.put("bell", message);
			
			apiNoticeSocket.taskaddSend(socketData, member, Integer.parseInt((String) socketData.get("userNo")));

		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
}
