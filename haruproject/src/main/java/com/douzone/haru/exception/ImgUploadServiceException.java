package com.douzone.haru.exception;

public class ImgUploadServiceException extends RuntimeException {
	private static final long serialVersionUID = 1L;

	public ImgUploadServiceException() {
		super("BlogServiceException Occurs");
	}

	public ImgUploadServiceException(String message) {
		super(message);
	}

}