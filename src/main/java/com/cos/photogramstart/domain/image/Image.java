package com.cos.photogramstart.domain.image;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.PrePersist;

import com.cos.photogramstart.domain.user.User;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class Image {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String caption; // 오늘 나 너무 피곤해
	private String postImageUrl; // 사진을 전송받아서 그 사진을 서버의 특정 폴더에 저장 - DB에 그 저장된 경로를 insert
	
	@JoinColumn(name="userId")
	@ManyToOne
	private User user; // 누가 업로드 했는지.
	
	// 이미지 좋아요
	
	// 댓글
	
	private LocalDateTime createDate; // 항상 DB에는 시간이 들어가야 한다.(언제 들어왔는지)
	
	@PrePersist//콜백처리
	public void createDate() {
		this.createDate = LocalDateTime.now();
	}

	@Override
	public String toString() {
		return "Image [id=" + id + ", caption=" + caption + ", postImageUrl=" + postImageUrl + ", createDate="
				+ createDate + "]";
	}
	
	
}