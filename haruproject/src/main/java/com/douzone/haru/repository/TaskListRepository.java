package com.douzone.haru.repository;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.douzone.haru.vo.TaskListVo;

@Repository
public class TaskListRepository {
	@Autowired
	private SqlSession sqlSession;
	
	public long insertTaskList(TaskListVo vo) {
		sqlSession.insert("tasklist.taskListAdd", vo);
		long num = vo.getTaskListNo();
		return num;
	}
	
	public long deleteTaskList(long no) {
		return sqlSession.update("tasklist.taskListDelete", no);
	}
	
	public List<TaskListVo> selectTasklist(long projectNo) {
		return sqlSession.selectList("tasklist.selectTaskList", projectNo);
	}
}
