package com.douzone.haru.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.douzone.haru.repository.ProjectRepository;
import com.douzone.haru.vo.ProjectVo;
import com.douzone.haru.vo.UserVo;

@Service
public class ProjectService {
	
	@Autowired
	private ProjectRepository projectRepository;

	//프로젝트 생성
	public ProjectVo ProjectInsert(Long authUserNo,ProjectVo projectVo) {
		// TODO Auto-generated method stub
//		ProjectVo projectVo = new ProjectVo();
//		projectVo.setProjectTitle("test11");
//		projectVo.setProjectDesc("test11");
//		projectVo.setProjectTitle("settest4");
//		projectVo.setProjectDesc("settest4");
//		projectVo.setProjectStart("2021-12-01 00:00:00");
//		projectVo.setProjectEnd("2021-12-31 01:00:00");
//		projectVo.setProjectState("T");
//		projectVo.setProjectRegDate("2021-11-30 20:42:00");
//		System.out.println("======="+projectVo+"==========");
		//프로젝트 생성 완료 (깡통)
		System.out.println("authno : "+authUserNo);
		System.out.println(projectVo);
		projectRepository.procjectInsert(projectVo);
		projectVo.setUserNo(authUserNo);
		
		//O권한을 가진 유저(프로젝트 생성자) 프로젝트에 insert 
		projectRepository.ownerProjectInsert(projectVo);
		//=
		
		//프로젝트에 member 추가
		
		Map<String, Object> map = new HashMap<String, Object>();
		for(UserVo members : projectVo.getMembers()) {
			map.put("userNo", members.getUserNo());
			map.put("projectNo", projectVo.getProjectNo());
			System.out.println("map : "+map);
			
			projectRepository.memberProjectInsert(map);			
		}
		return projectVo;
	}
	//내가 속한 프로젝트 출력(Main)
	public List<ProjectVo> projectMainselect(Long authUserNo) {
		// TODO Auto-generated method stub
		//프로젝트 리스트 찾기 멤버 X
		List<ProjectVo> projectList = projectRepository.projectMainselect(authUserNo);
		for(int i=0; i<projectList.size(); i++) {
			Long projectNo = projectList.get(i).getProjectNo();
			//프로젝트 멤버 찾기
			List<UserVo> memberlist = projectRepository.projectMemberListSelect(projectNo);
			projectList.get(i).setMembers(memberlist);
		}
		return projectList;
		
	}
	
	//프로젝트 상세보기
	public void projectDetail(Long projectNo) {
		// TODO Auto-generated method stub
//		projectRepository.projectDetail(projectNo);
	}
	
	//프로젝트 생성 시 멤버 찾기
	public List<UserVo> projectMemberDetail() {
		// TODO Auto-generated method stub
		return projectRepository.projectMemberDetail();
	}
	
	//프로젝트 수정
	public int projectUpdate(ProjectVo projectVo) {
		// TODO Auto-generated method stub
		int projectUpdateResult = projectRepository.projectUpdate(projectVo);
		
		//멤버 초기화 시키기

		int memberReset = projectRepository.projectMemberReset(projectVo);
		
		//멤버 추가 시키기
		Map<String, Object> map = new HashMap<String, Object>();
		for(UserVo members : projectVo.getMembers()) {
			map.put("userNo", members.getUserNo());
			map.put("projectNo", projectVo.getProjectNo());
			projectRepository.memberProjectInsert(map);			
		}
		return projectUpdateResult;
	}
	//프로젝트 삭제
	public int projectDelete(ProjectVo projectVo) {
		// TODO Auto-generated method stub
		return projectRepository.projectDelete(projectVo);
	}
	
	
	public List<UserVo> proejctmemberAlllistselect(long projectNo) {
		return projectRepository.proejctmemberAlllistselect(projectNo);
	}
	
	public void memberProjectInsert(Map<String, Object> map) {
		projectRepository.memberProjectInsert(map);
	}
	
}
