package com.maids.springbootquiz;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
// ComponentScan to reach (route) controllers in the controller package
@ComponentScan({"com.maids.springbootquiz","com.maids.springbootquiz.controller"})
public class SpringBootQuizApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringBootQuizApplication.class, args);
	}

}
