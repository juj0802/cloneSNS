package com.cos.photogramstart.web;


import java.util.HashMap;
import java.util.Map;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cos.photogramstart.domain.user.User;
import com.cos.photogramstart.handler.ex.CustomValidationException;
import com.cos.photogramstart.service.AuthService;
import com.cos.photogramstart.util.Script;
import com.cos.photogramstart.web.dto.auth.SignupDto;

@Controller //1.IoC에 등록이 됐다는 의미 2.파일을 리턴하는 컨트롤러
public class AuthController {

	private static final Logger log = LoggerFactory.getLogger(AuthController.class);
	
	@Autowired
	private AuthService authService;
	
	@GetMapping("/auth/signin")
	public String signinForm() {
		return "auth/signin";
	}
	
	@GetMapping("/auth/signup")
	public String signupForm() {
		return "auth/signup";
	}
	
	// 회원가입버튼 -> /auth/signup -> /auth/signin
		@PostMapping("/auth/signup")
		public String signup(@Valid SignupDto signupDto, BindingResult bindingResult) { // form으로 데이터가 날아오면 데이터 형식이 key=value 형식으로 데이터가 들어온다. (x-www.form-urlencoded 방식)
			
			if(bindingResult.hasErrors()) {
				Map<String, String> errorMap = new HashMap<>();
				
				for(FieldError error:bindingResult.getFieldErrors()) {
					errorMap.put(error.getField(), error.getDefaultMessage());
					System.out.println("=================================");
					System.out.println(error.getDefaultMessage());
					System.out.println("=================================");

				}
				throw new CustomValidationException("유효성검사 실패함", errorMap); // 유효성중 하나라도 실패하면 bindingresult에 값이 담기고 hashmap에 데이터를 담고 , throw로 예외처리한다
		} else {
			log.info(signupDto.toString());
			// User 오브젝트에 SignupDto 데이터를 넣어야함
			User user = signupDto.toEntity();
			log.info(user.toString());
			User userEntity = authService.join(user);
			System.out.println(userEntity);
			return "auth/signin";
		}
	}
}


