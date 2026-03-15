package com.example.springWithJdbcTemplate.repository;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.example.springWithJdbcTemplate.dto.User;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class UserRepository {

	private final JdbcTemplate jdbcTemplate;

	public List<User> findAll() {

		String sql = "SELECT USER_ID,USER_NAME ,BIRTH_DAY,CREATED_DATE,CREATED_BY FROM USER_TABLE order by USER_ID";

		return jdbcTemplate.query(sql, (rs, rowNum) -> new User().setUserid(rs.getInt("USER_ID"))
				.setUsername(rs.getString("USER_NAME"))
				.setBirthDay(rs.getDate("BIRTH_DAY") != null ? rs.getDate("BIRTH_DAY").toLocalDate() : null)
				.setCreatedBy(rs.getString("CREATED_BY"))
				.setCreatedDate(rs.getTimestamp("CREATED_DATE") != null ? rs.getTimestamp("CREATED_DATE").toLocalDateTime() : null));
	}

	public void insertUser() {

		int start = 2011;
		int to = start + 10;
		for (int idx = start; idx < to; idx++) {
			var user = new User().setUserid(idx)
					.setUsername("user " + idx)
					.setBirthDay(LocalDate.now())
					.setCreatedBy("c" + idx)
					.setCreatedDate(LocalDateTime.now());

			//System.out.println("idx " + idx);

			String sql = "INSERT INTO USER_TABLE(USER_ID,USER_NAME,BIRTH_DAY,CREATED_DATE,CREATED_BY) VALUES (?,?,?,?,?)";

			jdbcTemplate.update(sql,
					user.getUserid(),
					user.getUsername(),
					user.getBirthDay(),
					user.getCreatedDate(),
					user.getCreatedBy());

		}

	}

	public void insertUsersByBatch() {
		//=== การ insert แบบ batch
		String sql = """
				    INSERT INTO USER_TABLE
				    (USER_ID,USER_NAME,BIRTH_DAY,CREATED_DATE,CREATED_BY)
				    VALUES (?,?,?,?,?)
				""";

		jdbcTemplate.batchUpdate(sql, new BatchPreparedStatementSetter() {

			int start = 2011;

			@Override
			public void setValues(PreparedStatement ps, int i) throws SQLException {

				int idx = start + i;

				ps.setInt(1, idx);
				ps.setString(2, "user " + idx);
				ps.setDate(3, java.sql.Date.valueOf(LocalDate.now()));
				ps.setTimestamp(4, java.sql.Timestamp.valueOf(LocalDateTime.now()));
				ps.setString(5, "c" + idx);
			}

			@Override
			public int getBatchSize() {
				return 10;
			}

		});
	}

}