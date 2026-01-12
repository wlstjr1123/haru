package com.douzone.haru.repository;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.douzone.haru.vo.CommentVo;
//종윤
@Repository
public class CommentRepository {
	@Autowired
	private SqlSession sqlSession;
	
	public List<CommentVo> selectComments(Long taskNo) {
		return sqlSession.selectList("comment.selectComments",taskNo);
	}
	
	public int insertComment(CommentVo commentVo) {
		return sqlSession.insert("comment.insertComment", commentVo);
	}

	public int updateCommentContents(CommentVo commentVo) {
		return sqlSession.update("comment.updateCommentContents", commentVo);
	}

	public int deleteComment(Long commentNo) {
		return sqlSession.delete("comment.deleteComment", commentNo);
	}

}
