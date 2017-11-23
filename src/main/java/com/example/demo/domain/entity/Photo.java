package com.example.demo.domain.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Photo {
	int photoId;
	String contentType;
	byte[] photo;
}
