package com.example.demo.controller;

import java.io.IOException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.example.demo.domain.entity.Photo;
import com.example.demo.domain.repository.PhotoRepository;

@RestController
@RequestMapping("/photos")
public class PhotoController {
	
	@Autowired
	PhotoRepository photoRepository;
	
	@GetMapping("/{photoId}")
	public HttpEntity<byte[]> getPhoto(@PathVariable("photoId") int photoId) {
		Photo photo = photoRepository.findById(photoId);
//		System.out.println(photo.toString());
		HttpHeaders headers = new HttpHeaders();
		headers.add("Content-Type", photo.getContentType());
//		headers.setContentType(MediaType.parseMediaType(photo.getContentType()));
		headers.setContentLength(photo.getPhoto().length);
		return new HttpEntity<byte[]>(photo.getPhoto(), headers);
	}
	
	@PostMapping
	public int uploadPhoto(@RequestParam("file") MultipartFile multipartFile) throws IOException {
		return photoRepository.saveOne(multipartFile);
	}
}
