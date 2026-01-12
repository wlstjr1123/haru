package com.douzone.haru.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.douzone.haru.repository.TaskListRepository;
import com.douzone.haru.vo.TaskListVo;

@Service
public class TasklistService {
	
	@Autowired
	TaskListRepository taskListRepository;
	
	public List<TaskListVo> selectTaskList(long projectNo) {
		return taskListRepository.selectTasklist(projectNo);
	}
	
	public long insertTaskList(TaskListVo vo) {
		return taskListRepository.insertTaskList(vo);
	}
	
	public long deleteTaskList(long no) {
		return taskListRepository.deleteTaskList(no);
	}
}
