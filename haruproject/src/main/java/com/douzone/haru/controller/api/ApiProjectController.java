package com.douzone.haru.controller.api;

import java.sql.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.douzone.haru.dto.JsonResult;
import com.douzone.haru.service.ProjectService;
import com.douzone.haru.vo.ProjectVo;
import com.douzone.haru.vo.UserVo;

@RestController
@RequestMapping("/api/project")
public class ApiProjectController {

	@Autowired
	private ProjectService projectService;
	
	//프로젝트 생성
	@PostMapping("/add/{authUserNo}")
	public JsonResult ProjectInsert(@PathVariable("authUserNo")Long authUserNo, @RequestBody ProjectVo projectVo) {
		System.out.println("프로젝트 생성 fetch 들어옴?"+projectVo);
		System.out.println("members"+projectVo.getMembers());
		ProjectVo insertProjectVo = projectService.ProjectInsert(authUserNo,projectVo);
		System.out.println("생성 확인 : "+insertProjectVo);
		return JsonResult.success(insertProjectVo);
	}
	
	//내가 속한 프로젝트 출력(Main)
	@GetMapping("/{authUserNo}")
	public JsonResult ProjectMainSelect(@PathVariable("authUserNo") Long authUserNo) {
		System.out.println("fetch 요청 됫니~~~~~~~~~~~~~~~`"+authUserNo);
		List<ProjectVo> projectlist = projectService.projectMainselect(authUserNo);
		System.out.println(projectlist);
		return JsonResult.success(projectlist);
	}
	
	//프로젝트 상세보기
	@GetMapping("/detail/{projectNo}")
	public void projectDetail(@PathVariable("projectNo")Long projectNo) {
		projectService.projectDetail(projectNo);
	}
	
	//프로젝트 생성시 멤버 찾기
	@GetMapping("/member")
	public JsonResult projectMemberDetail() {
		System.out.println("멤버 찾자");
		List<UserVo> userVo = projectService.projectMemberDetail();
		System.out.println("모든 멤버 vo"+userVo);
		return JsonResult.success(userVo); 
	}
	
	//프로젝트 수정
	@PutMapping("/update")
	public JsonResult projectUpdate(
			@RequestBody ProjectVo projectVo) {
		System.out.println("프로젝트 수정 요청 들어옴?, OB : "+projectVo);
		System.out.println("프로젝트 수정 members : "+projectVo.getMembers());
		
		int result = projectService.projectUpdate(projectVo);
		return JsonResult.success(result);
	}
	
	//프로젝트 삭제 (상태값 F로 변경)
	@PutMapping("/delete")
	public JsonResult projectDelete(@RequestBody ProjectVo projectVo) {
		System.out.println("프로젝트 삭제 요청 들어옴?"+projectVo);
		return JsonResult.success(projectService.projectDelete(projectVo));
	}
}