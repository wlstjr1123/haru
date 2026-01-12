package com.douzone.haru.controller.api;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.mail.MessagingException;
import javax.naming.spi.DirStateFactory.Result;
import javax.security.auth.message.callback.PrivateKeyCallback.Request;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.douzone.haru.config.auth.PrincipalDetails;
import com.douzone.haru.config.auth.scurity.AuthUser;
import com.douzone.haru.dto.JsonResult;
import com.douzone.haru.service.NoticeMessageService;
import com.douzone.haru.service.ProjectService;
import com.douzone.haru.service.TaskService;
import com.douzone.haru.service.TasklistService;
import com.douzone.haru.service.UserService;
import com.douzone.haru.service.tagListService;
import com.douzone.haru.service.email.MailService;
import com.douzone.haru.vo.MessageBoxVo;
import com.douzone.haru.vo.NoticeMessageVo;
import com.douzone.haru.vo.TaskListVo;
import com.douzone.haru.vo.TaskVo;
import com.douzone.haru.vo.UserVo;

@CrossOrigin(origins = { "http://localhost:8080" })
@RestController
@RequestMapping("/api/tasklist")
public class ApiTasklistController {
	
	@Autowired
	private TasklistService tasklistService;
	
	@Autowired
	private TaskService taskService;
	
	@Autowired
	private tagListService tagListService;
	
	@Autowired
	private NoticeMessageService noticeMessageService;
	
	@Autowired
	private ProjectService projectService;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private MailService mailService;
	
	@Autowired
	private ApiNoticeSocket apiNoticeSocket;
	
	// 테스크리스트 가져오기
	//@MessageMapping("test")
	@GetMapping("/data/{projectNo}")
	@Transactional
	public JsonResult selectTasklist(@PathVariable long projectNo ) {
		List<TaskListVo> list = tasklistService.selectTaskList(projectNo);
		
		for (TaskListVo item : list) {
			Map<String, Object> map = new HashMap<String, Object>();
			
			map.put("projectNo", item.getProjectNo());
			map.put("tasklistNo", item.getTaskListNo());
			
			item.setTaskVoList(taskService.taskBytaskList(map));
			
			
			for (TaskVo taskVo : item.getTaskVoList()) {
				taskVo.setTagListVo(tagListService.selectTag(taskVo.getTaskNo()));
			}
		}
	
		
		return JsonResult.success(list);
	}
	
//	@AuthenticationPrincipal PrincipalDetails userVo,
	@PostMapping("/add")
	public JsonResult insertTaskList(@RequestBody Map<String, Object> vo, @AuthUser PrincipalDetails principa) {
		
//		System.out.println(user.getUserNo());
		
		TaskListVo tlVo = new TaskListVo();
		tlVo.setProjectNo((Integer)vo.get("projectNo"));
		tlVo.setTaskListName((String)vo.get("taskListName"));
		tlVo.setTaskListOrder((Integer)vo.get("taskListOrder"));
		
		System.out.println("asdsada"+principa.getUserNo());
		
		String userEmail = (String)vo.get("userEmail");
		
		long result = tasklistService.insertTaskList(tlVo);
		if (result > 0) {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("message", "테스크 리스트가 추가되었습니다.");
			map.put("projectNo", tlVo.getProjectNo());
			map.put("userEmail", userEmail);
			
			//맴버 있는지 체크
			List<UserVo> member = projectService.proejctmemberAlllistselect(tlVo.getProjectNo());
			
			if (member == null || member.size() <= 1) {
				return JsonResult.success(result);
			}
			
			
			// 알림 추가
			String message = ((String) vo.get("projectName")) + "에 테스크 리스트가 추가되었습니다.";
			NoticeMessageVo messageVo = new NoticeMessageVo();
			messageVo.setNoticeMessage(message);
			messageVo = noticeMessageService.noticeInsert(messageVo);
			
			// 맴버들에게 알림
			for (UserVo userVo : member) {
				if (userVo.getUserNo() != (principa.getUserNo())) {
					MessageBoxVo mbVo = new MessageBoxVo();
					mbVo.setUserNo(userVo.getUserNo());
					mbVo.setNoticeNo(messageVo.getNoticeNo());
					
					noticeMessageService.noticeBoxInsert(mbVo);
				}
			}
			
			// 소켓
			Map<String, Object> resultSend = new HashMap<String, Object>();
			resultSend.put("data", vo);
			resultSend.put("bellNo",messageVo.getNoticeNo());
			resultSend.put("bell", message);
			resultSend.put("taskListNo", result);
			resultSend.put("projectNo", (Integer)vo.get("projectNo"));
			
			apiNoticeSocket.taskUpdateSend(resultSend, member, principa.getUserNo());
			
			return JsonResult.success(result);
		} else {
			return JsonResult.fail("생성 실패");
		}

	}
	
	@PostMapping("/delete")
	@Transactional
	public JsonResult deleteTaskList(@RequestBody Map<String, Object> map, @AuthUser PrincipalDetails principa) {
		System.out.println(principa.getUserNo());
		
		long result1 = tasklistService.deleteTaskList((Integer)map.get("taskListNo"));
		long result2 = taskService.deleteByTaskList((Integer)map.get("taskListNo"));
		
		if (result1 > 0) {
			
			//맴버 있는지 체크
			List<UserVo> member = projectService.proejctmemberAlllistselect((Integer)map.get("projectNo"));
			
			if (member == null || member.size() <= 1) {
				return JsonResult.success(result1);
			}
			
			// 알림 추가
			String message = ((String) map.get("projectTitle")) + "에 테스크 리스트가 삭제되었습니다.";
			NoticeMessageVo messageVo = new NoticeMessageVo();
			messageVo.setNoticeMessage(message);
			messageVo = noticeMessageService.noticeInsert(messageVo);
			
			// 맴버들에게 알림
			for (UserVo userVo : member) {
				if (userVo.getUserNo() != (principa.getUserNo())) {
					MessageBoxVo mbVo = new MessageBoxVo();
					mbVo.setUserNo(userVo.getUserNo());
					mbVo.setNoticeNo(messageVo.getNoticeNo());
								
					noticeMessageService.noticeBoxInsert(mbVo);
				}
			}
			
			// 소켓
			Map<String, Object> resultSend = new HashMap<String, Object>();
			resultSend.put("data", (Integer)map.get("taskListNo"));
			resultSend.put("bellNo",messageVo.getNoticeNo());
			resultSend.put("bell", message);
			resultSend.put("projectNo", (Integer)map.get("projectNo"));
						
			apiNoticeSocket.taskRemoveSend(resultSend, member, principa.getUserNo());
			
			return JsonResult.success(result1);
		} else {
			return JsonResult.fail("삭제 실패");
		}
		
	}
	
	@PostMapping("/member/invite/{projectNo}")
	@Transactional
	public JsonResult invite(@RequestBody UserVo uVo, @PathVariable("projectNo") long projectNo, @AuthUser PrincipalDetails principa) {
		UserVo userVo = userService.findByUsername(uVo.getUserEmail());
		System.out.println(uVo.getUserEmail());
		System.out.println("asdsa" + userVo);
		if (userVo == null) {
			return JsonResult.fail("가입된 이메일이 아닙니다.");
		} else {
			List<UserVo> member = projectService.proejctmemberAlllistselect(projectNo);
			
			if (member == null || member.size() <= 1) {
				if (member.get(0).getUserNo() == principa.getUserNo()) {
					return JsonResult.fail("자신을 초대할 수 없습니다.");
				}
				
				Map<String, Object> map = new HashMap<String, Object>();
				map.put("userNo", userVo.getUserNo());
				map.put("projectNo", projectNo);
				
				projectService.memberProjectInsert(map);	
				return JsonResult.success(userVo);
			}
			
			for (UserVo user : member) {
				if (userVo.getUserNo() == user.getUserNo()) {
					return JsonResult.fail("이미 추가된 맴버입니다.");
				}
			}
			
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("userNo", userVo.getUserNo());
			map.put("projectNo", projectNo);
			
			projectService.memberProjectInsert(map);	
			
			try {
				mailService.projectInviteMailSend(uVo.getUserEmail(), principa.getUserName());
			} catch (UnsupportedEncodingException | MessagingException e) {
				e.printStackTrace();
			}
			
			return JsonResult.success(userVo);
		}
	}
	
}
