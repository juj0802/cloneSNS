package com.cos.photogramstart.handler.ex;

public class CustomException extends RuntimeException {

	// 객체를 구분할 때 쓰는 것(JVM)
	private static final long serialVersionUID = 1L;
	
	public CustomException(String message) {
		super(message);
	} // 생성자
}
