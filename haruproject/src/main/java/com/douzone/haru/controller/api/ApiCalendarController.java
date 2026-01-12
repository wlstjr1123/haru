package com.douzone.haru.controller.api;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.douzone.haru.dto.JsonResult;
import com.douzone.haru.service.CalendarService;
import com.douzone.haru.vo.CalendarVo;

//@CrossOrigin(origins = {"http://localhost:3000"})
@RestController
@RequestMapping("/api/calendar")
public class ApiCalendarController {
	
	@Autowired
	CalendarService calendarService;
	
	//개인 일정 뿌리기
	@GetMapping("/{authUserNo}")
	public JsonResult calendarMainSelect(@PathVariable("authUserNo") Long authUserNo) {
		Map<String, Object> map = new HashMap<String, Object>();
		map = calendarService.calendarMainSelect(authUserNo);
		return JsonResult.success(map);
	}
	
	//개인 일정 추가
	@PostMapping("/add/{authUserNo}")
	public JsonResult ScheduleAdd(@PathVariable("authUserNo") Long authUserNo,
			@RequestBody CalendarVo calendarVo) {
		System.out.println("/add api 요청 들어옴?? 들어온 데이터 "+ calendarVo);
		calendarVo.setUserNo(authUserNo);
		CalendarVo calendaraddVo = calendarService.ScheduleAdd(calendarVo);
		return JsonResult.success(calendaraddVo);
	}
	//개인 일정 상세보기
	@GetMapping("/detail/{scheduleNo}")
	public JsonResult ScheduleDetail(@PathVariable("scheduleNo")Long scheduleNo) {
		System.out.println("개인 일정 상세보기 no 값 : "+scheduleNo);
		CalendarVo calendarVo = calendarService.ScheduleDetail(scheduleNo);
		System.out.println("개인 일정 상세보기 : "+calendarVo);
		return JsonResult.success(calendarVo);
	}
	//개인 일정 수정
	@PutMapping("/update/{scheduleNo}")
	public JsonResult ScheduleUpdate(@PathVariable("scheduleNo")Long scheduleNo,
			@RequestBody CalendarVo calendarVo) {
		System.out.println("개인 일정 수정 들어온 no :"+scheduleNo);
		System.out.println("개인 일정 수정 들어온 vo :"+calendarVo);
		Map<String, Object> map = new HashMap<String, Object>();
		map = calendarService.ScheduleUpdate(scheduleNo,calendarVo);
		System.out.println("개인 일정 수정  retrun json 데이터 : "+map);
		return JsonResult.success(map);
	}
	
	//개인 일정 삭제
	@DeleteMapping("/delete/{scheduleNo}")
	public JsonResult ScheduleDelete(@PathVariable("scheduleNo")Long scheduleNo) {
		System.out.println("개인 일정 삭제 들어온 no 값 :"+scheduleNo);
		
		return JsonResult.success(calendarService.ScheduleDelete(scheduleNo) == 1);
	}
	
	//개인 일정 수정(Drag & Drop)
	@PutMapping("/schedule/update")
	public JsonResult DragUpdate(@RequestBody CalendarVo calendarVo) {
		System.out.println("drag fetch : "+calendarVo);
		
		return JsonResult.success(calendarService.DragUpdate(calendarVo)==1);
	}
	
	//캘린더 업무 클릭 시 업무 no 값으로 업무가 속한 프로젝트no 값 구하기

}