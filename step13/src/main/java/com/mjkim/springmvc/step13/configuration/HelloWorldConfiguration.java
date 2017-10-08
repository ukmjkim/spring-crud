package com.mjkim.springmvc.step13.configuration;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@Configuration
@EnableWebMvc
@ComponentScan(basePackages="com.mjkim.springmvc.step13")
public class HelloWorldConfiguration {

}
