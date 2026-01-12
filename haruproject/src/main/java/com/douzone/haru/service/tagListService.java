package com.douzone.haru.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.douzone.haru.repository.TagListRepository;
import com.douzone.haru.vo.TagListVo;

@Service
public class tagListService {

	@Autowired
	private TagListRepository tagListRepository;
	
	public List<TagListVo> selectTag(long taskNo) {
		return tagListRepository.selectTag(taskNo);
	}
}
