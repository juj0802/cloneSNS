package com.cos.photogramstart.handler;

import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;

import com.cos.photogramstart.handler.ex.CustomValidationApiException;
import com.cos.photogramstart.handler.ex.CustomValidationException;
import com.cos.photogramstart.util.Script;
import com.cos.photogramstart.web.dto.CMRespDto;

@RestController
@ControllerAdvice
public class ControllerExceptionHandler {
	
	
	@ExceptionHandler(CustomValidationException.class)
	public String validationException(CustomValidationException e) {
		
		//CMRespDto, Script 비교
		//1.클라이언트에게 응답할 때는 Script 좋음(브라우저)
		//2.Ajax통신 - CMRespDto(개발자)
		//3.Android통신 - CMRespDto(개발자)
		
		return Script.back(e.getErrorMap().toString());
//		return new CMRespDto(-1, e.getMessage(), e.getErrorMap());
	}
	
	@ExceptionHandler(CustomValidationApiException.class)
	public ResponseEntity<?> validationApiException(CustomValidationApiException e) {
		
		//CMRespDto, Script 비교
		//1.클라이언트에게 응답할 때는 Script 좋음(브라우저)
		//2.Ajax통신 - CMRespDto(개발자)
		//3.Android통신 - CMRespDto(개발자)
		
		return new ResponseEntity<>(new CMRespDto<>(-1, e.getMessage(), e.getErrorMap()), HttpStatus.BAD_REQUEST);
//		return new CMRespDto<>(-1, e.getMessage(), e.getErrorMap());
	}
	}