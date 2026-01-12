package com.douzone.haru.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.douzone.haru.repository.ChecklistRepository;
import com.douzone.haru.repository.CommentRepository;
import com.douzone.haru.repository.FileRepository;
import com.douzone.haru.repository.TagListRepository;
import com.douzone.haru.repository.TaskSettingRepository;
import com.douzone.haru.vo.CheckListVo;
import com.douzone.haru.vo.CommentVo;
import com.douzone.haru.vo.FileVo;
import com.douzone.haru.vo.TagListVo;
import com.douzone.haru.vo.TaskVo;

@Service
public class TaskSettingService {

	@Autowired
	private TaskSettingRepository taskSettingRepository;
	@Autowired
	private ChecklistRepository checklistRepository;
	@Autowired
	private FileRepository fileRepository;
	@Autowired
	private CommentRepository commentRepository;
	@Autowired
	private TagListRepository tagListRepository;

	/*
	 * Task : 종윤
	 */
	public Map<String,Object> getTaskInfo(long taskNo) {
		Map<String,Object> taskMap = new HashMap<String, Object>();
		taskMap.put("taskInfo",taskSettingRepository.taskSelect(taskNo));
		taskMap.put("filesInfo",fileRepository.selectFileList(taskNo));
		taskMap.put("commentsInfo",commentRepository.selectComments(taskNo));
		taskMap.put("tagsInfo",tagListRepository.selectTag(taskNo));
		taskMap.put("checkListInfo",checklistRepository.selectCheckList(taskNo));
		return taskMap;
	}
	
	public TaskVo getTask(long taskNo) {
		return taskSettingRepository.taskSelect(taskNo);
	}

	public boolean updateTask(TaskVo taskVo) {
		return 1 == taskSettingRepository.updateTask(taskVo);
	}

	public boolean updateTaskDate(TaskVo taskVo) {
		return 1 == taskSettingRepository.updateTaskDate(taskVo);
	}

	public boolean updateTaskLabel(Long taskNo, String color) {
		return 1 == taskSettingRepository.updateTaskLabel(taskNo, color);
	}

	public boolean updateTaskState(List<TaskVo> tasks) {
		int result = 0;
		for (TaskVo vo : tasks) {
			result = taskSettingRepository.updateTaskState(vo);
			if (result != 1) {
				return false;
			}
		}
		return result != -1;
	}

	public boolean updateTaskName(TaskVo taskListVo) {
		return 1 == taskSettingRepository.updateTaskName(taskListVo);
	}
	
	/*
	 * file : 종윤
	 */

	public List<FileVo> getTaskFiles(Long taskNo) {
		return fileRepository.selectFileList(taskNo);
	}

	public boolean insertFile(FileVo fileVo) {
		int file = fileRepository.insertFile(fileVo);
		return file == 1;
	}

	public int removeFile(Long fileNo) {
		return fileRepository.deleteFile(fileNo);
	}

	/*
	 * comment : 종윤
	 */
	public List<CommentVo> selectComments(Long taskNo) {
		return commentRepository.selectComments(taskNo);
	}

	public boolean insertComment(CommentVo commentVo) {
		return 1 == commentRepository.insertComment(commentVo);
}

	public boolean updateCommentContents(CommentVo commentVo) {

		return 1 == commentRepository.updateCommentContents(commentVo);
	}

	public boolean deleteComment(Long commentNo) {
		return 1 == commentRepository.deleteComment(commentNo);
	}

	/*
	 * tag : 종윤
	 */
	//태그리스트 목록 불러오기
	public List<TagListVo> selectTagList() {
		return tagListRepository.selectTagList();
	}

	//현재테스크의 태그 목록 불러오기
	public List<TagListVo> selectTag(long taskNo) {
		return tagListRepository.selectTag(taskNo);
	}

	//업무에 태그 추가
	//taskTagInsert
	public boolean taskTagInsert(TagListVo tagListVo) {
		return 1 == tagListRepository.insertTaskTag(tagListVo);
	}

	//업무의 태그 삭제
	//taskTagDelete
	public boolean taskTagDelete(TagListVo tagListVo) {
		return 1 == tagListRepository.deleteTaskTag(tagListVo);
	}

	//태그 리스트에 추가
	public boolean tagInsert(TagListVo tagListVo) {
		return 1 == tagListRepository.insertTagList(tagListVo);
	}

	//태그 리스트에서 삭제
	public boolean tagDelete(Long tagNo) {
		return tagListRepository.tagDelete(tagNo) > 0;
	}

	/*
	 * checklist : 종윤
	 */
	//현재테스크의 체크리스트 목록 불러오기
	public List<CheckListVo> selectChecklist(Long taskNo) {
		return checklistRepository.selectCheckList(taskNo);
	}
	
	public boolean insertChecklist(CheckListVo checklistVo) {
		return 1 == checklistRepository.insertChecklist(checklistVo);
	}

	public boolean updateChecklist(CheckListVo checklistVo) {
		return 1 == checklistRepository.updateChecklist(checklistVo);
	}

	public boolean deleteChecklist(Long checklistNo) {
		return 1 == checklistRepository.deleteChecklist(checklistNo);
	}
}
