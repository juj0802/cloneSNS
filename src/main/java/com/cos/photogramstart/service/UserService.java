package com.cos.photogramstart.service;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cos.photogramstart.domain.user.User;
import com.cos.photogramstart.domain.user.UserRepository;
import com.cos.photogramstart.handler.ex.CustomException;
import com.cos.photogramstart.web.dto.user.UserProfileDto;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class UserService {

	private final UserRepository userRepository;
	private final BCryptPasswordEncoder bCryptPasswordEncoder;
	
	@Transactional
	public User update(int id, User user) {
		//1.영속화
	//	User userEntity = userRepository.findById(id).get(); //1.무조건 찾았다. 걱정마 get() 2.못찾았어 익셉션 발동시킬게 orElseThrow()
		User userEntity = userRepository.findById(id).orElseThrow(() -> {
			return new IllegalArgumentException("찾을 수 없는 id입니다.");
		});
		//2.영속화된 오브젝트를 수정 - 더티체킹(업데이트 완료)
		
		String rawPassword = user.getPassword();
		String encPassword = bCryptPasswordEncoder.encode(rawPassword);
		
		userEntity.setName(user.getName());
		userEntity.setPassword(encPassword);
		userEntity.setBio(user.getBio());
		userEntity.setWebsite(user.getWebsite());
		userEntity.setPhone(user.getPhone());
		userEntity.setGender(user.getGender());
		return userEntity;
	
	}
	@Transactional(readOnly = true) //조금 더 빠른 jpa연산을 위해서 읽기전용으로설정
	public UserProfileDto userProfile(int pageUserid,int principalId) {
		// SELECT * FROM image WHERE userId = :userId;
		UserProfileDto dto = new UserProfileDto();
		
		User userEntity = userRepository.findById(pageUserid).orElseThrow(()-> {
			throw new CustomException("해당 프로필 페이지는 없는 페이지입니다.");
		});
		//userEntity.getImages().get(0);
		dto.setUser(userEntity);
		dto.setPageOwnerState(pageUserid == principalId); 
		dto.setImageCount(userEntity.getImages().size());
		
		
		
		return dto;
	}
}