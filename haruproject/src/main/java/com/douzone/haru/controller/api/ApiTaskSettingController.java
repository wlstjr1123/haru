package com.douzone.haru.controller.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.douzone.haru.dto.JsonResult;
import com.douzone.haru.service.TaskSettingService;
import com.douzone.haru.vo.CheckListVo;
import com.douzone.haru.vo.CommentVo;
import com.douzone.haru.vo.TagListVo;
import com.douzone.haru.vo.TaskVo;

/*
 * 종윤
 */
@CrossOrigin(origins = { "http://localhost:8080" })
@RestController
public class ApiTaskSettingController {

	@Autowired
	private TaskSettingService taskSettingService;

	/*
	 * tasksetting을 SETTING
	 */
	@GetMapping("/api/tasksetting/{taskNo}")
	public JsonResult taskSetting(@PathVariable("taskNo") Long taskNo) {
		return JsonResult.success(taskSettingService.getTaskInfo(taskNo));
	}
	
	/* 업무 날짜 변경 이름 컨텐트
	 * 업무 내용, 이름 업데이트 need : taskContents, taskNo
	 */
	@PostMapping("/api/tasksetting/task/update")
	public JsonResult taskUpdate(@RequestBody TaskVo taskVo) {
		boolean result = taskSettingService.updateTask(taskVo);
		return JsonResult.success(result? taskVo : -1);
	}

//////////////////////////////////////////////////////////////
	/*
	 * checklist insert need : checklistContents, taskNo
	 */
	@PostMapping("/api/tasksetting/checklist/add")
	public JsonResult checklistInsert(@RequestBody CheckListVo checklistVo) {
		boolean result = taskSettingService.insertChecklist(checklistVo);
		return JsonResult.success(result ? checklistVo : -1);
	}

	/*
	 * checklist update 내용이 있으면 내용 변화, 내용이 없으면 상태 수정 need : checklistContents,
	 * checklistNo or :checklistState, checklistNo
	 */
	@PostMapping("/api/tasksetting/checklist/update")
	public JsonResult checklistUpdate(@RequestBody CheckListVo checklistVo) {
		boolean result = taskSettingService.updateChecklist(checklistVo);

		return JsonResult.success(result ? checklistVo : -1);
	}

	/*
	 * checklist delete need : checklistNo
	 */
	@DeleteMapping("/api/tasksetting/checklist/{checklistNo}")
	public JsonResult checklistDelete(@PathVariable("checklistNo") Long checklistNo) {
		boolean result = taskSettingService.deleteChecklist(checklistNo);
		return JsonResult.success(result ? checklistNo: -1);
	}

//////////////////////////////////////////////////////////////
	/*
	 * comment insert
	 */
	@PostMapping("/api/comment")
	public JsonResult comment(@RequestBody CommentVo commentVo) {
		boolean result = taskSettingService.insertComment(commentVo);

		return JsonResult.success(result ? commentVo : -1);
	}

	/*
	 * comment update
	 */
	@PostMapping("/api/comment/contents/{commentNo}")
	public JsonResult commentUpdate(@RequestBody CommentVo commentVo) {

		boolean result = taskSettingService.updateCommentContents(commentVo);

		return JsonResult.success(result ? commentVo.getCommentContents() : -1);

	}

	/*
	 * comment delete
	 */
	@DeleteMapping("/api/comment/{commentNo}")
	public JsonResult commentDelete(@PathVariable("commentNo") Long commentNo) {
		boolean result = taskSettingService.deleteComment(commentNo);
		return JsonResult.success(result ? commentNo : -1);
	}
//////////////////////////////////////////////////////////////	
	//태그리스트 목록 불러오기
	@GetMapping("/api/taglist")
	public JsonResult tagList() {
		List<TagListVo> tagListVo = taskSettingService.selectTagList();
		return JsonResult.success(tagListVo);
	}
	
	//업무에 태그 추가
	@PostMapping("/api/tag/add")
	public JsonResult tagAdd(
			@RequestBody TagListVo tagListVo) {
		boolean result = taskSettingService.taskTagInsert(tagListVo);
		return JsonResult.success(result ? tagListVo : -1);
	}
	
	//업무의 태그 삭제
	@DeleteMapping("/api/tag/{taskNo}/{tagNo}")
	public JsonResult tagDelete(
			@PathVariable("taskNo") Long taskNo,
			@PathVariable("tagNo") Long tagNo) {
		
		TagListVo tagListVo = new TagListVo();
		tagListVo.setTagNo(tagNo);
		tagListVo.setTaskNo(taskNo);
		System.out.println(tagListVo);
		
		boolean result = taskSettingService.taskTagDelete(tagListVo);
		return JsonResult.success(result ? tagListVo : -1);
	}
	
	@PostMapping("/api/taglist/add")
	public JsonResult tagListAdd(
			@RequestBody TagListVo tagListVo) {
		boolean result = taskSettingService.tagInsert(tagListVo);
		return JsonResult.success(result ? tagListVo : -1);
	}
	
	@DeleteMapping("/api/taglist/delete")
	public JsonResult tagListDelete(
			@RequestBody Long tagNo) {
		boolean result = taskSettingService.tagDelete(tagNo);
		return JsonResult.success(result ? tagNo : -1);
	}
	
	//	/*
	//	 * 설명 : 업무 날짜 변경
	//	 * taskStart taskEnd taskNo
	//	 */ 
	//	@PostMapping("/api/tasksetting/calendar/update")
	//	public JsonResult taskDateUpdate(@RequestBody TaskVo TaskVo) {
	//		int result = taskSettingService.updateTaskDate(TaskVo);
	//		return  JsonResult.success(result == 1 ? TaskVo : -1);
	//	}
	//	
	//	
	//	/*
	//	 * 설명: 업무 라벨 색상 수정
	//	 * color = #fff000 ,taskNo
	//	 */
	//	
	//	@PostMapping("/api/tasksetting/tasklabel/{taskNo}")
	//	public JsonResult taskLabel(
	//			@PathVariable("taskNo") Long taskNo,
	//			@RequestBody String color) {
	//		
	//		int result = taskSettingService.updateTaskLabel(taskNo, color);
	//		return JsonResult.success(result == 1 ? taskNo : -1);
	//	}

}
