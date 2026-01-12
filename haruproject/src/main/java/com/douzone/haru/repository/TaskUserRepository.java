package com.douzone.haru.repository;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.douzone.haru.vo.TaskUserVo;

@Repository
public class TaskUserRepository {
	@Autowired
	private SqlSession sqlSession;
	
	public long insertTaskUser(TaskUserVo vo) {
		return sqlSession.insert("taskuser.taskUserAdd", vo);
	}
	
	public long deleteTaskUser(TaskUserVo vo) {
		return sqlSession.delete("taskuser.taskUserDelete", vo);
	}
}
