package com.douzone.haru.repository;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.douzone.haru.vo.TaskVo;

@Repository
public class TaskRepository {
	@Autowired
	private SqlSession sqlSession;
	
	public long insertTask(TaskVo vo) {
		sqlSession.insert("task.taskAdd", vo);
		long num = vo.getTaskNo();
		return num;
	}
	
	public long insertTaskUser(Map<String, Object> map) {
		return sqlSession.insert("task.taskUserAdd", map);
	}
	
	public long deleteTask(long index) {
		return sqlSession.update("task.taskDelete", index);
	}
	
	public List<TaskVo> taskAllSelect(Map<String, Object> map) {
		return sqlSession.selectList("task.taskAllSelect", map);
	}
	
	public long taskDropUpdate (Map<String, Object> map) {
		return sqlSession.update("task.taskDropUpdate", map);
	}
	
	public long deleteByTaskList(long index) {
		return sqlSession.update("task.taskDeleteByTaskList", index);
	}
	
	public long taskDelete(long taskNo) {
		return sqlSession.update("task.taskDelete", taskNo);
	}
}
