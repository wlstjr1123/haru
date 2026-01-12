package com.douzone.haru.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.douzone.haru.repository.TaskRepository;
import com.douzone.haru.vo.TaskListVo;
import com.douzone.haru.vo.TaskVo;

@Service
public class TaskService {
	@Autowired
	TaskRepository taskRepository;
	
	public List<TaskVo> taskBytaskList(Map<String, Object> map) {
		return taskRepository.taskAllSelect(map);
	}
	
	public long insertTaskUser(Map<String, Object> map) {
		return taskRepository.insertTaskUser(map);
	}
	
	@Transactional()
	public long taskDropUpdate(TaskListVo vo) {
		
		int j = 0;
		
		if (vo.getTaskVoList().size() == 0) {
			return 1L;
		}
		
		for (int i = 0; i < vo.getTaskVoList().size() ; i++) {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("taskNo", vo.getTaskVoList().get(i).getTaskNo());
			map.put("taskOrder", i);
			map.put("tasklistNo", vo.getTaskListNo());
			
			taskRepository.taskDropUpdate(map);
			
			j = i;
		}
		
		if (j == vo.getTaskVoList().size() -1) {
			return 1L;
		} else {
			return 0L;
		}
	}
	
	public long insertTask(TaskVo vo) {
		
		return taskRepository.insertTask(vo); 
	}
	
	public long deleteByTaskList(long index) {
		return taskRepository.deleteByTaskList(index);
	}
	
	public long taskDelete(long taskNo) {
		return taskRepository.taskDelete(taskNo);
	}
}
