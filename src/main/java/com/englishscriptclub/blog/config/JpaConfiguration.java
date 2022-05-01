package com.englishscriptclub.blog.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.web.config.EnableSpringDataWebSupport;

@Configuration
@EnableJpaRepositories(basePackages = "com.englishscriptclub.blog.dao")
@EnableSpringDataWebSupport
@ComponentScan
public class JpaConfiguration {
}
