package com.douzone.haru.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.douzone.haru.repository.FileRepository;
import com.douzone.haru.vo.FileVo;

@Service
public class FileService {

	@Autowired
	private FileRepository fileRepository;

	public void uploadFile(FileVo fileVo, Long userNo, Long taskNo) {
		fileVo.setTaskNo(taskNo);
		fileVo.setFileMaker(fileRepository.getUserName(userNo));
		fileRepository.insertFile(fileVo);
	}

	public String getFileName(Long fileNo) {
		return fileRepository.findByFileName(fileNo);
	}

	public List<FileVo> getFiles(Long projectNo) {
		return fileRepository.selectFile(projectNo);
	}


	public int removeFile(Long fileNo) {
		return fileRepository.deleteFile(fileNo);
	}
}
