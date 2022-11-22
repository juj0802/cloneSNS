package com.cos.photogramstart.service;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cos.photogramstart.config.auth.PrincipalDetails;
import com.cos.photogramstart.domain.image.Image;
import com.cos.photogramstart.domain.image.ImageRepository;
import com.cos.photogramstart.web.dto.image.ImageUploadDto;

import lombok.RequiredArgsConstructor;


@RequiredArgsConstructor
@Service
public class ImageService {
	

	
	private final ImageRepository imageRepository;
	
	@Value("${file.path}")
	private String uploadFolder;//application.yml에 있는 file: path: C:/workspace/springbootwork/upload/ 경로가 uploadFolder에 저장
	
@Transactional
public void uploadImage(ImageUploadDto imageUploadDto, PrincipalDetails principalDetails) {
	
		UUID uuid = UUID.randomUUID();//파일 랜덤번호 부여
		String imageFileName = uuid+"_"+imageUploadDto.getFile().getOriginalFilename(); //파일형식
		System.out.println("이미지 파일이름:"+imageFileName);
		
		Path imageFilePath = Paths.get(uploadFolder+imageFileName);
		

		// 통신, I/O -> 예외가 발생할 수 있다.
		try {
			Files.write(imageFilePath, imageUploadDto.getFile().getBytes());
		} catch (Exception e) {
			e.printStackTrace();
		}
		Image image = imageUploadDto.toEntity(imageFileName, principalDetails.getUser()); // e05f4c5e-fe69-4c3d-8aee-3257b80940e6_flowers.jpg
		//Image imageEntity = 
				imageRepository.save(image);
		
		//System.out.println(imageEntity);
		
	
	
	}
	
}
