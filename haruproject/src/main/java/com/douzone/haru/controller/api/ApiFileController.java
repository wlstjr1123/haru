package com.douzone.haru.controller.api;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.douzone.haru.dto.JsonResult;
import com.douzone.haru.service.FileService;
import com.douzone.haru.service.FileUploadService;
import com.douzone.haru.vo.FileVo;

@RestController
public class ApiFileController {

	@Autowired
	private FileUploadService fileUploadService;

	@Autowired
	private FileService fileService;

	@GetMapping("/api/dashboard/{projectNo}/file")
	public JsonResult projectFile(@PathVariable("projectNo") Long projectNo) throws IOException {
		List<FileVo> fileVo = fileService.getFiles(projectNo);
		return JsonResult.success(fileVo);
	}

	@PostMapping("/api/upload")
	public JsonResult FileUpload(@RequestParam("file") MultipartFile multipartFile,
			@RequestParam("taskNo") Long taskNo,
			@RequestParam("userNo") Long userNo) throws IOException {
		FileVo fileVo = new FileVo();
		fileUploadService.restore(fileVo, multipartFile);
		fileService.uploadFile(fileVo, userNo, taskNo); //파일 insert

		return JsonResult.success(fileVo);
	}

	@GetMapping("/api/download/{fileNo}")
//	@CrossOrigin(value = { "*" }, exposedHeaders = { "Content-Disposition" })
	public ResponseEntity<Resource> downloadFile(@PathVariable("fileNo") Long fileNo) throws IOException {
		Path path = Paths.get("/upload-haru/" + fileService.getFileName(fileNo));//new IO 를 사용해서 경로(Path)타입의 path를 만들어낸다
		String contentType = Files.probeContentType(path);//그 경로의 내용의 타입을 알아낸다.
		HttpHeaders headers = new HttpHeaders();
		System.out.println("Header에 들어가는 ContentType이다 파일이니까 File이 나와야겠지? >>>>>>" + contentType);
		headers.add(HttpHeaders.CONTENT_TYPE, contentType); // content type을 조사하여 response header에 세팅
		headers.set("Content-Disposition", "attachment; filename=" + path.getFileName()); // 브라우저에서 url 호출
		Resource resource = new InputStreamResource(Files.newInputStream(path));
		return new ResponseEntity<>(resource, headers, HttpStatus.OK);
	}

	@DeleteMapping("/api/file/del/{fileNo}")
	public JsonResult fileDelete(@PathVariable("fileNo") Long fileNo) {

		int result = fileService.removeFile(fileNo);
		return JsonResult.success(result == 1 ? result : -1);
	}
}
