package com.example.demo.domain.repository;

import java.io.IOException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.web.multipart.MultipartFile;

import com.example.demo.domain.entity.Photo;

@Repository
public class PhotoRepository {
	@Autowired
    private NamedParameterJdbcTemplate jdbcTemplate;

	public int saveOne(MultipartFile multipartFile) throws IOException {
		byte[] photo = multipartFile.getBytes();
		String contentType = multipartFile.getContentType();
		String sql = "INSERT INTO photos (photo, content_type) VALUES (:photo, :contentType)";
		return jdbcTemplate.update(sql, new MapSqlParameterSource()
				.addValue("photo", photo)
				.addValue("contentType", contentType));
	}
	
	public Photo findById(int photoId) {
		byte[] photo = null;
		String sql = "SELECT content_type, photo FROM photos WHERE photo_id = :photoId";
		return jdbcTemplate.queryForObject(sql, new MapSqlParameterSource().addValue("photoId", photoId),
				new BeanPropertyRowMapper<Photo>(Photo.class));
	}
	
}
