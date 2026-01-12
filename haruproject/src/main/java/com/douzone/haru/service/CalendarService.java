package com.douzone.haru.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.config.Task;
import org.springframework.stereotype.Service;

import com.douzone.haru.repository.CalendarRepository;
import com.douzone.haru.vo.CalendarVo;

@Service
public class CalendarService {
	
	@Autowired
	CalendarRepository calendarRepository;

	public Map<String, Object> calendarMainSelect(Long authUserNo) {
		// TODO Auto-generated method stub
		Map<String, Object> map = new HashMap<String, Object>();
		List<CalendarVo> scheduleList = calendarRepository.calendarMainselect(authUserNo);
//		for(CalendarVo scheduleVo : scheduleList) {
//			map.put("scheduleNo", scheduleVo.getScheduleNo());
//			map.put("scheduleUserNo", scheduleVo.getUserNo());
//			map.put("scheduleStart", scheduleVo.getScheduleStart());
//			map.put("scheduleEnd", scheduleVo.getScheduleEnd());
//			map.put("scheduleContents", scheduleVo.getScheduleContents());
//			System.out.println("제바라아아아알 : "+map);
//		}
		map.put("scheduleList", scheduleList);
		List<Task> taskList = calendarRepository.taskMainselect(authUserNo);
		map.put("taskList", taskList);
		return map;
	}
	
	//개인 일정 추가
	public CalendarVo ScheduleAdd(CalendarVo calendarvo) {
		// TODO Auto-generated method stub
		return calendarRepository.ScheduleAdd(calendarvo);
	}

	//개인 일정 상세보기
	public CalendarVo ScheduleDetail(Long scheduleNo) {
		// TODO Auto-generated method stub
		return calendarRepository.ScheduleDetail(scheduleNo);
	}
	
	//개인 일정 수정
	public Map<String, Object> ScheduleUpdate(Long scheduleNo, CalendarVo calendarvo) {
		// TODO Auto-generated method stub
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("scheduleNo", scheduleNo);
		map.put("userNo", calendarvo.getUserNo());
		map.put("scheduleStart", calendarvo.getScheduleStart());
		map.put("scheduleEnd", calendarvo.getScheduleEnd());
		map.put("scheduleContents", calendarvo.getScheduleContents());
		return calendarRepository.ScheduleUpdate(map);
	}

	//개인 일정 삭제
	public int ScheduleDelete(Long scheduleNo) {
		// TODO Auto-generated method stub
		return calendarRepository.ScheduleDelete(scheduleNo);
	}

	//개인 일정 수정(Drag & Drop)
	public int DragUpdate(CalendarVo calendarVo) {
		// TODO Auto-generated method stub
		return calendarRepository.DragUpdate(calendarVo);
	}
	
	
}
