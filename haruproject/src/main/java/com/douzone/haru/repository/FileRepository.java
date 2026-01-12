package com.douzone.haru.repository;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.douzone.haru.vo.FileVo;
// 종윤
@Repository
public class FileRepository {

	@Autowired
	private SqlSession sqlSession;
	
	//프로젝트 별 파일 select
	public List<FileVo> selectFile(Long projectNo) {
		return sqlSession.selectList("file.selectFile", projectNo);
	}

	public int insertFile(FileVo fileVo) {
		return sqlSession.insert("file.insertFile", fileVo);
	}

	public int deleteFile(Long fileNo) {
		return sqlSession.delete("file.deleteFile", fileNo);
	}

	public String findByFileName(Long fileNo) {
		return sqlSession.selectOne("file.findByFileName", fileNo);
	}
	
	//Task 별 File select
	public List<FileVo> selectFileList(Long taskNo) {
		return sqlSession.selectList("file.selectFileList", taskNo);
	}
	
	// 이름 찾기
	public String getUserName(Long userNo) {
		String userName = sqlSession.selectOne("file.findNameByNo",userNo);
		return userName;
	}


}
