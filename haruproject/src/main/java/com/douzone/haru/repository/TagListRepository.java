package com.douzone.haru.repository;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.douzone.haru.vo.TagListVo;

//종윤
@Repository
public class TagListRepository {
	
	@Autowired
	private SqlSession sqlSession;
	
	//태그리스트 목록 불러오기
	public List<TagListVo> selectTagList() {
		return sqlSession.selectList("taglist.selectTagList");
	}
	
	//태그 리스트 추가
	public int insertTagList(TagListVo tagListVo) {
		return sqlSession.insert("taglist.insertTagList", tagListVo);
		
	}
	
	public int tagDelete(Long tagNo) {
		//tagtask에 있는 태그 먼저 지우고
		int tagTask = sqlSession.delete("taglist.deletAllTaskTag", tagNo);
		//taglist에 있는 태그 삭제
		int tagList = sqlSession.delete("taglist.deleteTagList", tagNo);
		return tagList  + tagTask ;
	}
	
	//업무에 태그 추가
	//taskTagInsert
	public int insertTaskTag(TagListVo tagListVo) {
		return sqlSession.insert("taglist.insertTaskTag", tagListVo);
	}
	
	//업무의 태그 삭제
	//taskTagDelete
	public int deleteTaskTag(TagListVo tagListVo) {
		return sqlSession.delete("taglist.deleteTaskTag", tagListVo);
	}
	
	//태그리스트의 태그 업데이트
	public int updateTag(TagListVo taglistVo) {
		return sqlSession.update("taglist.updateTag", taglistVo);
	}
	
	//테스크에 있는 태그 select
	public List<TagListVo> selectTag(long taskNo) {
		return sqlSession.selectList("taglist.selectTag", taskNo);
	}

}
