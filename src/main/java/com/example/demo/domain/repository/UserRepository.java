package com.example.demo.domain.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import com.example.demo.domain.entity.User;

@Repository
public class UserRepository {
	@Autowired
    private NamedParameterJdbcTemplate jdbcTemplate;
	
	public User findOneById(int id) {
//		return new User(0, "John", "Smith");
		String sql = "SELECT user_id, first_name, last_name FROM users WHERE user_id = :id";
		SqlParameterSource paramMap = new MapSqlParameterSource().addValue("id", id);
		
		try {
			return jdbcTemplate.queryForObject(sql, paramMap, new BeanPropertyRowMapper<User>(User.class));
		} catch (EmptyResultDataAccessException e) {
			return null;
		}
	}
	
	public List<User> findAll() {
//		List<User> users = new ArrayList<>();
//		users.add(new User(1, "John", "Smith"));
//		users.add(new User(2, "Jane", "Williams"));
//		users.add(new User(3, "Taro", "Yamada"));
//		users.add(new User(4, "Hanako", "Tanaka"));
//		return users;
		return jdbcTemplate.query("SELECT user_id, first_name, last_name FROM users ORDER BY user_id ASC",
				new BeanPropertyRowMapper<User>(User.class));
	}
	
	public User saveOne(User user) {
		SqlParameterSource paramMap = new BeanPropertySqlParameterSource(user);
		if (user.getUserId() == 0) {
			SimpleJdbcInsert insert = new SimpleJdbcInsert((JdbcTemplate)jdbcTemplate.getJdbcOperations())
					.withTableName("users")
					.usingGeneratedKeyColumns("user_id");
			Number key = insert.executeAndReturnKey(paramMap);
			user.setUserId((int)key);
		} else {
		}
		return user;
	}
}
