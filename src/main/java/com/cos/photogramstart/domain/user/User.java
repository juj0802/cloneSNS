package com.cos.photogramstart.domain.user;

import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.PrePersist;

import com.cos.photogramstart.domain.image.Image;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

// JPA - Java Persistence API (자바로 데이터를 영구적으로 저장(DB)할 수 있는 API를 제공)
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data // Getter, Setter
@Entity // DB에 테이블을 생성
public class User {
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY) 
	private int id; // id는 테이블에 데이터가 들어올 때마다 1,2,3,4 늘어난다. 사용자가 많으면 int로 커버가 안된다(Long을 써야함). 우리는 서비스하는게 아니므로 int로 만든다.
	@Column(unique = true, length=20,nullable = false)
	private String username;
	@Column(nullable = false)
	private String password;
	@Column(nullable = false)
	private String name;
	private String website; // 웹사이트
	private String bio; // 자기소개
	@Column(nullable = false)
	private String email;
	private String phone;
	private String gender;
	
	private String profileImageUrl; // 사진
	private String role; // 권한
	
	private LocalDateTime createDate;
	
	
	// 나는 연관관계의 주인이 아니다. 연관관계의 주인은 Image테이블의 user이다. 그러므로 테이블에 컬럼을 만들지마.
		// User를 Select할 때 해당 User id로 등록된 image들을 다 가져와
		// Lazy = User를 Select할 때 해당 User id로 등록된 image들을 가져오지마 - 대신 getImages() 함수의 image들이 호출될 때 가져와
		// Eager = User를 Select할 때 해당 user id로 등록된 image들을 전부 Join해서 가져와
	@OneToMany(mappedBy="user", fetch = FetchType.LAZY) 
	@JsonIgnoreProperties({"user"}) //user파싱 방지
	private List<Image> images; // 양방향 매핑
	
	@PrePersist // DB에 insert 되기 직전에 실행, 나중에 DB에 값을 넣을 때 위에 값만 넣어주면 createDate는 자동으로 들어감.
	public void createDate() {
		this.createDate = LocalDateTime.now();
	}
}