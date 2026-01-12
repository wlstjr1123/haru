package com.douzone.haru.repository;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.douzone.haru.vo.UserVo;

@Repository
public class UserRepository {
	
	@Autowired
	SqlSession sqlSession;
	
	// 로그인시 시큐리티가 쓰는 곳
	public UserVo findByUserEmail(String userEmail) {
		return sqlSession.selectOne("user.findByUserEmail", userEmail);
	}
	
	// 세로운 회원가입 유저 추가
	public boolean addUser(UserVo vo) {
		 return 1 == sqlSession.insert("user.addUser", vo);
	}
	
	public UserVo findByUsername(String userEmail) {
		System.err.println("여기옸나");
		UserVo vo = sqlSession.selectOne("user.findByUsername", userEmail);
		System.err.println(vo);
		return vo;
	}

	public UserVo findUserBykey(String key) {
		return sqlSession.selectOne("user.findUserBykey", key);
	}

	public void updateUserAuth(UserVo authUser) {
		sqlSession.update("user.updateUserAuth", authUser);
	}

	public UserVo findEmailById(String userEmail) {
		return sqlSession.selectOne("user.findEmailById", userEmail);
	}

	public void updateUserPassword(UserVo vo) {
		sqlSession.update("user.updateUserPassword", vo);
	}

	public UserVo findUserProfile(String userEmail) {
		return sqlSession.selectOne("user.findUserProfile", userEmail);
	}

	public boolean updateProfile(UserVo vo) {
		return 1 == sqlSession.update("user.updateProfile",vo);
	}

	public boolean updateProfileImg(UserVo userVo) {
		return 1 == sqlSession.update("user.updateProfileImg", userVo);
	}

	public boolean findUserByPassword(String password) {
		return sqlSession.selectOne("user.findUserByPassword", password);
	}

	public boolean updatePassword(UserVo userVo) {
		System.err.println("비밀번호 변경하러 왔음 : " + userVo);
		return 1 == sqlSession.update("user.updatePassword", userVo);
	}

	public boolean deleteUser(UserVo vo) {
		return 1 == sqlSession.update("user.deleteUser", vo);
	}

}
