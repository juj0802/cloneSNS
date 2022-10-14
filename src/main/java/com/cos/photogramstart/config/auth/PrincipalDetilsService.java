package com.cos.photogramstart.config.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.cos.photogramstart.domain.user.User;
import com.cos.photogramstart.domain.user.UserRepository;

import lombok.RequiredArgsConstructor;
@RequiredArgsConstructor
@Service//auth/sign 요청이 포스트방식이면 인터셉트한다
//시큐리티가 서버에서 대기타면서 누군가 포스트방식을 보내면 UserDetailsService 객체에 처리를 위임한다
public class PrincipalDetilsService implements UserDetailsService{

	
	private final UserRepository userRepository;
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		//패스워드는 알아서 체크해준다
		User userEntity = userRepository.findByUsername(username);
		if(userEntity == null) {
			return null;
		} else {
			return new PrincipalDetails(userEntity);
		}
		
	
		
		
	}
	

}
