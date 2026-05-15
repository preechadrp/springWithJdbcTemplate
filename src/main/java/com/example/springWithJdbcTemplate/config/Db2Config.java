package com.example.springWithJdbcTemplate.config;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;

import lombok.extern.slf4j.Slf4j;

@Configuration
@Slf4j
public class Db2Config {
    //ตัวอย่างการเชื่อม database ตัวที่ 2
	@Bean(name = "db2DataSource")
	@ConfigurationProperties("spring.datasource.db2")
	DataSource db2DataSource() {
		
		log.debug("Init db2DataSource");
		return DataSourceBuilder.create().build();
	}

	@Bean(name = "jdbcTemplateDb2")
	JdbcTemplate jdbcTemplateDb2(
			@Qualifier("db2DataSource") DataSource ds) {
		
		log.debug("Init jdbcTemplateDb2");
		return new JdbcTemplate(ds);
	}
	
	/* ตัวอย่างการใช้งาน ต้องระบุ @Qualifier("jdbcTemplateDb2") ส่วนตัวหลักไม่ต้องใส่
	 @Repository
	public class LogRepository {
	
	    private final JdbcTemplate jdbcTemplate;
	
	    public LogRepository(@Qualifier("jdbcTemplateDb2") JdbcTemplate jdbcTemplate) {
	
	        this.jdbcTemplate = jdbcTemplate;
	    }
	
	}
	 */

}