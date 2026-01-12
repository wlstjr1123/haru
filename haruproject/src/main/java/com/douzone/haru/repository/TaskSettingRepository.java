package com.douzone.haru.repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.douzone.haru.vo.TaskVo;

/*
 * 작성자 : 이종윤
 * 설명   : Task Setting
*/
@Repository
public class TaskSettingRepository {
	
	@Autowired
	private SqlSession sqlSession;
	
	public TaskVo taskSelect(long taskNo) {
		return sqlSession.selectOne("tasksetting.taskSelect", taskNo);
	}
	
	//업무 내용 수정
	public int updateTask(TaskVo taskVo) {
		return sqlSession.update("tasksetting.updateTask", taskVo);
	}
	
	//업무 날짜 변경
	public int updateTaskDate(TaskVo taskVo) {
		return sqlSession.update("tasksetting.updateTaskDate", taskVo);
	}
	
	//업무 라벨 수정
	public int updateTaskLabel(Long taskNo, String color) {
		Map<String, Object> map = new HashMap<>();
		
		map.put("taskNo", taskNo);
		map.put("color", color);
		
		return sqlSession.update("tasksetting.updateTaskLabel",map);
	}
	//업무 이름 수정
	public int updateTaskName(TaskVo taskVo) {
		return sqlSession.update("tasksetting.updateTaskName", taskVo);
	}
	//업무 상태 수정
	public int updateTaskState(TaskVo taskVo) {
		return sqlSession.update("tasksetting.updateTaskState", taskVo);
	}
	

}
