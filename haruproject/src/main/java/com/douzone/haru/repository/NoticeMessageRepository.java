package com.douzone.haru.repository;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.douzone.haru.vo.MessageBoxVo;
import com.douzone.haru.vo.NoticeMessageVo;
import com.douzone.haru.vo.UserVo;

@Repository
public class NoticeMessageRepository {
	@Autowired
	private SqlSession sqlSession;
	
	public List<NoticeMessageVo> mynoticeSelect(long userNo) {
		return sqlSession.selectList("notice.myNotice", userNo);
	}
	
	public long noticeCheck(Map<String, Object> map) {
		return sqlSession.update("notice.noticeCheck", map);
	}
	
	public long noticeAllCheck(long userNo) {
		return sqlSession.update("notice.noticeAllCheck", userNo);
	}
	
	public NoticeMessageVo noticeInsert(NoticeMessageVo vo) {
		sqlSession.insert("notice.insert", vo);
		
		return vo;
	}
	
	public long noticeBoxInsert(MessageBoxVo vo) {
		return sqlSession.insert("notice.messageBoxInsert", vo);
	}
	
	public long allDelete(long UserNo) {
		return sqlSession.delete("notice.allDelete", UserNo);
	}
}
