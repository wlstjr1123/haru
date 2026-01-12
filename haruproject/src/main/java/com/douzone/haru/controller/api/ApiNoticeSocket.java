package com.douzone.haru.controller.api;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;

import com.douzone.haru.service.NoticeMessageService;
import com.douzone.haru.vo.TaskListVo;
import com.douzone.haru.vo.UserVo;

@CrossOrigin(origins = { "http://localhost:8080" })
@Controller
public class ApiNoticeSocket {
	
    @Autowired
	private SimpMessagingTemplate template;
    
    @Autowired
    private NoticeMessageService noticeMessageService;
    
	
	@MessageMapping("/test") // react -> spring 송신
//	@SendTo("topic/asnotice")	// spring -> react 송신
	public void testSend(Object socketData) {
		try{
			System.out.println("작동");
//			System.out.println(socketData);
			template.convertAndSend("/topic/test", socketData);
		}catch(Exception ex){
			System.out.println(ex.getMessage());
		}
	}
	
	
	@MessageMapping("kanban")
	public void taskUpdateSend(Map<String, Object> socketData, List<UserVo> userVo, long myNo) {
		try {
			for (UserVo vo : userVo) {
				if (myNo != vo.getUserNo()) {
					template.convertAndSend("/topic/kanban/tasklist/add/" + vo.getUserNo() + "/" + (Integer) socketData.get("projectNo"), socketData);
					template.convertAndSend("/topic/kanban/tasklist/add/notice/" + vo.getUserNo(), socketData);
				}
				
			}
			
		} catch(Exception ex) {
			System.out.println(ex.getMessage());
		}
	}
	
	public void taskRemoveSend(Map<String, Object> socketData, List<UserVo> userVo, long myNo) {
		try {
			for (UserVo vo : userVo) {
				if (myNo != vo.getUserNo()) {
					template.convertAndSend("/topic/kanban/tasklist/remove/" + vo.getUserNo() + "/" + (Integer) socketData.get("projectNo"), socketData);
					template.convertAndSend("/topic/kanban/tasklist/add/notice/" + vo.getUserNo(), socketData);
				}
				
			}
			
		} catch(Exception ex) {
			System.out.println(ex.getMessage());
		}
	}
	
	
	
	public void taskMoveSend(TaskListVo socketData, List<UserVo> userVo, long myNo) {
		try {
			for (UserVo vo : userVo) {
				if (myNo != vo.getUserNo()) {
					template.convertAndSend("/topic/kanban/task/move/" + vo.getUserNo() + "/" + socketData.getProjectNo(), socketData);
				}
				
			}
		} catch(Exception ex) {
			System.out.println(ex.getMessage());
		}
	}
	
//	@Scheduled(fixedRate = 5000)
//	@GetMapping("/test")
//	public void send() {
//		template.convertAndSend("/api/calender/1",authUser);
//		
//	}
	
	public void taskDeleteSend(Map<String, Object> socketData, List<UserVo> userVo, long myNo) {
		try {
			for (UserVo vo : userVo) {
				if (myNo != vo.getUserNo()) {
					template.convertAndSend("/topic/kanban/task/delete/" + vo.getUserNo() + "/" + (Integer) socketData.get("projectNo"), socketData);
					template.convertAndSend("/topic/kanban/tasklist/add/notice/" + vo.getUserNo(), socketData);
				}
				
			}
		} catch(Exception ex) {
			System.out.println(ex.getMessage());
		}
	}
	
	public void taskaddSend(Map<String, Object> socketData, List<UserVo> userVo, long myNo) {
		try {
			for (UserVo vo : userVo) {
				if (myNo != vo.getUserNo()) {
					template.convertAndSend("/topic/kanban/task/add/" + vo.getUserNo() + "/" + (Integer) socketData.get("projectNo"), socketData);
					template.convertAndSend("/topic/kanban/tasklist/add/notice/" + vo.getUserNo(), socketData);
				}
				
			}
		} catch(Exception ex) {
			System.out.println(ex.getMessage());
		}
	}
}
