package com.douzone.haru.repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.config.Task;
import org.springframework.stereotype.Repository;

import com.douzone.haru.vo.CalendarVo;

@Repository
public class CalendarRepository {

	@Autowired
	SqlSession sqlSession;
	
	//스케줄 리스트
	public List<CalendarVo> calendarMainselect(Long authUserNo) {
		// TODO Auto-generated method stub
		return sqlSession.selectList("schedule.schedulefindAll",authUserNo);
	}
	
	//개인 일정 추가
	public CalendarVo ScheduleAdd(CalendarVo calendarvo) {
		// TODO Auto-generated method stub
			sqlSession.insert("schedule.calendarscheduleadd",calendarvo);
		return calendarvo; 
	}

	//개인 일정 상세보기
	public CalendarVo ScheduleDetail(Long scheduleNo) {
		// TODO Auto-generated method stub
		return sqlSession.selectOne("schedule.calendarscheduledetail",scheduleNo);
	}

	//개인 일정 수정
	public Map<String, Object> ScheduleUpdate(Map<String, Object> map) {
		// TODO Auto-generated method stub
		sqlSession.update("schedule.calendarscheduleupdate",map);
//		System.out.println("변경된 map?? : "+map); 확인o / 수정된 값 넘어옴
		return map; 
	}

	//캘린더 내 업무 리스트
	public List<Task> taskMainselect(Long authUserNo) {
		// TODO Auto-generated method stub
		return sqlSession.selectList("schedule.taskfindAll", authUserNo);
	}

	//개인 일정 삭제
	public int ScheduleDelete(Long scheduleNo) {
		// TODO Auto-generated method stub
		return sqlSession.delete("schedule.calendarscheduledelete",scheduleNo);
	}

	//개인 일정 수정(Drag & Drop)
	public int DragUpdate(CalendarVo calendarvo) {
		// TODO Auto-generated method stub
		return sqlSession.update("schedule.dragupdate",calendarvo);
	}
}
