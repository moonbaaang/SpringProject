package com.multi.kdigitalboot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class KdigitalbootApplication {
// 메인 메서드로 내장 tomcat을 실행함
	public static void main(String[] args) {
		SpringApplication.run(KdigitalbootApplication.class, args);
	}
//수동 브라우저 열어서 http://127.0.0.1:9000/hello.boot - hello.jsp 실행 결과 보기
}
