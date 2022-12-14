package com.cos.photogramstart.handler.ex;

import java.util.Map;

public class CustomValidationApiException extends RuntimeException {

	// 객체를 구분할 때 쓰는 것(JVM)
	private static final long serialVersionUID = 1L;

	private Map<String, String> errorMap;
	
	public CustomValidationApiException(String message) {
		super(message);
	} // 생성자
	
	public CustomValidationApiException(String message, Map<String,String> errorMap) {
		super(message);
		this.errorMap = errorMap;
	} // 생성자
	
	public Map<String, String> getErrorMap() {
		return errorMap;
	} // Getter
}