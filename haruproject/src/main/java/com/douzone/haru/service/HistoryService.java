package com.douzone.haru.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.douzone.haru.repository.HistoryRepository;
import com.douzone.haru.vo.HistoryVo;

@Service
public class HistoryService {


	
	@Autowired
	private HistoryRepository historyRepository;

	public List<HistoryVo> selectHistory(Long projectNo) {
		return historyRepository.selectHistory(projectNo);
	}
	


	/*
	 * json-simple 특징
	 * 
	 * 1.json-simple은 내부적으로 JSON 데이터를 처리하기 위해 Map과 List를 사용합니다.
	 * 
	 * 2.json-simple은 JSON 데이터를 구문 분석하고 JSON을 파일에 기록할 수 있습니다.
	 * 
	 * 3.json-simple의 가장 큰 특징은 타사 라이브러리에 대한 의존성이 없습니다.
	 * 
	 * 4.json-simple는 매우 가벼운 API이며 간단한 JSON 데이터를 처리하기 위해 적합합니다.
	 * 
	 * 5.작성자:이종윤 설명:히스토리추가
	 * 
	 */

	public boolean insertHistory(JSONObject historyJson) {
		Map<String, Object> map = new HashMap<>();

		HashMap memberInfo = new HashMap<>();

		map.put("logDate", historyJson.get("historyDate"));
		map.put("projectNo", historyJson.get("projectNo"));
		System.out.println(historyJson.get("actionName"));
		if (historyJson.get("actionName") instanceof String == false) {
			memberInfo = (HashMap) historyJson.get("actionName");
			System.err.println(memberInfo);
		}

		switch ((String) historyJson.get("historyType")) {
		case "taskContentsUpdate"://
			map.put("logContents", historyJson.get("senderName") + " 님이 " + historyJson.get("actionName") + " 으로 업무이름을 수정하셨습니다.");
			break;
		case "taskListInsert":
			map.put("logContents", historyJson.get("senderName") + " 님이 " + historyJson.get("actionName") + " 업무리스트를 추가하였습니다.");
			break;
		case "taskListDelete":
			map.put("logContents", historyJson.get("senderName") + " 님이 " + historyJson.get("actionName") + " 업무리스트를 삭제하였습니다.");
			break;
		case "taskDateUpdate":
			map.put("logContents", historyJson.get("senderName") + " 님이 " + historyJson.get("actionName") + " 업무의 마감일을 수정하였습니다.");
			break;
		case "taskMemberJoin"://
			map.put("logContents", historyJson.get("senderName") + " 님이 " + historyJson.get("actionName") + " 업무에 멤버를 추가하였습니다.");
			break;
		case "checklistInsert":
			map.put("logContents", historyJson.get("senderName") + " 님이 " + historyJson.get("actionName") + " 업무에 체크리스트를 추가하였습니다.");
			break;
		case "checklistStateUpdate":
			map.put("logContents", historyJson.get("senderName") + " 님이 " + historyJson.get("actionName") + " 업무의 체크리스트 상태를 수정하였습니다.");
			break;
		case "commentInsert":
			map.put("logContents", historyJson.get("senderName") + " 님이 " + historyJson.get("actionName") + " 업무에 코멘트를 추가하였습니다.");
			break;
		case "taskDragNdrop":
			map.put("logContents", historyJson.get("senderName") + " 님이 " + historyJson.get("actionName") + " 업무의 위치를 변경하였습니다.");
			break;
		case "taskListDragNdrop":
			map.put("logContents", historyJson.get("senderName") + " 님이 " + historyJson.get("actionName") + " 업무리스트의 위치를 변경하였습니다.");
			break;
		case "taskStateUpdate":
			map.put("logContents", historyJson.get("senderName") + " 님이 " + historyJson.get("actionName") + " 업무 상태를 변경하였습니다.");
			break;
		case "taskInsert":
			map.put("logContents", historyJson.get("senderName") + " 님이 " + historyJson.get("actionName") + " 업무를 추가하였습니다.");
			break;
		case "taskDelete":
			map.put("logContents", historyJson.get("senderName") + " 님이 " + historyJson.get("actionName") + " 업무를 삭제하였습니다.");
			break;
		case "projectMemberInvite":
			if (memberInfo.get("memberName") == "") {
				map.put("logContents", historyJson.get("senderName") + " 님이 " + memberInfo.get("memberEmail") + " 님을 초대하였습니다.");
			} else {
				map.put("logContents", historyJson.get("senderName") + " 님이 " + memberInfo.get("memberName") + " 님을 초대하였습니다.");
			}
			break;
		case "projectMemberJoin":
			System.err.println(historyJson.get("actionName"));
			map.put("logContents", historyJson.get("senderName") + " 님이 " + historyJson.get("actionName") + " 님을 프로젝트에 참여시켰습니다.");
			break;
		case "projectDateUpdate":
			map.put("logContents", historyJson.get("senderName") + " 님이 " + historyJson.get("actionName") + " 프로젝트의 업무마감일을 수정하였습니다.");
			break;
		}
		System.out.println("과연 Map에서는 뭐가 나올까?"+map);
		
		int result = historyRepository.insertHistory(map);
		return result == 1;
	}

}
