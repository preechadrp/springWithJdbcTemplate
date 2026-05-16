package com.example.springWithJdbcTemplate.config;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.core.JdbcTemplate;

import com.zaxxer.hikari.HikariDataSource;

import lombok.extern.slf4j.Slf4j;

@Configuration
@Slf4j
public class DatabaseConfig {

	@Primary
	@Bean(name = "dataSourceDb1")
	@ConfigurationProperties(prefix = "spring.datasource.db1")
	HikariDataSource dataSourceDb1() {
		return new HikariDataSource();
	}

	@Primary
	@Bean(name = "jdbcTemplateDb1")
	JdbcTemplate jdbcTemplateDb1(@Qualifier("dataSourceDb1") HikariDataSource ds) {
		log.debug("Init jdbcTemplateDb1");
		return new JdbcTemplate(ds);
	}

	@Bean(name = "dataSourceDb2")
	@ConfigurationProperties(prefix = "spring.datasource.db2")
	HikariDataSource dataSourceDb2() {
		return new HikariDataSource();
	}

	@Bean(name = "jdbcTemplateDb2")
	JdbcTemplate jdbcTemplateDb2(@Qualifier("dataSourceDb2") HikariDataSource ds) {
		log.debug("Init jdbcTemplateDb2");
		return new JdbcTemplate(ds);
	}

}