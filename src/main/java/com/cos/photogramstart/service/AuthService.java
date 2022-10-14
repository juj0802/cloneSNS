package com.cos.photogramstart.service;


import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.cos.photogramstart.domain.user.User;
import com.cos.photogramstart.domain.user.UserRepository;
import lombok.RequiredArgsConstructor;



@RequiredArgsConstructor
@Service //IOC(인스턴스의 생성과 소멸까지 컨테이너가 관리) , 트랜잭션관리
public class AuthService {
	
	 private final UserRepository userRepository;

	 private final BCryptPasswordEncoder bCryptPasswordEncoder;
	
	@Transactional //write메서드 실행할때 실행
	public User join(User user) {
		String rawPassword = user.getPassword();
		String encPassword = bCryptPasswordEncoder.encode(rawPassword);
		user.setPassword(encPassword);
		user.setRole("ROLE_USER"); // 관리자 ROLE_ADMIN
		User userEntity = userRepository.save(user);
		return userEntity;

	}
}
